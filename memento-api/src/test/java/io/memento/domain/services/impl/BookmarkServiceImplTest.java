package io.memento.domain.services.impl;

import io.memento.domain.model.Account;
import io.memento.domain.model.Bookmark;
import io.memento.domain.model.EntityFactory;
import io.memento.infra.repository.bookmark.BookmarkRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

/**
 * Test class to demonstrate Mockito usage.
 */
@RunWith(MockitoJUnitRunner.class)
public class BookmarkServiceImplTest {

    private final static Long SOME_ID = Long.valueOf("1");

    @Mock
    private BookmarkRepository repository;

    @InjectMocks
    private BookmarkServiceImpl service;

    /*
     * BookmarkService#save
     */

    @Test
    public void testSave() {
        // Given
        Account account = EntityFactory.newAccount("jdoe");
        Bookmark bookmark = EntityFactory.newBookmark(account, "http://original.com", "Original bookmark", "Description of my original bookmark");
        assertThat(bookmark.getCreationDate()).isEqualTo(null);
        when(repository.save(any(Bookmark.class))).thenReturn(bookmark);

        // When
        Bookmark actual = service.save(bookmark);

        // Then
        assertThat(actual.getCreationDate()).isNotNull();
    }

    /*
     * BookmarkService#findOne
     */

    @Test
    public void testFindOneShouldBeOk() {
        // When
        Bookmark bookmark = service.findOne(SOME_ID);
        // Then
        /* There is no error */
    }

    /*
     * BookmarkService#findAll
     */

    @Test
    public void testFindAllShouldBeOk() {
        // TODO
    }

    /*
     * BookmarkService#update
     */

    @Test
    public void testUpdateShouldBeOk() {
        // Given
        Account account = EntityFactory.newAccount("jdoe");
        Bookmark bookmark = EntityFactory.newBookmark(account, "http://original.com", "Original bookmark", "Description of my original bookmark");
        assertThat(bookmark.getModificationDate()).isEqualTo(null);
        when(repository.save(any(Bookmark.class))).thenReturn(bookmark);

        // When
        Bookmark actual = service.update(bookmark);

        // Then
        assertThat(actual.getModificationDate()).isNotNull();
    }

    /*
     * BookmarkService#delete
     */

    @Test
    public void testDeleteShouldBeOk() {
        // When
        service.delete(SOME_ID);
        // Then
        /* There is no error */
    }

}
