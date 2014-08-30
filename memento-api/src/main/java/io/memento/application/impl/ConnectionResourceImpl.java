package io.memento.application.impl;

import com.google.gson.JsonObject;
import io.memento.application.ConnectionResource;
import io.memento.application.request.ConnectionRequest;
import io.memento.application.response.ConnectionResponse;
import io.memento.domain.model.Account;
import io.memento.domain.model.EntityFactory;
import io.memento.domain.services.AccountService;
import io.memento.infra.security.jwt.IgnoreAudience;
import io.memento.infra.security.oauth.OAuthTokenData;
import io.memento.infra.security.oauth.OAuthTokenStore;
import net.oauth.jsontoken.Checker;
import net.oauth.jsontoken.JsonToken;
import net.oauth.jsontoken.JsonTokenParser;
import net.oauth.jsontoken.discovery.VerifierProviders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        String tokenId = request.getGoogleTokenId();
        String userId = generateUserId(tokenId);

        // Retrieve associated account if it exists
        LOGGER.info("Retrieving account associated to user ID '" + userId + "'");
        Account account = accountService.getAccount(userId);

        // If not, create and associate a new user account
        if (account == null) {
            LOGGER.info("There is currently no account associated to client ID '" + userId + "'");
            Account newAccount = EntityFactory.newAccount(userId);
            account = accountService.save(newAccount);
            LOGGER.info("An account was created for client ID '" + userId + "'");
        } else {
            LOGGER.info("The account with ID '" + account.getId() + "' was found for client ID '" + userId + "'");
        }

        // Generate OAuth access_token
        OAuthTokenData tokenData = tokenStore.generateAndStoreTokenForAccount(account);

        // Build response
        ConnectionResponse response = new ConnectionResponse();
        response.setAccount(account);
        response.setAccessToken(tokenData.getAccess_token());
        return response;
    }

    @Override
    @RequestMapping(value ="/logout", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public HttpServletResponse logout(HttpServletRequest request) {
        return null;
    }

    private String generateUserId(String tokenString) {
        // Prepare
        VerifierProviders verifierProviders = new VerifierProviders();
        Checker checker = new IgnoreAudience();
        JsonTokenParser parser = new JsonTokenParser(verifierProviders, checker);
        JsonToken jsonToken = parser.deserialize(tokenString);
        JsonObject payload = jsonToken.getPayloadAsJsonObject();

        // Perform
        String iss = payload.get("iss").getAsString();
        String sub = payload.get("sub").getAsString();

        String userId = sub + "-" + iss;
        return userId;
    }

}
