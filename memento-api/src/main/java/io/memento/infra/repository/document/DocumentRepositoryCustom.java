package io.memento.infra.repository.document;

import io.memento.domain.model.Document;

public interface DocumentRepositoryCustom {

    long count(String pattern);

    Iterable<Document> findMementos(int offset, int size);

    Iterable<Document> findMementos(String pattern, int offset, int size);
}
