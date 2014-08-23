package io.memento.domain.model;

import io.memento.infra.authentication.IdentityProvider;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Project: Memento
 * User:    Jérémy Buget
 * Email:   jbuget@agile-spirit.fr
 * Date:    20/08/2014
 */
@Entity
public class Account extends PersistableEntity {

    /*
     * ATTRIBUTES
     */

    /**
     * The ID of the user provided by the identity provider (Google, Facebook, etc.)
     */
    @Column(nullable = false)
    private String clientId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private IdentityProvider provider;

    @Column(nullable = true)
    private String firstName;

    @Column(nullable = true)
    private String lastName;

    /*
     * GETTERS & SETTERS
     */

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public IdentityProvider getProvider() {
        return provider;
    }

    public void setProvider(IdentityProvider provider) {
        this.provider = provider;
    }
}
