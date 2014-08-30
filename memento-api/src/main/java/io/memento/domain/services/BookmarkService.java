package io.memento.domain.services;

import io.memento.domain.model.Account;
import io.memento.domain.model.Bookmark;

public interface BookmarkService extends DocumentService<Bookmark> {

    boolean isBookmarkAlreadyAdded(String url, Account account);

    Bookmark populateBookmark(Bookmark bookmark, Account account);

}
