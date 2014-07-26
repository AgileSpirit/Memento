package io.memento.application;

import io.memento.application.responses.FindDocumentsResponse;
import io.memento.domain.model.Bookmark;
import io.memento.domain.model.Note;

public interface DocumentResource {

    /*
     * General Documents
     */
    FindDocumentsResponse findDocuments(String query, int offset, int size);

}
