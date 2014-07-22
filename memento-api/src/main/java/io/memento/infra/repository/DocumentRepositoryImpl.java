package io.memento.infra.repository;

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
    public long count(String pattern) {
        String expression = "" +
                "select d.id from Document d " +
                "where d.url like :pattern " +
                "or d.title like :pattern " +
                "or d.description like :pattern " +
                "order by d.creationDate desc";
        TypedQuery<Long> query = em.createQuery(expression, Long.class);
        query.setParameter("pattern", "%" + pattern + "%");
        return query.getResultList().size();
    }

    @Override
    public Iterable<Document> findMementos(int offset, int size) {
        String expression = "" +
                "select d from Document d " +
                "order by d.creationDate desc";
        TypedQuery<Document> query = em.createQuery(expression, Document.class);
        query.setFirstResult(offset);
        query.setMaxResults(size);
        return query.getResultList();
    }

    @Override
    public Iterable<Document> findMementos(String pattern, int offset, int size) {
        String expression = "" +
                "select d from Document d " +
                "where d.url like :pattern " +
                "or d.title like :pattern " +
                "or d.description like :pattern " +
                "order by d.creationDate desc";
        TypedQuery<Document> query = em.createQuery(expression, Document.class);
        query.setParameter("pattern", "%" + pattern + "%");
        query.setFirstResult(offset);
        query.setMaxResults(size);
        return query.getResultList();
    }

}
