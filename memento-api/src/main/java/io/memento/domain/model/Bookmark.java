package io.memento.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Entity class that defines a bookmark.
 */
@Entity
@DiscriminatorValue(value = DocumentType.Values.BOOKMARK)
public class Bookmark extends Memento {

    /**
     * The URL pointed by the bookmark.
     */
    @Column(nullable = false, length = 1024)
    private String url;

    /**
     * Factory method to instance a bookmark.
     *
     * @param url the URL of the bookmark
     * @param title the title of the bookmark
     * @param description the description of the bookmark
     * @return the created bookmark
     */
    public static Bookmark create(String url, String title, String description) {
        Bookmark bookmark = new Bookmark();
        bookmark.setUrl(url);
        bookmark.setTitle(title);
        bookmark.setDescription(description);
        return bookmark;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getType() {
        return DocumentType.Values.BOOKMARK;
    }
}
