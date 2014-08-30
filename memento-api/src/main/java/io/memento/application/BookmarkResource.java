package io.memento.application;

import io.memento.domain.model.Bookmark;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface for the REST resources that deal with bookmarks.
 */
public interface BookmarkResource {

    String ping();

    Bookmark getBookmark(Long id);

    Bookmark saveBookmark(Bookmark bookmark, HttpServletRequest request);

    Bookmark updateBookmark(Long id, Bookmark bookmark);

    void removeBookmark(Long id);

}
