package io.memento.infra.security.oauth;

import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

/**
 * Project: Memento
 * User:    Jérémy Buget
 * Email:   jbuget@agile-spirit.fr
 * Date:    27/08/2014
 */
@Named
public class InMemoryOAuthTokenStore implements OAuthTokenStore {

    private final Map<String, OAuthTokenData> tokens = new HashMap<>();

    /**
     * @param accessToken
     * @return <code>null</code> if the token was not found
     */
    @Override
    public OAuthTokenData get(String accessToken) {
        OAuthTokenData token = tokens.get(accessToken);
        return token;
    }

    @Override
    public OAuthTokenData add(OAuthTokenData token) {
        tokens.put(token.getAccess_token(), token);
        return token;
    }

    @Override
    public void remove(String accessToken) {
        tokens.remove(accessToken);
    }

}
