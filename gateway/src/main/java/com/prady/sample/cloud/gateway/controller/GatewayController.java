/**
 *
 */

package com.prady.sample.cloud.gateway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prady.sample.cloud.gateway.service.GatewayService;
import com.prady.sample.cloud.model.user.User;

/**
 * @author Prady
 */
// @RestController
public class GatewayController {

    @Autowired
    private GatewayService gatewayService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getUsers() {
        return this.gatewayService.getUsers();
    }
}
