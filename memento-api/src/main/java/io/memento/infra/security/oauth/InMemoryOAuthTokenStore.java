package io.memento.infra.security.oauth;

import io.memento.domain.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Project: Memento
 * User:    Jérémy Buget
 * Email:   jbuget@agile-spirit.fr
 * Date:    27/08/2014
 */
@Named
public class InMemoryOAuthTokenStore implements OAuthTokenStore {

    private static Logger LOGGER = LoggerFactory.getLogger(InMemoryOAuthTokenStore.class);

    private final Map<String, OAuthTokenData> tokens = new HashMap<>();
    private final Map<OAuthTokenData, Account> accounts = new HashMap<>();

    @Override
    public OAuthTokenData getTokenData(String accessToken) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Get token data for access token " + accessToken);
        }
        OAuthTokenData tokenData = tokens.get(accessToken);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("tokenData = " + tokenData);
        }

        return tokenData;
    }

    @Override
    public Account getAccount(String accessToken) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Get account for access token " + accessToken);
        }

        OAuthTokenData tokenData = tokens.get(accessToken);

        return getAccount(tokenData);
    }

    @Override
    public Account getAccount(OAuthTokenData tokenData) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Get account for token data " + tokenData);
        }

        Account account = accounts.get(tokenData);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("The account " + account + " was found");
        }

        return account;
    }

    @Override
    public OAuthTokenData generateAndStoreTokenForAccount(Account account) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Generate and store token data for account " + account);
        }

        String accessToken = generateAccessToken();
        OAuthTokenData token = buildTokenData(accessToken);
        storeTokenDataForAccount(token, account);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Generated token data " + token);
        }

        return token;
    }

    private String generateAccessToken() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Generating an access token");
        }

        String accessToken = UUID.randomUUID().toString();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("The access token " + accessToken + " was generated");
        }

        return accessToken;
    }

    private OAuthTokenData buildTokenData(String accessToken) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Building token data from access token");
        }

        OAuthTokenData token = new OAuthTokenData();
        token.setAccess_token(accessToken);
        // TODO set token expiration

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("A token data was generated for access token " + accessToken);
        }

        return token;
    }

    private void storeTokenDataForAccount(OAuthTokenData tokenData, Account account) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Storing the token data (access token = " + tokenData.getAccess_token() + ")");
        }

        tokens.put(tokenData.getAccess_token(), tokenData);
        accounts.put(tokenData, account);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("The token data was stored");
        }
    }

    @Override
    public void remove(String accessToken) {
        OAuthTokenData tokenData = tokens.get(accessToken);
        if (tokenData == null) {
            LOGGER.error("Could not find token data for access token " + accessToken);
            throw new RuntimeException();
        }

        remove(tokenData);
    }

    @Override
    public void remove(OAuthTokenData tokenData) {
        tokens.remove(tokenData.getAccess_token());
        accounts.remove(tokenData);
    }

}
