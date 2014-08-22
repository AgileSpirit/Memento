package io.memento.infra.authentication;

/**
 * Project: Memento
 * User:    Jérémy Buget
 * Email:   jbuget@agile-spirit.fr
 * Date:    21/08/2014
 */
public class GoogleAuthenticationTokenData {

    public static String kind = "memento#tokendata";

    /**
     * Google access token used to authorize requests to Google.
     */
    public String access_token;

    /**
     * Google refresh token used to get new access tokens when needed.
     */
    public String refresh_token;

    /**
     * Authorization code used to exchange for an access/refresh token pair.
     */
    public String code;

    /**
     * Identity token for this user.
     */
    public String id_token;

    /**
     * When the access token expires.
     */
    public Long expires_at;

    /**
     * How long until the access token expires.
     */
    public Long expires_in;

}
