/**
 * 
 */
package com.prady.sample.clould.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prady.sample.cloud.model.user.User;
import com.prady.sample.clould.user.service.UserService;

/**
 * @author Prady
 *
 */
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> getUsers() {
		return userService.getUsers();
	}
}
