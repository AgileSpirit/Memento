package io.memento.domain.services.impl;

import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.Lists;
import io.memento.application.exceptions.Http500InternalServerError;
import io.memento.domain.model.Account;
import io.memento.domain.model.Bookmark;
import io.memento.domain.model.EntityFactory;
import io.memento.domain.services.BookmarkService;
import io.memento.infra.readability.ReadabilityResponse;
import io.memento.infra.repository.bookmark.BookmarkRepository;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Named
public class BookmarkServiceImpl implements BookmarkService {

    private final static Logger LOGGER = LoggerFactory.getLogger(BookmarkServiceImpl.class);

    @Inject
    private BookmarkRepository bookmarkRepository;

    BookmarkServiceImpl() {
        // Empty constructor
    }

    BookmarkServiceImpl(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    @Timed
    public Bookmark findOne(Long id) {
        return bookmarkRepository.findOne(id);
    }

    @Timed
    public List<Bookmark> findAll() {
        final List<Order> orders = new ArrayList<>();
        orders.add(new Order(Direction.DESC, "creationDate"));
        orders.add(new Order(Direction.DESC, "modificationDate"));

        return Lists.newArrayList(bookmarkRepository.findAll(new Sort(orders)));
    }

    @Override
    public List<Bookmark> find(String query, int offset, int size) {
        List<Bookmark> bookmarks = new ArrayList<>();
        if (query == null || query.trim().isEmpty()) {
            Iterable<Bookmark> data = bookmarkRepository.findBookmarks(offset, size);
            bookmarks = Lists.newArrayList(data);
        } else {
            Iterable<Bookmark> data = bookmarkRepository.findBookmarks(query, offset, size);
            bookmarks = Lists.newArrayList(data);
        }
        return bookmarks;
    }

    @Timed
    @Transactional
    public Bookmark save(Bookmark bookmark) {
        bookmark.setCreationDate(new DateTime());
        return bookmarkRepository.save(bookmark);
    }

    @Timed
    @Transactional
    public Bookmark update(Bookmark bookmark) {
        bookmark.setModificationDate(new DateTime());
        return bookmarkRepository.save(bookmark);
    }

    @Timed
    @Transactional
    public void delete(Long id) {
        bookmarkRepository.delete(id);
    }

    @Override
    public Long count(String query) {
        if (query == null || query.trim().isEmpty()) {
            return bookmarkRepository.count();
        } else {
            return bookmarkRepository.count(query);
        }
    }

    @Override
    public Bookmark populateBookmark(Bookmark bookmark) {
        // https://www.readability.com/api/content/v1/parser?url=http://blog.readability.com/2011/02/step-up-be-heard-readability-ideas/&token=aadef94fc0e970862ac00067cf09717d111fa788
        // Readability token : aadef94fc0e970862ac00067cf09717d111fa788
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.readability.com/api/content/v1/parser";
        url += "?url=" + bookmark.getUrl();
        url += "&token=" + "aadef94fc0e970862ac00067cf09717d111fa788";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Call Readability API with URL: " + url);
        }

        ReadabilityResponse response = restTemplate.getForObject(url, ReadabilityResponse.class);
        if (response == null) {
            LOGGER.error("The response from Readability API was null");
            throw new Http500InternalServerError();
        } else {
            if (bookmark.getTitle() == null || bookmark.getTitle().trim().isEmpty()) {
                bookmark.setTitle(response.getTitle());
            }
            if (bookmark.getDescription() == null || bookmark.getDescription().trim().isEmpty()) {
                bookmark.setDescription(response.getExcerpt());
            }
            bookmark.setContent(response.getContent());
        }
        return bookmark;
    }

    @Override
    public Bookmark findBookmarkByAccountAndUrl(Account account, String url) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
