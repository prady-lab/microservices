/**
 *
 */

package com.prady.sample.cloud.auth.listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.prady.sample.cloud.auth.model.ClientDetail;
import com.prady.sample.cloud.auth.model.RegisteredService;
import com.prady.sample.cloud.auth.service.ClientDetailService;

/**
 * @author Prady
 */
@Component
public class AuthApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private ClientDetailService clientDetailService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        ClientDetail adminClientDetail = new ClientDetail("AdminClient", "AdminSecert",
                new ArrayList<>(Arrays.asList(new RegisteredService("user-service", "user-service"))));

        adminClientDetail.setScope(new HashSet<>(Arrays.asList("server", "user-service")));

        ClientDetail testClientDetail = new ClientDetail("TestClient", "TestSecert",
                new ArrayList<>(Arrays.asList(new RegisteredService("user-service", "user-service"))));

        testClientDetail.setScope(new HashSet<>(Arrays.asList("server")));

        this.clientDetailService.createIfNotPresent(adminClientDetail);
        this.clientDetailService.createIfNotPresent(testClientDetail);
    }

    /**
     * @param securityClientDetailsService the securityClientDetailsService to set
     */
    @Autowired
    public void setClientDetailService(ClientDetailService ClientDetailService) {
        this.clientDetailService = ClientDetailService;
    }

}