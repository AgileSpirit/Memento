package io.memento.infra.repository.document;

import io.memento.domain.model.Account;
import io.memento.domain.model.Document;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * This class is an example of advanced/customized/specific query implementation.
 */
@SuppressWarnings("ALL")
@Named
public class DocumentRepositoryImpl implements DocumentRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long count(String pattern, Account account) {
        String expression = "" +
                "SELECT d.id FROM Document d " +
                "WHERE d.owner = :account " +
                "AND (d.url LIKE :pattern " +
                "OR d.title LIKE :pattern " +
                "OR d.description LIKE :pattern) " +
                "ORDER BY d.creationDate DESC";
        TypedQuery<Long> query = em.createQuery(expression, Long.class);
        query.setParameter("pattern", "%" + pattern + "%");
        query.setParameter("account", account);
        return query.getResultList().size();
    }

    @Override
    public Iterable<Document> findMementos(int offset, int size, Account account) {
        String expression = "" +
                "SELECT d FROM Document d " +
                "WHERE d.owner = :account " +
                "ORDER BY d.creationDate DESC";
        TypedQuery<Document> query = em.createQuery(expression, Document.class);
        query.setParameter("account", account);
        query.setFirstResult(offset);
        query.setMaxResults(size);
        return query.getResultList();
    }

    @Override
    public Iterable<Document> findMementos(String pattern, int offset, int size, Account account) {
        String expression = "" +
                "SELECT d FROM Document d " +
                "WHERE d.owner = :account " +
                "AND (d.url LIKE :pattern " +
                "OR d.title LIKE :pattern " +
                "OR d.description LIKE :pattern) " +
                "ORDER BY d.creationDate DESC";
        TypedQuery<Document> query = em.createQuery(expression, Document.class);
        query.setParameter("pattern", "%" + pattern + "%");
        query.setParameter("account", account);
        query.setFirstResult(offset);
        query.setMaxResults(size);
        return query.getResultList();
    }

}
