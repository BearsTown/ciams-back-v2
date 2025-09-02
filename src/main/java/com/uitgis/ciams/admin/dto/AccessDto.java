package com.uitgis.ciams.admin.dto;

import com.uitgis.ciams.model.CiamsAccessRoleLink;
import com.uitgis.ciams.model.CiamsAccessUserLink;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AccessDto {
    private String userId;
    private String roleCode;
    private String menuCode;


    @Getter
    @Setter
    public static class Add {
        // 		private List<String> roles;
        private List<String> users;
        private List<String> menus;
        // 		private String userId;
        private String roleCode;
    }


    @Getter
    @Setter
    public static class RoleResult extends CiamsAccessRoleLink {
        private String accessRoleCodeName;
        private String accessMenuCodeName;
    }


    @Getter
    @Setter
    public static class UserResult extends CiamsAccessUserLink {
        private String userName;
        private String accessRoleCodeName;
    }


    @Getter
    @Setter
    public static class Delete {
        private String roleCode;
        // 		private String userId;
// 		private List<String> roles;
        private List<String> users;
        private List<String> menus;
    }


    @Getter
    @Setter
    public static class MenuAccess {
        String userId;
        String accessMenuCode;
        String code;
        String codeName;
        String codeVal;
    }

}
