package io.memento.infra.repository.document;

import io.memento.domain.model.Account;
import io.memento.domain.model.Document;

public interface DocumentRepositoryCustom {

    long count(String pattern, Account account);

    Iterable<Document> findMementos(int offset, int size, Account account);

    Iterable<Document> findMementos(String pattern, int offset, int size, Account account);
}
