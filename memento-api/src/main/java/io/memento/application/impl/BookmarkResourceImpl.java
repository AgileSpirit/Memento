package io.memento.application.impl;

import io.memento.application.exceptions.ApplicationException;
import io.memento.application.exceptions.BadRequestParametersException;
import io.memento.application.exceptions.BookmarkNotFoundException;
import io.memento.application.BookmarkResource;
import io.memento.domain.model.Bookmark;
import io.memento.domain.services.BookmarkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@Controller
@RequestMapping("/api/bookmarks")
public class BookmarkResourceImpl implements BookmarkResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookmarkResourceImpl.class);

    @Inject
    BookmarkService bookmarkService;

    @Override
    @RequestMapping("/ping")
    public String ping() {
        return "pong";
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Bookmark getBookmark(@PathVariable Long id) {
        // Log
        LOGGER.info("getBookmark(" + id + ")");

        // Check input
        if (id == null) {
            throw new BadRequestParametersException("Missing bookmark ID");
        }

        // Process
        Bookmark bookmark = bookmarkService.findOne(id);

        // Check output
        if (bookmark == null) {
            throw new BookmarkNotFoundException();
        }

        // Return
        return bookmark;
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Bookmark saveBookmark(@RequestBody Bookmark bookmark) {
        // Log
        LOGGER.info("saveBookmark([bookmark.id] " + bookmark.getId() + " )");

        // Check Input
        checkParametersForCreate(bookmark);

        // Process
        Bookmark entity = bookmarkService.save(bookmark);

        // Check Output
        if (entity == null) {
            throw new ApplicationException();
        }

        // Return
        return entity;
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Bookmark updateBookmark(@PathVariable Long id, @RequestBody Bookmark bookmark) {
        // Log
        LOGGER.info("updateBookmark(" + bookmark.getId() + " )");

        // Check Input
        checkParametersForUpdate(id, bookmark);
        Bookmark old = bookmarkService.findOne(id);
        if (old == null) {
            throw new BookmarkNotFoundException();
        }

        // Process
        Bookmark entity = bookmarkService.update(bookmark);

        // Check Output
        if (entity == null) {
            throw new ApplicationException();
        }

        // Return
        return entity;
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeBookmark(@PathVariable Long id) {
        // Log
        LOGGER.info("removeBookmark(" + id + ")");

        // Check input
        if (id == null) {
            throw new BadRequestParametersException("Missing bookmark ID");
        }

        // Process
        bookmarkService.delete(id);
    }

    /*
     * -----------------------------------------------------------------------
     * UTILS
     * -----------------------------------------------------------------------
     */

    private void checkBookmarkForCreateOrUpdate(Bookmark bookmark) {
        if (bookmark == null) {
            throw new BadRequestParametersException("Given bookmark must not be null");
        }
        if (bookmark.getUrl() == null) {
            throw new BadRequestParametersException("Given bookmark's URL must not be null");
        }
        if (bookmark.getTitle() == null) {
            throw new BadRequestParametersException("Given bookmark's title must not be null");
        }
    }

    private void checkParametersForCreate(Bookmark bookmark) {
        checkBookmarkForCreateOrUpdate(bookmark);

        if (bookmark.getId() != null) {
            throw new BadRequestParametersException("Given bookmark's ID must be null");
        }
        if (bookmark.getCreationDate() != null) {
            throw new BadRequestParametersException("Given bookmark's creation date must be null");
        }
    }

    private void checkParametersForUpdate(Long id, Bookmark bookmark) {
        checkBookmarkForCreateOrUpdate(bookmark);

        if (id == null) {
            throw new BadRequestParametersException("Given ID must not be null");
        }
        if (bookmark.getId() == null) {
            throw new BadRequestParametersException("Given bookmark's ID must not be null");
        }
        if (id.equals(bookmark.getId())) {
            throw new BadRequestParametersException("Given ID and bookmark.id must be equal");
        }
        if (bookmark.getCreationDate() == null) {
            throw new BadRequestParametersException("Given bookmark's creation date must be null");
        }
    }

}
