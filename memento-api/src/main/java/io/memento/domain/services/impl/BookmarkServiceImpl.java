package io.memento.domain.services.impl;

import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.Lists;
import io.memento.domain.model.Bookmark;
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

}
