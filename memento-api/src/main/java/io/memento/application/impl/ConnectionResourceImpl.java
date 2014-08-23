package io.memento.application.impl;

import io.memento.application.ConnectionResource;
import io.memento.application.request.ConnectionRequest;
import io.memento.application.response.ConnectionResponse;
import io.memento.domain.model.Account;
import io.memento.domain.model.EntityFactory;
import io.memento.domain.services.AccountService;
import io.memento.infra.authentication.IdentityProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

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
    private AccountService accountService;

    @Override
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ConnectionResponse login(@RequestBody ConnectionRequest request) {
        // 1. Extract client_id
        String clientId = request.getClient_id();
        IdentityProvider provider = IdentityProvider.GOOGLE;

        // 2. Retrieve associated account if it exists
        LOGGER.info("Try to retrieve account associated to client ID '" + clientId + "' and provider '" + provider + "'");
        Account account = accountService.getAccount(clientId, provider);

        // 3. If not, create and associate a new user account
        if (account == null) {
            LOGGER.info("There is currently no account associated to client ID '" + clientId + "' and provider '" + provider + "'; create one...");
            Account newAccount = EntityFactory.newAccount(clientId, provider);
            account = accountService.save(newAccount);
            LOGGER.info("An account was created for client ID '" + clientId + "' and provider '" + provider + "'");
        } else {
            LOGGER.info("The account with ID '" + account.getId() + "' was found for client ID '" + clientId + "' and provider '" + provider + "'");
        }

        // 4. Build response
        ConnectionResponse response = new ConnectionResponse();
        response.setAccount(account);
        return response;
    }

    @Override
    @RequestMapping(value ="/logout", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ConnectionResponse logout(@RequestBody ConnectionRequest request) {
        // TODO
        return null;
    }
}
