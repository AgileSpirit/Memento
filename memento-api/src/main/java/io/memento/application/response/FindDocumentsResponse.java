package io.memento.application.response;

import io.memento.domain.model.Document;

import java.util.List;

/**
 * User: OCTO-JBU
 * Date: 08/07/2014
 * Time: 08:12
 */
public class FindDocumentsResponse {

    /*
     * ATTRIBUTES
     */

    // Input
    private String query;
    private int offset;
    private int size;
    // Output
    private long totalItems;
    private List<Document> documents;

    /*
     * GETTERS & SETTERS
     */

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }
}
