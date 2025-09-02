package com.uitgis.ciams.admin.controller;

import com.uitgis.ciams.admin.dto.UserDto;
import com.uitgis.ciams.admin.service.AdminUserService;
import com.uitgis.ciams.admin.service.AdminUserRoleService;
import com.uitgis.ciams.enums.ActionTypeEnum;
import com.uitgis.ciams.enums.MenuEnum;
import com.uitgis.ciams.service.CiamsCommonService;
import com.uitgis.ciams.admin.dto.UserRoleDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("/api/v1/admin/user")
@RestController
@AllArgsConstructor
public class AdminUserController {
    private final CiamsCommonService ciamsCommonService;
    private final AdminUserService adminUserService;
    private final AdminUserRoleService ciamsAdminUserRoleService;


    @GetMapping("/list")
    public ResponseEntity<?> list(UserDto.Find find) throws Exception {
        Map<String, Object> list = adminUserService.selectList(find);
        return ResponseEntity.ok(list);
    }


    @GetMapping("/{loginId}")
    public ResponseEntity<?> selectUser(@PathVariable String loginId) throws Exception {
        UserDto.Select userDto = adminUserService.selectById(loginId);
        return ResponseEntity.ok(userDto);
    }


    /**
     * 사용자 패스워드 초기화
     *
     * @param loginId 초기화 대상 사용자 아이디
     */
    @PutMapping("/{loginId}/initPass")
    public ResponseEntity<?> initPassword(@PathVariable String loginId) throws Exception {

        adminUserService.initPass(loginId);

        ciamsCommonService.log(MenuEnum.USER, ActionTypeEnum.UPDATE, loginId);

        return ResponseEntity.ok().build();
    }


    /**
     * 로그인 사용자의 정보 수정
     *
     * @param param 수정 정보
     */
    @PutMapping("/{loginId}")
    public ResponseEntity<?> modify(@PathVariable String loginId, @RequestBody UserDto.Modify param) throws Exception {

        param.setLoginId(loginId);
        adminUserService.modify(param);

        ciamsCommonService.log(MenuEnum.USER, ActionTypeEnum.UPDATE, loginId);

        return ResponseEntity.ok().build();
    }


    /**
     * 삭제
     *
     * @param userList 삭제 대상 사용자 아이디
     */
    @DeleteMapping
    public ResponseEntity<?> remove(@RequestParam("userList") List<String> userList) throws Exception {

        adminUserService.removeByIds(userList);

        ciamsCommonService.log(MenuEnum.USER, ActionTypeEnum.DELETE, userList);

        return ResponseEntity.ok().build();
    }


    /**
     * 승인
     *
     * @param param
     */
    @PutMapping("/approve")
    public ResponseEntity<?> approve(@RequestBody UserRoleDto.Approve approve) throws Exception {

        ciamsAdminUserRoleService.modifyApprove(approve);

        ciamsCommonService.log(MenuEnum.USER, ActionTypeEnum.UPDATE, approve.getUserList());

        return ResponseEntity.ok().build();
    }


    /**
     * 잠김
     *
     * @param param
     */
    @PutMapping("/lock")
    public ResponseEntity<?> lock(@RequestBody UserDto.Lock lockUsers) throws Exception {
        adminUserService.updateLockUsers(lockUsers);

        ciamsCommonService.log(MenuEnum.USER, ActionTypeEnum.UPDATE, lockUsers.getUserList());

        return ResponseEntity.ok().build();
    }
}
