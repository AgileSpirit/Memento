package io.memento.infra.repository.note;

import io.memento.domain.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long>, NoteRepositoryCustom {
}
