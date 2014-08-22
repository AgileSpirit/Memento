package io.memento.application.impl;

import io.memento.application.AccountResource;
import io.memento.domain.model.Account;
import io.memento.domain.services.AccountService;
import io.memento.infra.authentication.IdentityProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;

/**
 * Project: Memento
 * User:    Jérémy Buget
 * Email:   jbuget@agile-spirit.fr
 * Date:    23/08/2014
 */
@Controller
@RequestMapping("/api/accounts")
public class AccountResourceImpl implements AccountResource {

    @Inject
    private AccountService accountService;

    @Override
    @RequestMapping(value = "/{cid}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Account getAccount(@PathVariable("cid") String clientId) {
        Account account = accountService.getAccount(clientId, IdentityProvider.GOOGLE);
        return account;
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Account> listAllAccounts() {
        List<Account> accounts = accountService.list();
        return accounts;
    }
}
