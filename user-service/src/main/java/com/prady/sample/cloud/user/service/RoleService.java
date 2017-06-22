
package com.prady.sample.cloud.user.service;

import java.util.List;

import com.prady.sample.cloud.common.dto.user.RoleDTO;

/**
 * @author Prady
 */
public interface RoleService {

    /**
     * @return
     */
    List<RoleDTO> getRoles();

    RoleDTO getRolebyRoleName(String roleName);

    /**
     * @param roleDTO
     */
    void create(RoleDTO roleDTO);

    /**
     * @param roleDTO
     */
    void createIfNotPresent(RoleDTO roleDTO);

    /**
     * @param id
     * @return
     */
    RoleDTO get(String id);

    /**
     * @param id
     */
    void delete(String id);

    /**
     * @param id
     * @param userAccountDTO
     * @return
     */
    RoleDTO update(String id, RoleDTO roleDTO);

    /**
     * @param permissionName
     * @param permissionDescription
     */
    void createPermissionIfNotPresent(String permissionName, String permissionDescription);

    /**
     *
     */
    void refreshPermissionCache();
}
