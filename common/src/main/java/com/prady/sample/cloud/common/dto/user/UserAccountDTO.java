
package com.prady.sample.cloud.common.dto.user;

import java.util.ArrayList;
import java.util.List;

import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

/**
 * @author Prady
 */
public class UserAccountDTO {

    private String id;

    @NotNull(message = "User Login name cannot be null")
    @NotEmpty(message = "User Login name cannot be null")
    private String userLoginName;

    private String password;

    @NotNull(message = "First name cannot be null")
    @NotEmpty(message = "First Login name cannot be null")
    private String firstName;

    @NotNull(message = "Last Login name cannot be null")
    @NotEmpty(message = "Last Login name cannot be null")
    private String lastName;

    private String status;
    private Boolean enabled;

    private List<RoleDTO> roles = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserLoginName() {
        return userLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }
}
