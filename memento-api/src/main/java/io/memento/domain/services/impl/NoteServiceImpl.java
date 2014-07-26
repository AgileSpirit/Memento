package io.memento.domain.services.impl;

import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.Lists;
import io.memento.domain.model.Note;
import io.memento.domain.services.DocumentService;
import io.memento.domain.services.NoteService;
import io.memento.infra.repository.note.NoteRepository;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class NoteServiceImpl implements NoteService {

    @Inject
    private NoteRepository noteRepository;

    NoteServiceImpl() {
        // Empty constructor
    }

    NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Timed
    public Note findOne(Long id) {
        return noteRepository.findOne(id);
    }

    @Override
    public List<Note> find(String query, int offset, int size) {
        List<Note> notes = new ArrayList<>();
        if (query == null || query.trim().isEmpty()) {
            Iterable<Note> data = noteRepository.findNotes(offset, size);
            notes = Lists.newArrayList(data);
        } else {
            Iterable<Note> data = noteRepository.findNotes(query, offset, size);
            notes = Lists.newArrayList(data);
        }
        return notes;
    }

    @Timed
    public Note save(Note note) {
        note.setCreationDate(new DateTime());
        return noteRepository.save(note);
    }

    @Timed
    public Note update(Note note) {
        note.setModificationDate(new DateTime());
        return noteRepository.save(note);
    }

    @Timed
    public void delete(Long id) {
        noteRepository.delete(id);
    }

    @Override
    public Long count(String query) {
        if (query == null || query.trim().isEmpty()) {
            return noteRepository.count();
        } else {
            return noteRepository.count(query);
        }
    }

}
