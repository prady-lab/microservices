/**
 *
 */

package com.prady.sample.cloud.auth.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientAlreadyExistsException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.prady.sample.cloud.auth.mapper.ClientDetailsMapper;
import com.prady.sample.cloud.auth.model.ClientDetail;

/**
 * @author Prady
 */
@Component
public class SecurityClientDetailsService implements ClientDetailsService, ClientRegistrationService {

    @Autowired
    private ClientDetailService clientDetailsService;

    @Autowired
    private ClientDetailsMapper clientDetailsMapper;

    /*
     * (non-Javadoc)
     * @see org.springframework.security.oauth2.provider.ClientRegistrationService#addClientDetails(org.springframework.security.oauth2
     * .provider.ClientDetails)
     */
    @Override
    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {

        ClientDetail clientDetail = clientDetailsMapper.toClientDetail(clientDetails);

        clientDetailsService.create(clientDetail);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.security.oauth2.provider.ClientRegistrationService#updateClientDetails(org.springframework.security.
     * oauth2.provider.ClientDetails)
     */
    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {

    }

    /*
     * (non-Javadoc)
     * @see org.springframework.security.oauth2.provider.ClientRegistrationService#updateClientSecret(java.lang.String, java.lang.String)
     */
    @Override
    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {

    }

    /*
     * (non-Javadoc)
     * @see org.springframework.security.oauth2.provider.ClientRegistrationService#removeClientDetails(java.lang.String)
     */
    @Override
    public void removeClientDetails(String clientId) throws NoSuchClientException {

    }

    /*
     * (non-Javadoc)
     * @see org.springframework.security.oauth2.provider.ClientRegistrationService#listClientDetails()
     */
    @Override
    public List<ClientDetails> listClientDetails() {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.security.oauth2.provider.ClientDetailsService#loadClientByClientId(java.lang.String)
     */
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        ClientDetail client = clientDetailsService.getClient(clientId);
        if (null == client) {
            throw new NoSuchClientException("No client with requested id: " + clientId);
        }

        BaseClientDetails details = new BaseClientDetails(client.getClientId(),
                StringUtils.collectionToCommaDelimitedString(client.getResourceIds()),
                StringUtils.collectionToCommaDelimitedString(client.getScope()),
                StringUtils.collectionToCommaDelimitedString(client.getAuthorizedGrantTypes()),
                StringUtils.collectionToCommaDelimitedString(
                        client.getRegisteredServices().stream().map(p -> p.getServiceId()).collect(Collectors.toSet())),
                StringUtils.collectionToCommaDelimitedString(client.getResourceIds()));

        details.setClientSecret(client.getClientSecret());
        details.setAccessTokenValiditySeconds(client.getAccessTokenValiditySeconds());
        details.setRefreshTokenValiditySeconds(client.getRefreshTokenValiditySeconds());

        String scopes = StringUtils.collectionToCommaDelimitedString(client.getScope());
        if (scopes != null) {
            details.setAutoApproveScopes(StringUtils.commaDelimitedListToSet(scopes));
        }
        return details;
    }

}
