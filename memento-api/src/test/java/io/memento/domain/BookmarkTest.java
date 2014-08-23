package io.memento.domain;

import io.memento.domain.model.Account;
import io.memento.domain.model.Bookmark;
import io.memento.domain.model.EntityFactory;
import io.memento.infra.authentication.IdentityProvider;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class BookmarkTest {

    /**
     * Simple Unit Test that uses AssertJ Assertions Java library.
     */
    @Test
    public void create() {
        // Given
        String inputUrl = "http://some-url.fr";
        String inputTitle = "Lorem ipsum";
        String inputDescription = "Lorem ipsum dolor sit amet...";
        Account account = EntityFactory.newAccount("jdoe", IdentityProvider.GOOGLE);

        // When
        Bookmark bookmark = EntityFactory.newBookmark(account, inputUrl, inputTitle, inputDescription);

        // Then
        Assertions.assertThat(bookmark.getUrl()).isEqualTo(inputUrl);
        Assertions.assertThat(bookmark.getTitle()).isEqualTo(inputTitle);
        Assertions.assertThat(bookmark.getDescription()).isEqualTo(inputDescription);
        Assertions.assertThat(bookmark.getCreationDate()).isNull();
        Assertions.assertThat(bookmark.getModificationDate()).isNull();
    }

}
