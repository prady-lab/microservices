/**
 *
 */

package com.prady.sample.cloud.gateway.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomUtils;

import com.prady.sample.cloud.common.dto.user.RoleDTO;

/**
 * @author Prady
 */
public class RoleHelper {

    private List<RoleDTO> roles = new ArrayList<>();

    public Boolean isRoleEmpty() {
        return CollectionUtils.isEmpty(roles);
    }

    /**
     * @param roles
     */
    public void updateRoleList(List<RoleDTO> roles) {
        this.roles.addAll(roles);
    }

    /**
     * @param roleId
     */
    public void removeRole(String roleId) {
        if (null != roleId && CollectionUtils.isNotEmpty(roles)) {
            roles.removeIf(role -> roleId.equals(role.getId()));
        }
    }

    /**
     * @return
     */
    public RoleDTO randomRole() {
        int index = RandomUtils.nextInt(0, roles.size());
        return roles.get(index);
    }

    /**
     * @return
     */
    public String randomRoleId() {
        return randomRole().getId();
    }

    public RoleDTO populateRoleDTO() {
        RoleDTO role = new RoleDTO();
        role.setRoleName("Role Name " + RandomUtils.nextLong(0, 1000));
        role.setRoleDescription("Role Description " + RandomUtils.nextLong(0, 1000));

        return role;
    }
}
