/**
 * 
 */
package com.prady.sample.clould.gateway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prady.sample.cloud.model.user.User;
import com.prady.sample.clould.gateway.service.GatewayService;

/**
 * @author Prady
 *
 */
@RestController
public class GatewayController {

	@Autowired
	private GatewayService gatewayService;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> getUsers() {
		return gatewayService.getUsers();
	}
}
