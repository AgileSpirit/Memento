package io.memento.infra.security.oauth;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;

/**
 * Project: Memento
 * User:    Jérémy Buget
 * Email:   jbuget@agile-spirit.fr
 * Date:    25/08/2014
 */
@Entity
public class OAuthTokenData extends AbstractPersistable<Long> {

    /*
     * ATTRIBUTES
     */

    private String access_token;
    private String id_token;
    private Long expires_in;

    /*
     * GETTERS & SETTERS
     */

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getId_token() {
        return id_token;
    }

    public void setId_token(String id_token) {
        this.id_token = id_token;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

}
