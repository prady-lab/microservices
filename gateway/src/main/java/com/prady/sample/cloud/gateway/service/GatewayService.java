/**
 *
 */

package com.prady.sample.cloud.gateway.service;

import java.util.List;

import com.prady.sample.cloud.model.user.User;

/**
 * @author Prady
 */
public interface GatewayService {

    /**
     * @return
     */
    List<User> getUsers();

}
