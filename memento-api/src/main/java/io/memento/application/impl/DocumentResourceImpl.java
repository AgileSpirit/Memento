package io.memento.application.impl;

import io.memento.application.DocumentResource;
import io.memento.application.DocumentSearchResponse;
import io.memento.domain.model.Bookmark;
import io.memento.domain.services.DocumentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@Controller
@RequestMapping("/api/documents")
public class DocumentResourceImpl implements DocumentResource {

    @Inject
    private DocumentService documentService;

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/search", produces="application/json")
    @ResponseBody
    public DocumentSearchResponse searchDocuments(@RequestParam("q") String query, @RequestParam("o") int offset, @RequestParam("s") int size) {
        DocumentSearchResponse response = new DocumentSearchResponse();
        response.setQuery(query);
        response.setOffset(offset);
        response.setSize(size);
        response.setTotalItems(documentService.count(query));
        response.setDocuments(documentService.find(query, offset, size));
        return response;
    }

}
