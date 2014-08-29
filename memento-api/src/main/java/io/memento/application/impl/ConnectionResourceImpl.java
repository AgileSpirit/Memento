package io.memento.application.impl;

import io.memento.application.ConnectionResource;
import io.memento.application.request.ConnectionRequest;
import io.memento.application.response.ConnectionResponse;
import io.memento.domain.model.Account;
import io.memento.domain.model.EntityFactory;
import io.memento.domain.services.AccountService;
import io.memento.domain.model.IdentityProvider;
import io.memento.infra.security.oauth.OAuthTokenData;
import io.memento.infra.security.oauth.OAuthTokenStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.UUID;

/**
 * Project: Memento
 * User:    Jérémy Buget
 * Email:   jbuget@agile-spirit.fr
 * Date:    21/08/2014
 */
@Controller
@RequestMapping("/api/connection")
public class ConnectionResourceImpl implements ConnectionResource {

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionResourceImpl.class);

    @Inject
    private OAuthTokenStore tokenStore;

    @Inject
    private AccountService accountService;

    @Override
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ConnectionResponse login(@RequestBody ConnectionRequest request) {
        // Extract client_id
        String clientId = request.getClient_id();
        IdentityProvider provider = IdentityProvider.GOOGLE;

        // Retrieve associated account if it exists
        LOGGER.info("Try to retrieve account associated to client ID '" + clientId + "' and provider '" + provider + "'");
        Account account = accountService.getAccount(clientId, provider);

        // If not, create and associate a new user account
        if (account == null) {
            LOGGER.info("There is currently no account associated to client ID '" + clientId + "' and provider '" + provider + "'; create one...");
            Account newAccount = EntityFactory.newAccount(clientId, provider);
            account = accountService.save(newAccount);
            LOGGER.info("An account was created for client ID '" + clientId + "' and provider '" + provider + "'");
        } else {
            LOGGER.info("The account with ID '" + account.getId() + "' was found for client ID '" + clientId + "' and provider '" + provider + "'");
        }

        // Generate OAuth access_token
        String accessToken = generateAccessToken();
        OAuthTokenData token = buildTokenData(accessToken);
        storeTokenData(token);

        // Build response
        ConnectionResponse response = new ConnectionResponse();
        response.setAccount(account);
        response.setAccessToken(accessToken);
        return response;
    }

    private String generateAccessToken() {
        LOGGER.debug("Generating an access_token");
        String accessToken = UUID.randomUUID().toString();
        LOGGER.debug("The access_token " + accessToken + " was generated");
        return accessToken;
    }

    private OAuthTokenData buildTokenData(String accessToken) {
        LOGGER.debug("Building token data from access_token");
        OAuthTokenData token = new OAuthTokenData();
        token.setAccess_token(accessToken);
        // TODO set token expiration
        LOGGER.debug("A token data was generated for access_token " + accessToken);
        return token;
    }

    private OAuthTokenData storeTokenData(OAuthTokenData token) {
        LOGGER.debug("Storing the token data (access_token = " + token.getAccess_token() + ")");
        OAuthTokenData tokenData = tokenStore.add(token);
        LOGGER.debug("The token data was stored");
        return tokenData;
    }

    @Override
    @RequestMapping(value ="/logout", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ConnectionResponse logout(@RequestBody ConnectionRequest request) {
        // TODO
        return null;
    }
}
