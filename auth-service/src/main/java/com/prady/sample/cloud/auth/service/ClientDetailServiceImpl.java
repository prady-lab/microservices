/**
 *
 */

package com.prady.sample.cloud.auth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.prady.sample.cloud.auth.model.ClientDetail;
import com.prady.sample.cloud.auth.repository.ClientDetailRepository;

/**
 * @author Prady
 */
@Service
public class ClientDetailServiceImpl implements ClientDetailService {

    private static final Logger log = LoggerFactory.getLogger(ClientDetailServiceImpl.class);

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private ClientDetailRepository repository;

    /*
     * (non-Javadoc)
     * @see com.prady.sample.clould.auth.service.ClientDetailService#getClient(java.lang.String)
     */
    @Override
    public ClientDetail getClient(String clientId) {
        return this.repository.findOne(clientId);
    }

    /*
     * (non-Javadoc)
     * @see com.prady.sample.clould.auth.service.ClientDetailsService#create(com.prady.sample.clould.auth.model.ClientDetail)
     */
    @Override
    public void create(ClientDetail client) {
        log.debug("Creating Client {} ", client.getClientId());

        ClientDetail existing = this.repository.findOne(client.getClientId());
        Assert.isNull(existing, "Client already exists " + client.getClientId());

        String hash = encoder.encode(client.getClientSecret());
        client.setClientSecret(hash);

        this.repository.save(client);

        log.info("Client '{}' created Successfully", client.getClientId());
    }

    @Override
    public void createIfNotPresent(ClientDetail client) {

        ClientDetail existing = this.repository.findOne(client.getClientId());
        if (null != existing) {
            log.info("Client '{}' already exists", client.getClientId());
        } else {
            String hash = encoder.encode(client.getClientSecret());
            client.setClientSecret(hash);

            this.repository.save(client);

            log.info("Client '{}' created Successfully ", client.getClientId());
        }
    }

}
