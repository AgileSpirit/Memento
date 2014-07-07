package io.memento.infra.repository;

import io.memento.domain.Bookmark;

public interface BookmarkRepositoryCustom {

    long count(String pattern);

    Iterable<Bookmark> findLastBookmarksOrderByCreationDateDesc(int number);

    Iterable<Bookmark> findBookmarks(int offset, int size);

    Iterable<Bookmark> findBookmarks(String pattern, int offset, int size);
}
