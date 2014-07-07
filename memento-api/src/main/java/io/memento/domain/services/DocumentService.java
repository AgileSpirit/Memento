package io.memento.domain.services;

import io.memento.domain.model.Memento;

import java.util.List;

public interface DocumentService {

    Memento getDocument(Long id);
    List<Memento> findDocuments(String query, int offset, int size);
    Memento saveDocument(Memento memento);
    void deleteDocument(Long id);
    Long countDocuments(String query);

}
