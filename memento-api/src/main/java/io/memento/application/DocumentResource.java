package io.memento.application;

import io.memento.application.response.FindDocumentsResponse;

public interface DocumentResource {

    /*
     * General Documents
     */
    FindDocumentsResponse findDocuments(String query, int offset, int size);

}
