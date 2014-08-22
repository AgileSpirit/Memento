package io.memento.application;

import io.memento.domain.model.Bookmark;

/**
 * Interface for the REST resources that deal with bookmarks.
 */
public interface BookmarkResource {

    String ping();

    Bookmark getBookmark(Long id);

    Bookmark saveBookmark(Bookmark bookmark);

    Bookmark updateBookmark(Long id, Bookmark bookmark);

    void removeBookmark(Long id);

}
