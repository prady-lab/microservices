/**
 *
 */

package com.prady.sample.cloud.gateway.client;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prady.sample.cloud.model.user.User;

/**
 * @author Prady
 */
// @FeignClient(name = "user-service")
public interface UserServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/users", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<User> getUsers();

}
