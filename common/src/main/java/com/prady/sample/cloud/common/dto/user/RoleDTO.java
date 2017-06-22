
package com.prady.sample.cloud.common.dto.user;

import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

/**
 * @author Prady
 */
public class RoleDTO {

    private String id;

    @NotNull(message = "Role name cannot be null")
    @NotEmpty(message = "Role name cannot be null")
    private String roleName;

    private String roleDescription;

    // private List<String> permissions = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    /*
     * public List<String> getPermissions() { return permissions; } public void setPermissions(List<String> permissions) { this.permissions
     * = permissions; }
     */
}
