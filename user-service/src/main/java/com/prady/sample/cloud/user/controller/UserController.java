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
import com.prady.sample.cloud.common.dto.user.UserAccountDTO;
import com.prady.sample.cloud.user.service.UserService;

/**
 * @author Prady
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @Audit(module = "USER_SERVICE", action = "GET_ALL_USER")
    public List<UserAccountDTO> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    @Audit(module = "USER_SERVICE", action = "GET_USER")
    public ResponseEntity<UserAccountDTO> getUser(@PathVariable("id") String id) {
        UserAccountDTO user = userService.get(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/users")
    @Audit(module = "USER_SERVICE", action = "CREATE_USER")
    public ResponseEntity<UserAccountDTO> createUser(@RequestBody UserAccountDTO userAccountDTO) {
        userService.create(userAccountDTO);
        return new ResponseEntity<>(userAccountDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    @Audit(module = "USER_SERVICE", action = "DELETE_USER")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        userService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    @Audit(module = "USER_SERVICE", action = "UPDATE_USER")
    public ResponseEntity<UserAccountDTO> updateUser(@PathVariable String id, @RequestBody UserAccountDTO userAccountDTO) {
        userAccountDTO = userService.update(id, userAccountDTO);
        return new ResponseEntity<>(userAccountDTO, HttpStatus.OK);
    }
}
