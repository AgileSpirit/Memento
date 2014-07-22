package io.memento.domain.services.impl;

import com.google.common.collect.Lists;
import io.memento.domain.model.Document;
import io.memento.domain.services.DocumentService;
import io.memento.infra.repository.DocumentRepository;
import org.springframework.data.domain.Sort;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class DocumentServiceImpl implements DocumentService {

    @Inject
    private DocumentRepository documentRepository;

    @Override
    public List<Document> findAll() {
        final List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "creationDate"));
        orders.add(new Sort.Order(Sort.Direction.DESC, "modificationDate"));

        return Lists.newArrayList(documentRepository.findAll(new Sort(orders)));
    }

    @Override
    public List<Document> find(String query, int offset, int size) {
        List<Document> documents = new ArrayList<>();
        if (query == null || query.trim().isEmpty()) {
            Iterable<Document> data = documentRepository.findMementos(offset, size);
            documents = Lists.newArrayList(data);
        } else {
            Iterable<Document> data = documentRepository.findMementos(query, offset, size);
            documents = Lists.newArrayList(data);
        }
        return documents;
    }

    @Override
    public Long count(String query) {
        if (query == null || query.trim().isEmpty()) {
            return documentRepository.count();
        } else {
            return documentRepository.count(query);
        }
    }
}
