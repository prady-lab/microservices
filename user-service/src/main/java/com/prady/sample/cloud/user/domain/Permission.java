
package com.prady.sample.cloud.user.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Prady
 */
@Document
public class Permission {

    @Id
    private String id;

    private String permissionName;

    private String permissionDescription;

    public Permission() {
        super();
    }

    public Permission(String id) {
        super();
        setId(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionDescription() {
        return permissionDescription;
    }

    public void setPermissionDescription(String permissionDescription) {
        this.permissionDescription = permissionDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Permission permission = (Permission) o;

        if (!id.equals(permission.id)) {
            return false;
        }
        if (!permissionName.equals(permission.permissionName)) {
            return false;
        }
        return permissionDescription != null ? permissionDescription.equals(permission.permissionDescription)
                : permission.permissionDescription == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + permissionName.hashCode();
        result = 31 * result + (permissionDescription != null ? permissionDescription.hashCode() : 0);
        return result;
    }
}
