/**
 *
 */

package com.prady.sample.cloud.user.listener;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.prady.sample.cloud.common.dto.user.RoleDTO;
import com.prady.sample.cloud.common.dto.user.UserAccountDTO;
import com.prady.sample.cloud.user.service.RoleService;
import com.prady.sample.cloud.user.service.UserService;

/**
 * @author Prady
 */
@Component
public class UserAccountPopulateListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        roleService.createPermissionIfNotPresent("PR1", "Permission 1");
        roleService.createPermissionIfNotPresent("PR2", "Permission 1");
        roleService.createPermissionIfNotPresent("PR3", "Permission 1");
        roleService.createPermissionIfNotPresent("PR4", "Permission 1");
        roleService.createPermissionIfNotPresent("PR5", "Permission 1");

        roleService.refreshPermissionCache();

        UserAccountDTO userAccountDTO = new UserAccountDTO();
        userAccountDTO.setUserLoginName("prady");
        userAccountDTO.setFirstName("Prady");
        userAccountDTO.setLastName("B");
        userAccountDTO.setPassword("password");

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleName("ADMIN");
        roleDTO.setRoleDescription("Admin Role");
        roleDTO.setPermissions(Arrays.asList(new String[] { "PR1", "PR2", "PR3" }));

        roleService.createIfNotPresent(roleDTO);
        RoleDTO savedRoleDTO = roleService.getRolebyRoleName(roleDTO.getRoleName());

        userAccountDTO.setRoles(Arrays.asList(new RoleDTO[] { savedRoleDTO }));

        userService.createIfNotPresent(userAccountDTO);
    }

}