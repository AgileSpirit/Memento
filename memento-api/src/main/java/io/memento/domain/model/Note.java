package io.memento.domain.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = DocumentType.Values.NOTE)
public class Note extends Document {

    @Override
    public String getType() {
        return DocumentType.Values.NOTE;
    }

    public static Note create(String title, String content) {
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        return note;
    }
}
