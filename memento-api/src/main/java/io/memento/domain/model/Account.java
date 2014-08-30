package io.memento.domain.model;

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
     * The user ID generated during the very first connection login. According to JWT specifications, it is in fact the
     * concatenation of the 'sub' (or Subject, a.k.a. the owner unique ID on provider system) and 'iss' (or Issuer,
     * a.k.a. the provider system name) fields with a '-' separator ; Ex: "117024677747885647904-accounts.google.com"
     */
    @Column(nullable = false)
    private String userId;

    /**
     * The user first name
     */
    @Column(nullable = true)
    private String firstName;

    /**
     * The user last name
     */
    @Column(nullable = true)
    private String lastName;

    /*
     * GETTERS & SETTERS
     */

    public String getUserId() {
        return userId;
    }

    public void setUserId(String clientId) {
        this.userId = clientId;
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

}
