package io.memento.application.impl;

import io.memento.application.request.ConnectionRequest;
import io.memento.application.response.ConnectionResponse;
import io.memento.domain.model.Account;
import io.memento.domain.model.EntityFactory;
import io.memento.domain.services.AccountService;
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
 * Date:    22/08/2014
 */
@RunWith(MockitoJUnitRunner.class)
public class ConnectionResourceImplTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private ConnectionResourceImpl resource;

    @Test
    public void testLogin_withExistingAccount_shouldOnlyReturnTheAccount() throws Exception {
        // Given
        ConnectionRequest request = new ConnectionRequest();
        request.setGoogleTokenId("existing_client_id");
        Account account = EntityFactory.newAccount("existing_client_id");
        Mockito.when(accountService.getAccount(Mockito.anyString())).thenReturn(account);
        Mockito.when(accountService.save(Mockito.any(Account.class))).thenReturn(account);

        // When
        ConnectionResponse response = resource.login(request);

        // Then
        Mockito.verify(accountService, Mockito.times(1)).getAccount("existing_client_id");
        Mockito.verify(accountService, Mockito.times(0)).save(Mockito.any(Account.class));
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getAccount()).isNotNull();
        Assertions.assertThat(response.getAccount().getUserId()).isEqualTo("existing_client_id");
    }

    @Test
    public void testLogin_withUnknownAccount_shouldCreateAndReturnTheAccount() throws Exception {
        // Given
        ConnectionRequest request = new ConnectionRequest();
        request.setGoogleTokenId("new_client_id");
        Account account = EntityFactory.newAccount("new_client_id");
        Mockito.when(accountService.getAccount(Mockito.anyString())).thenReturn(null);
        Mockito.when(accountService.save(Mockito.any(Account.class))).thenReturn(account);

        // When
        ConnectionResponse response = resource.login(request);

        // Then
        Mockito.verify(accountService, Mockito.times(1)).getAccount("new_client_id");
        Mockito.verify(accountService, Mockito.times(1)).save(Mockito.any(Account.class));
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getAccount()).isNotNull();
        Assertions.assertThat(response.getAccount().getUserId()).isEqualTo("new_client_id");

    }

}
