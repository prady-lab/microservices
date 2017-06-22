/**
 *
 */

package com.prady.sample.cloud.gateway.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.prady.sample.cloud.gateway.client.UserServiceClient;
import com.prady.sample.cloud.model.user.User;

/**
 * @author Prady
 */
// @Service
public class GatewayServiceImpl implements GatewayService {

    private static final Logger log = LoggerFactory.getLogger(GatewayServiceImpl.class);

    @Autowired
    private UserServiceClient userServiceClient;

    /*
     * (non-Javadoc)
     * @see com.prady.sample.clould.gateway.service.GatewayService#getUsers()
     */
    @Override
    public List<User> getUsers() {
        log.info("Invoking getUsers API");
        return this.userServiceClient.getUsers();
    }

}
