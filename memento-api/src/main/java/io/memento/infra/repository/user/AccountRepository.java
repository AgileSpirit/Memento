package io.memento.infra.repository.user;

import io.memento.domain.model.Account;
import io.memento.domain.model.IdentityProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Project: Memento
 * User:    Jérémy Buget
 * Email:   jbuget@agile-spirit.fr
 * Date:    20/08/2014
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT a FROM io.memento.domain.model.Account a WHERE a.clientId = :clientId AND a.provider = :provider")
    Account findByClientIdAndProvider(@Param("clientId") String clientId, @Param("provider") IdentityProvider provider);

}
