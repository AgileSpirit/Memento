package io.memento.application;

import io.memento.application.response.FindDocumentsResponse;

import javax.servlet.http.HttpServletRequest;

public interface DocumentResource {

    /*
     * General Documents
     */
    FindDocumentsResponse findDocuments(String query, int offset, int size, HttpServletRequest request);

}
