package io.memento.domain.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = DocumentType.Values.NOTE)
public class Note extends Memento {

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

}
