package com.uitgis.ciams.admin.service.impl;

import com.uitgis.ciams.admin.dto.UserDto;
import com.uitgis.ciams.admin.mapper.AdminAccessMapper;
import com.uitgis.ciams.admin.mapper.AdminUserMapper;
import com.uitgis.ciams.admin.service.AdminUserService;
import com.uitgis.ciams.config.UitPasswordEncoder;
import com.uitgis.ciams.model.CiamsUser;
import com.uitgis.ciams.model.CiamsUserRole;
import com.uitgis.ciams.admin.dto.UserRoleDto;
import com.uitgis.ciams.user.dto.PaginationDto;
import com.uitgis.ciams.admin.mapper.AdminUserRoleMapper;
import com.uitgis.ciams.util.CipherUtil;
import com.uitgis.ciams.util.PageUtil;
import com.uitgis.ciams.util.ValidUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdminUserServiceImpl implements AdminUserService {
    private final AdminUserMapper adminUserMapper;
    private final AdminAccessMapper adminAccessMapper;
    private final AdminUserRoleMapper adminUserRoleMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Map<String, Object> selectList(UserDto.Find find) {
        int cnt = adminUserMapper.selectCnt(find);

        PaginationDto page = PageUtil.setTotalCount(find, cnt);

        List<UserDto.Data> list = adminUserMapper.selectList(find);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("list", list);
        resultMap.put("page", page);

        return resultMap;
    }


    /**
     * 패스워드 초기화
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void initPass(String loginId) {
        CiamsUser user = new CiamsUser();
        user.setLoginId(loginId);
        user.setUserPassword(passwordEncoder.encode(loginId));
        user.setLoginFailCnt(0);

        adminUserMapper.updateById(user);
    }


    /**
     * 사용자 삭제
     *
     * @param userList
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public void removeByIds(List<String> userList) {
        adminUserMapper.deleteByIds(userList);
        adminAccessMapper.deleteByIds(userList);
        adminUserRoleMapper.deleteByIds(userList);
    }

    @Override
    public void updateLockUsers(UserDto.Lock lockUsers) {
        adminUserMapper.updateLockUsers(lockUsers);
    }


    @Override
    @Transactional
    public void modify(UserDto.Modify param) {
        CiamsUser modify = new CiamsUser();
        BeanUtils.copyProperties(param, modify);

        String newPassword = param.getNewPassword();
        if (ValidUtil.notEmpty(newPassword)) {
            UserDto.Data model = adminUserMapper.selectById(param.getLoginId());
            String curPassword = "";
            try {
                curPassword = CipherUtil.decryptRSA(param.getCurPassword());
            } catch (NoSuchAlgorithmException | NoSuchPaddingException |
                     InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
                log.error(e.getMessage());
            }

            if (!passwordEncoder.matches(curPassword, model.getUserPassword())) {
                throw new BadCredentialsException("Invalid password");
            }
//			modify.setUserPassword(passwordEncoder.encode(param.getNewPassword()));
            modify.setUserPassword(uitPasswordEncoder().encode(param.getNewPassword()));
            adminUserMapper.updateById(modify);
        } else {
            adminUserMapper.updateById(modify);

            CiamsUserRole modifyRole = new CiamsUserRole();
            modifyRole.setUserId(param.getLoginId());
            modifyRole.setUserRole(param.getUserRole());

            CiamsUserRole rol = adminUserRoleMapper.selectById(param.getLoginId());
            if (rol != null) {
                adminUserRoleMapper.updateById(modifyRole);
            } else {
                modifyRole.setRoleYn("Y");

                UserRoleDto.Add add = UserRoleDto.Add.builder().build();
                BeanUtils.copyProperties(modifyRole, add);
                adminUserRoleMapper.insert(add);
            }
        }
    }


    @Override
    public UserDto.Select selectById(String loginId) {
        UserDto.Select result = new UserDto.Select();
        UserDto.Data data = adminUserMapper.selectById(loginId);
        BeanUtils.copyProperties(data, result);
        return result;
    }


    public PasswordEncoder uitPasswordEncoder() {
        return new UitPasswordEncoder();
    }
}
