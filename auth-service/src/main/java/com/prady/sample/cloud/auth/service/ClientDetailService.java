/**
 *
 */

package com.prady.sample.cloud.auth.service;

import com.prady.sample.cloud.auth.model.ClientDetail;

/**
 * @author Prady
 */
public interface ClientDetailService {

    /**
     * @param clientId
     * @return
     */
    ClientDetail getClient(String clientId);

    /**
     * @param client
     */
    void create(ClientDetail client);

    /**
     * @param client
     */
    void createIfNotPresent(ClientDetail client);
}
