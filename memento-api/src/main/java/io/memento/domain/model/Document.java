package io.memento.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType = DiscriminatorType.STRING)
public abstract class Document extends PersistableEntity {

    /*
     * ATTRIBUTES
     */

    @Column(nullable = true, length = 1024)
    private String title;

    @Lob
    @Column(nullable = true, length = Integer.MAX_VALUE)
    private String content;

    /*
     * GETTERS & SETTERS
     */

    public abstract String getType();

   public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
