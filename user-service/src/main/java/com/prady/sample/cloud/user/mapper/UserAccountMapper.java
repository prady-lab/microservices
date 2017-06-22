
package com.prady.sample.cloud.user.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.prady.sample.cloud.common.dto.user.RoleDTO;
import com.prady.sample.cloud.common.dto.user.UserAccountDTO;
import com.prady.sample.cloud.user.domain.Permission;
import com.prady.sample.cloud.user.domain.Role;
import com.prady.sample.cloud.user.domain.UserAccount;

/**
 * Created by Prady on 6/18/17.
 */
@Mapper(componentModel = "spring")
public interface UserAccountMapper {

    @Mapping(target = "password", ignore = true)
    UserAccountDTO toUserAccountDTO(UserAccount userAccount);

    List<UserAccountDTO> toUserAccountDTOs(List<UserAccount> userAccounts);

    @Mapping(target = "password", ignore = true)
    UserAccount toUserAccount(UserAccountDTO userAccountDTO);

    @Mapping(target = "password", ignore = true)
    void toUserAccount(UserAccountDTO userAccountDTO, @MappingTarget UserAccount userAccount);

    List<UserAccount> toUserAccounts(List<UserAccountDTO> userAccountDTOs);

    RoleDTO toRoleDTO(Role role);

    List<RoleDTO> toRoleDTOs(List<Role> roles);

    Role toRole(RoleDTO roleDTO);

    List<Role> toRoles(List<RoleDTO> roleDTOs);

    default String permissionToString(Permission permission) {
        return null != permission ? permission.getPermissionName() : null;
    }

}
