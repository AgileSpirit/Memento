package io.memento.domain.services;

import io.memento.domain.model.Bookmark;
import io.memento.domain.model.Document;

import java.util.List;

public interface DocumentService<T extends Document> {

    T findOne(Long id);

    T save(T bookmark);

    T update(T bookmark);

    List<T> find(String query, int offset, int size);

    void delete(Long id);

    Long count(String query);
}
