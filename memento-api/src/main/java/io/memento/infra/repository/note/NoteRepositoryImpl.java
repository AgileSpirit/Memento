package io.memento.infra.repository.note;

import io.memento.domain.model.Note;
import io.memento.infra.repository.note.NoteRepositoryCustom;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Named
public class NoteRepositoryImpl implements NoteRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long count(String pattern) {
        String expression = "" +
                "select n.id from Note n " +
                "where n.content like :pattern " +
                "or n.title like :pattern " +
                "or n.description like :pattern " +
                "order by n.creationDate desc";
        TypedQuery<Long> query = em.createQuery(expression, Long.class);
        query.setParameter("pattern", "%" + pattern + "%");
        return query.getResultList().size();
    }

    @Override
    public List<Note> findLastNotesOrderByCreationDateDesc(int number) {
        TypedQuery<Note> query = em.createQuery("from Note order by creationDate desc", Note.class).setMaxResults(number);
        return query.getResultList();
    }

    @Override
    public Iterable<Note> findNotes(int offset, int size) {
        String expression = "" +
                "select n from Note n " +
                "order by n.creationDate desc";
        TypedQuery<Note> query = em.createQuery(expression, Note.class);
        query.setFirstResult(offset);
        query.setMaxResults(size);
        return query.getResultList();
    }

    @Override
    public Iterable<Note> findNotes(String pattern, int offset, int size) {
        String expression = "" +
                "select n from Note n " +
                "where n.content like :pattern " +
                "or n.title like :pattern " +
                "or n.description like :pattern " +
                "order by n.creationDate desc";
        TypedQuery<Note> query = em.createQuery(expression, Note.class);
        query.setParameter("pattern", "%" + pattern + "%");
        query.setFirstResult(offset);
        query.setMaxResults(size);
        return query.getResultList();
    }

}
