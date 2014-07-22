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

    /**
     * The URL pointed by the bookmark.
     */
    @Column(nullable = true, length = 1024)
    private String url;

    /**
     * Factory method to instance a bookmark.
     *
     * @param url the URL of the bookmark
     * @param title the title of the bookmark
     * @param description the description of the bookmark
     * @return the created bookmark
     */
    public static Bookmark create(String title, String description, String url) {
        Bookmark bookmark = new Bookmark();
        bookmark.setTitle(title);
        bookmark.setDescription(description);
        bookmark.setUrl(url);
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
