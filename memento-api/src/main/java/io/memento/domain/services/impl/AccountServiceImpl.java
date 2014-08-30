package io.memento.domain.services.impl;

import io.memento.domain.model.Account;
import io.memento.domain.services.AccountService;
import io.memento.domain.model.IdentityProvider;
import io.memento.infra.repository.user.AccountRepository;
import io.memento.infra.security.oauth.OAuthTokenData;
import io.memento.infra.security.oauth.OAuthTokenStore;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Project: Memento
 * User:    Jérémy Buget
 * Email:   jbuget@agile-spirit.fr
 * Date:    20/08/2014
 */
@Named
@Transactional
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
    @Transactional
    public Account save(Account account) {
        account.setCreationDate(new DateTime());
        return accountRepository.save(account);
    }

    @Override
    @Transactional
    public Account update(Account account) {
        account.setModificationDate(new DateTime());
        return accountRepository.save(account);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        accountRepository.delete(id);
    }


}
