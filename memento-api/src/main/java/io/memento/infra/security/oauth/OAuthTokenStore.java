package io.memento.infra.security.oauth;

/**
 * Project: Memento
 * User:    Jérémy Buget
 * Email:   jbuget@agile-spirit.fr
 * Date:    27/08/2014
 */
public interface OAuthTokenStore {

    OAuthTokenData get(String accessToken);

    OAuthTokenData add(OAuthTokenData token);

    void remove(String accessToken);

}
