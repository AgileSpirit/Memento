package io.memento.application;

public interface DocumentResource {

    DocumentSearchResponse searchDocuments(String query, int offset, int size);

}
