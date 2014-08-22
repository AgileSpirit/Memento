package io.memento.application.impl;

import io.memento.application.DocumentResource;
import io.memento.application.response.FindDocumentsResponse;
import io.memento.domain.services.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@Controller
@RequestMapping("/api/documents")
public class DocumentResourceImpl implements DocumentResource {

    private final static Logger LOGGER = LoggerFactory.getLogger(DocumentResourceImpl.class);

    @Inject
    private DocumentService documentService;

    /*
     * -----------------------------------------------------------------------
     * DOCUMENTS
     * -----------------------------------------------------------------------
     */

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/search", produces="application/json")
    @ResponseBody
    public FindDocumentsResponse findDocuments(@RequestParam("q") String query, @RequestParam("o") int offset, @RequestParam("s") int size) {
        LOGGER.info("#findDocuments(" + query + ", " + offset + ", " + size + ")");
        FindDocumentsResponse response = new FindDocumentsResponse();
        response.setQuery(query);
        response.setOffset(offset);
        response.setSize(size);
        response.setTotalItems(documentService.count(query));
        response.setDocuments(documentService.find(query, offset, size));
        return response;
    }

}
