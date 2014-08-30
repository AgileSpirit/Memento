package io.memento.infra.security;

import io.memento.application.exceptions.Http500InternalServerError;
import io.memento.domain.model.Account;
import io.memento.infra.security.oauth.OAuthTokenData;
import io.memento.infra.security.oauth.OAuthTokenStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 * Project: Memento
 * User:    Jérémy Buget
 * Email:   jbuget@agile-spirit.fr
 * Date:    30/08/2014
 */
@Named
public abstract class HttpHeadersAccessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpHeadersAccessor.class);

    @Inject
    protected OAuthTokenStore tokenStore;

    protected final Account getAccount(HttpServletRequest request) {
        String accessToken = extractAccessToken(request);
        Account account  = tokenStore.getAccount(accessToken);
        return account;
    }

    protected final OAuthTokenData retrieveTokenData(String accessToken) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Retrieving token data from access token " + accessToken);
        }

        OAuthTokenData token = tokenStore.getTokenData(accessToken);
        if (token == null) {
            LOGGER.error("No token was found the the given access token " + accessToken);
            throw new Http500InternalServerError();
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("The token data was retrieved for access token " + accessToken);
        }

        return token;
    }

    protected final String extractAccessToken(HttpServletRequest request) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Extract access token from request");
        }

        String accessToken = null;

        // Request headers based method
        String bearer = request.getHeader("Authorization");
        accessToken = bearer.replace("Bearer", "").trim();

        // The request is secured and required authentication but the access token was not set in the HTTP request headers
        if (accessToken == null) {
            LOGGER.error("No request header 'Authorization' was set");
            throw new Http500InternalServerError();
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("The access token " + accessToken + " was extracted");
        }

        return accessToken;
    }

}
