package io.memento.infra.repository.note;

import io.memento.domain.model.Note;

public interface NoteRepositoryCustom {

    long count(String pattern);

    Iterable<Note> findLastNotesOrderByCreationDateDesc(int number);

    Iterable<Note> findNotes(int offset, int size);

    Iterable<Note> findNotes(String pattern, int offset, int size);
}
