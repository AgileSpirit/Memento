package io.memento.application.response;

import io.memento.domain.model.Account;

/**
 * Project: Memento
 * User:    Jérémy Buget
 * Email:   jbuget@agile-spirit.fr
 * Date:    21/08/2014
 */
public class ConnectionResponse {

    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
