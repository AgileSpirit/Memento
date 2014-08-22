package io.memento.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

/**
 * Project: Memento
 * User:    Jérémy Buget
 * Email:   jbuget@agile-spirit.fr
 * Date:    20/08/2014
 */
@JsonIgnoreProperties({"new"})
@MappedSuperclass
public abstract class PersistableEntity extends AbstractPersistable<Long> {

    /*
     * ATTRIBUTES
     */

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime creationDate;

    @Column(nullable = true)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime modificationDate;

    /*
     * GETTERS & SETTERS
     */

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
