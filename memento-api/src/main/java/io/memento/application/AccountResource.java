package io.memento.application;

import io.memento.domain.model.Account;

import java.util.List;

/**
 * Project: Memento
 * User:    Jérémy Buget
 * Email:   jbuget@agile-spirit.fr
 * Date:    23/08/2014
 */
public interface AccountResource {
    
    Account getAccount(String clientId);

    List<Account> listAllAccounts();

}
