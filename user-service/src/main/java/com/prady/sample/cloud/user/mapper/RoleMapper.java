/**
 *
 */

package com.prady.sample.cloud.user.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.springframework.util.CollectionUtils;

import com.prady.sample.cloud.common.cache.ApplicationCache;
import com.prady.sample.cloud.common.dto.user.PermissionDTO;
import com.prady.sample.cloud.common.dto.user.RoleDTO;
import com.prady.sample.cloud.user.domain.Permission;
import com.prady.sample.cloud.user.domain.Role;

/**
 * @author Prady
 */
@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDTO toRoleDTO(Role role);

    List<RoleDTO> toRoleDTOs(List<Role> roles);

    Role toRole(RoleDTO roleDTO);

    List<Role> toRoles(List<RoleDTO> roleDTOs);

    default List<Permission> permissionNameToPermission(List<String> permissionNames) {
        if (!CollectionUtils.isEmpty(permissionNames)) {
            List<Permission> permissions = permissionNames.stream().map(permissionName -> {
                PermissionDTO permissionDTO = ApplicationCache.getInstance().getPermissionForName(permissionName);
                if (null != permissionDTO) {
                    return new Permission(permissionDTO.getId(), permissionDTO.getPermissionName(),
                            permissionDTO.getPermissionDescription());
                }
                return null;
            }).collect(Collectors.toList());
            permissions.removeIf(Objects::isNull);
            return permissions;
        }
        return null;
    }

    default List<String> permissionToPermissionName(List<Permission> permissions) {
        if (!CollectionUtils.isEmpty(permissions)) {
            return permissions.stream().map(Permission::getPermissionName).collect(Collectors.toList());
        }
        return null;
    }

}
