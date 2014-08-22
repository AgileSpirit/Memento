package io.memento.domain.services.impl;

import io.memento.domain.model.Account;
import io.memento.domain.services.AccountService;
import io.memento.infra.authentication.IdentityProvider;
import io.memento.infra.repository.user.AccountRepository;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Project: Memento
 * User:    Jérémy Buget
 * Email:   jbuget@agile-spirit.fr
 * Date:    20/08/2014
 */
@Named
public class AccountServiceImpl implements AccountService {

    private final static Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Inject
    private AccountRepository accountRepository;

    @Override
    public List<Account> list() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccount(String clientId, IdentityProvider provider) {
        return accountRepository.findByClientIdAndProvider(clientId, provider);
    }

    @Override
    public Account save(Account account) {
        account.setCreationDate(new DateTime());
        return accountRepository.save(account);
    }

    @Override
    public Account update(Account account) {
        account.setModificationDate(new DateTime());
        return accountRepository.save(account);
    }

    @Override
    public void delete(Long id) {
        accountRepository.delete(id);
    }


}
