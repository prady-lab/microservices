
package com.prady.sample.cloud.user.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Prady
 */
@Document
public class Role {

    @Id
    private String id;

    private String roleName;

    private String roleDescription;

    @DBRef
    private List<Permission> permissions = new ArrayList<>();

    public Role() {
        super();
    }

    public Role(String id) {
        super();
        setId(id);
    }

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

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Role role = (Role) o;

        if (!id.equals(role.id)) {
            return false;
        }
        if (!roleName.equals(role.roleName)) {
            return false;
        }
        return roleDescription != null ? roleDescription.equals(role.roleDescription) : role.roleDescription == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + roleName.hashCode();
        result = 31 * result + (roleDescription != null ? roleDescription.hashCode() : 0);
        return result;
    }
}
