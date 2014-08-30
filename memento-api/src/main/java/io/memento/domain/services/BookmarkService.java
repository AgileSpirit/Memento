package io.memento.domain.services;

import io.memento.domain.model.Account;
import io.memento.domain.model.Bookmark;

public interface BookmarkService extends DocumentService<Bookmark> {

    Bookmark populateBookmark(Bookmark bookmark);

    Bookmark findBookmarkByAccountAndUrl(Account account, String url);
}
