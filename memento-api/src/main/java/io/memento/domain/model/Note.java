package io.memento.domain.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = DocumentType.Values.NOTE)
public class Note extends Document {

    @Column(nullable = true, length = 1024)
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getType() {
        return DocumentType.Values.NOTE;
    }

    public static Note create(String title, String description, String content) {
        Note note = new Note();
        note.setTitle(title);
        note.setDescription(description);
        note.setContent(content);
        return note;
    }
}
