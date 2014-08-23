package io.memento.domain.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = DocumentType.Values.NOTE)
public class Note extends Document {

    @Override
    public String getType() {
        return DocumentType.Values.NOTE;
    }

}
