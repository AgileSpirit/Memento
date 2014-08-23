package io.memento.domain.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Entity class that defines a bookmark.
 */
@Entity
@DiscriminatorValue(value = DocumentType.Values.BOOKMARK)
public class Bookmark extends Document {

    /*
     * ATTRIBUTES
     */

    @Column(nullable = true, length = 1024)
    private String url;

    @Column(nullable = true, length = 1024)
    private String description;

    /*
     * GETTERS & SETTERS
     */

    @Override
    public String getType() {
        return DocumentType.Values.BOOKMARK;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
