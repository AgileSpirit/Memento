package io.memento.domain.services.impl;

import static io.memento.infra.authentication.IdentityProvider.GOOGLE;
import static io.memento.infra.authentication.IdentityProvider.TWITTER;
import io.memento.domain.model.Account;
import io.memento.infra.authentication.IdentityProvider;
import io.memento.infra.repository.user.AccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Project: Memento
 * User:    Jérémy Buget
 * Email:   jbuget@agile-spirit.fr
 * Date:    20/08/2014
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl userService;

    @Test
    public void testGetAccount() throws Exception {
        // Given
        Mockito.when(accountRepository.findByClientIdAndProvider(Mockito.anyString(), Mockito.any(IdentityProvider.class))).thenReturn(Account.newAccount("user@test.com", GOOGLE));

        // When
        Account actual = userService.getAccount("1234-ABCD", GOOGLE);

        // Then
        Assertions.assertThat(actual).isNotNull();
    }

    @Test
    public void testSave() throws Exception {
        // Given
        Account account = Account.newAccount("user@test.com", GOOGLE);
        account.setCreationDate(null);
        Mockito.when(accountRepository.save(account)).thenReturn(account);

        // When
        Account actual = userService.save(account);

        // Then
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(account.getCreationDate()).isNotNull();
    }

    @Test
    public void testUpdate() throws Exception {
        // Given
        Account account = Account.newAccount("user@test.com", GOOGLE);
        account.setModificationDate(null);
        Mockito.when(accountRepository.save(account)).thenReturn(account);

        // When
        Account actual = userService.update(account);

        // Then
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(account.getModificationDate()).isNotNull();
    }

    @Test
    public void testDelete() throws Exception {
        // TODO
    }

}
