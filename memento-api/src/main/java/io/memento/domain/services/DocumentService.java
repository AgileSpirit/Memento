package io.memento.domain.services;

import io.memento.domain.model.Document;

import java.util.List;

public interface DocumentService {

    List<Document> findAll();

    List<Document> find(String query, int offset, int size);

    Long count(String query);

}
