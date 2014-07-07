package io.memento.domain.services;

import io.memento.domain.model.Memento;

import javax.inject.Named;
import java.util.List;

@Named
public class DocumentServiceImpl implements DocumentService {

    @Override
    public Memento getDocument(Long id) {
        return null;
    }

    @Override
    public List<Memento> findDocuments(String query, int offset, int size) {
        return null;
    }

    @Override
    public Memento saveDocument(Memento memento) {
        return null;
    }

    @Override
    public void deleteDocument(Long id) {
    }

    @Override
    public Long countDocuments(String query) {
        return null;
    }

}
