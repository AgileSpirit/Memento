package io.memento.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

@JsonIgnoreProperties({"new"})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType = DiscriminatorType.STRING)
public abstract class Document extends AbstractPersistable<Long> {

    @Column(nullable = true, length = 1024)
    private String title;

    @Column(nullable = true, length = 1024)
    private String description;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime creationDate;

    @Column(nullable = true)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime modificationDate;

    public abstract String getType();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(DateTime creationDate) {
        this.creationDate = creationDate;
        this.modificationDate = creationDate;
    }

    public DateTime getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(DateTime modificationDate) {
        this.modificationDate = modificationDate;
    }
}
