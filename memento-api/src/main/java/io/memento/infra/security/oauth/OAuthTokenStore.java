package io.memento.infra.security.oauth;

import io.memento.domain.model.Account;

/**
 * Project: Memento
 * User:    Jérémy Buget
 * Email:   jbuget@agile-spirit.fr
 * Date:    27/08/2014
 */
public interface OAuthTokenStore {

    void remove(String accessToken);

    void remove(OAuthTokenData tokenData);

    OAuthTokenData getTokenData(String accessToken);

    Account getAccount(String accessToken);

    Account getAccount(OAuthTokenData tokenData);

    OAuthTokenData generateAndStoreTokenForAccount(Account account);
}
