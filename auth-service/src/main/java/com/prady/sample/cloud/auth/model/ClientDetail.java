/**
 *
 */

package com.prady.sample.cloud.auth.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Prady
 */
@Document(collection = "client_detail")
public class ClientDetail {

    private final static String DEFAULT_GRANT_TYPES = "client_credentials,refresh_token";

    @Id
    private String clientId;
    private String clientSecret;
    private Set<String> scope;
    private Set<String> resourceIds;
    private Set<String> authorizedGrantTypes;
    private Set<String> registeredRedirectUris;
    private List<RegisteredService> registeredServices;
    private Integer accessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;

    public ClientDetail() {
        this.authorizedGrantTypes = new HashSet<>(Arrays.asList(DEFAULT_GRANT_TYPES.split(",")));
    }

    public ClientDetail(String clientId, String clientSecret, List<RegisteredService> registeredServices) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.registeredServices = registeredServices;
        this.authorizedGrantTypes = new HashSet<>(Arrays.asList(DEFAULT_GRANT_TYPES.split(",")));
        this.accessTokenValiditySeconds = 1000;
        this.refreshTokenValiditySeconds = 1000;
    }

    /**
     * @return the clientId
     */
    public String getClientId() {
        return this.clientId;
    }

    /**
     * @param clientId the clientId to set
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * @return the clientSecret
     */
    public String getClientSecret() {
        return this.clientSecret;
    }

    /**
     * @param clientSecret the clientSecret to set
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    /**
     * @return the scope
     */
    public Set<String> getScope() {
        return this.scope;
    }

    /**
     * @param scope the scope to set
     */
    public void setScope(Set<String> scope) {
        this.scope = scope;
    }

    /**
     * @return the resourceIds
     */
    public Set<String> getResourceIds() {
        return this.resourceIds;
    }

    /**
     * @param resourceIds the resourceIds to set
     */
    public void setResourceIds(Set<String> resourceIds) {
        this.resourceIds = resourceIds;
    }

    /**
     * @return the authorizedGrantTypes
     */
    public Set<String> getAuthorizedGrantTypes() {
        return this.authorizedGrantTypes;
    }

    /**
     * @param authorizedGrantTypes the authorizedGrantTypes to set
     */
    public void setAuthorizedGrantTypes(Set<String> authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    /**
     * @return the registeredRedirectUris
     */
    public Set<String> getRegisteredRedirectUris() {
        return this.registeredRedirectUris;
    }

    /**
     * @param registeredRedirectUris the registeredRedirectUris to set
     */
    public void setRegisteredRedirectUris(Set<String> registeredRedirectUris) {
        this.registeredRedirectUris = registeredRedirectUris;
    }

    /**
     * @return the registeredServices
     */
    public List<RegisteredService> getRegisteredServices() {
        return this.registeredServices;
    }

    /**
     * @param registeredServices the registeredServices to set
     */
    public void setRegisteredServices(List<RegisteredService> registeredServices) {
        this.registeredServices = registeredServices;
    }

    /**
     * @return the accessTokenValiditySeconds
     */
    public Integer getAccessTokenValiditySeconds() {
        return this.accessTokenValiditySeconds;
    }

    /**
     * @param accessTokenValiditySeconds the accessTokenValiditySeconds to set
     */
    public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    /**
     * @return the refreshTokenValiditySeconds
     */
    public Integer getRefreshTokenValiditySeconds() {
        return this.refreshTokenValiditySeconds;
    }

    /**
     * @param refreshTokenValiditySeconds the refreshTokenValiditySeconds to set
     */
    public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

}
