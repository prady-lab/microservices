/**
 *
 */

package com.prady.sample.cloud.common.dto.user;

/**
 * @author Prady
 */
public class PermissionDTO {

    private String id;
    private String permissionName;
    private String permissionDescription;

    /**
     *
     */
    public PermissionDTO() {
        super();
    }

    /**
     * @param id
     * @param permissionName
     * @param permissionDescription
     */
    public PermissionDTO(String id, String permissionName, String permissionDescription) {
        super();
        this.id = id;
        this.permissionName = permissionName;
        this.permissionDescription = permissionDescription;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the permissionName
     */
    public String getPermissionName() {
        return permissionName;
    }

    /**
     * @param permissionName the permissionName to set
     */
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    /**
     * @return the permissionDescription
     */
    public String getPermissionDescription() {
        return permissionDescription;
    }

    /**
     * @param permissionDescription the permissionDescription to set
     */
    public void setPermissionDescription(String permissionDescription) {
        this.permissionDescription = permissionDescription;
    }
}
