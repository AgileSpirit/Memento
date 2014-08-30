package io.memento.domain.services.impl;

import com.google.common.collect.Lists;
import io.memento.domain.model.Account;
import io.memento.domain.model.Document;
import io.memento.domain.services.DocumentService;
import io.memento.infra.repository.document.DocumentRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named(value = "documentService")
public class DocumentServiceImpl implements DocumentService<Document> {

    @Inject
    private DocumentRepository documentRepository;

    @Override
    public Document findOne(Long id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Document save(Document bookmark) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Document update(Document bookmark) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Document> find(String query, int offset, int size, Account account) {
        List<Document> documents = new ArrayList<>();
        if (query == null || query.trim().isEmpty()) {
            Iterable<Document> data = documentRepository.findMementos(offset, size, account);
            documents = Lists.newArrayList(data);
        } else {
            Iterable<Document> data = documentRepository.findMementos(query, offset, size, account);
            documents = Lists.newArrayList(data);
        }
        return documents;
    }

    @Override
    public void delete(Long id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Long count(String query, Account account) {
        if (query == null || query.trim().isEmpty()) {
            return documentRepository.count();
        } else {
            return documentRepository.count(query, account);
        }
    }
}
