/**
 *
 */

package com.prady.sample.cloud.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prady.sample.cloud.common.audit.Audit;
import com.prady.sample.cloud.common.dto.user.RoleDTO;
import com.prady.sample.cloud.user.service.RoleService;

/**
 * @author Prady
 */
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/roles")
    @Audit(module = "ROLE_SERVICE", action = "GET_ALL_ROLES")
    public List<RoleDTO> getRoles() {
        return roleService.getRoles();
    }

    @GetMapping("/roles/{id}")
    @Audit(module = "ROLE_SERVICE", action = "GET_ROLE")
    public ResponseEntity<RoleDTO> getRole(@PathVariable("id") String id) {
        RoleDTO role = roleService.get(id);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PostMapping(value = "/roles")
    @Audit(module = "ROLE_SERVICE", action = "CREATE_ROLE")
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO) {
        roleService.create(roleDTO);
        return new ResponseEntity<>(roleDTO, HttpStatus.OK);
    }

    @DeleteMapping("/roles/{id}")
    @Audit(module = "ROLE_SERVICE", action = "DELETE_ROLE")
    public ResponseEntity<String> deleteRole(@PathVariable String id) {
        roleService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping("/roles/{id}")
    @Audit(module = "ROLE_SERVICE", action = "UPDATE_ROLE")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable String id, @RequestBody RoleDTO roleDTO) {
        roleDTO = roleService.update(id, roleDTO);
        return new ResponseEntity<>(roleDTO, HttpStatus.OK);
    }
}
