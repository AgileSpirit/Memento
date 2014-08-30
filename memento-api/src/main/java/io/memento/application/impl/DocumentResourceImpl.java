package io.memento.application.impl;

import io.memento.application.DocumentResource;
import io.memento.application.response.FindDocumentsResponse;
import io.memento.domain.model.Account;
import io.memento.domain.model.Document;
import io.memento.domain.services.DocumentService;
import io.memento.infra.security.HttpHeadersAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/api/documents")
public class DocumentResourceImpl extends HttpHeadersAccessor implements DocumentResource {

    private final static Logger LOGGER = LoggerFactory.getLogger(DocumentResourceImpl.class);

    @Inject
    private DocumentService documentService;

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/search", produces="application/json")
    @ResponseBody
    public FindDocumentsResponse findDocuments(@RequestParam("q") String query, @RequestParam("o") int offset, @RequestParam("s") int size, HttpServletRequest request) {
        Account account = getAccount(request);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Find documents: query='" + query + "', offset=" + offset + ", size=" + size + " for account " + account.getId());
        }

        long totalItems = documentService.count(query, account);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(totalItems + " have matched query '" + query + "'");
        }

        List documents = documentService.find(query, offset, size, account);

        // Build response
        FindDocumentsResponse response = new FindDocumentsResponse();
        response.setQuery(query);
        response.setOffset(offset);
        response.setSize(size);
        response.setTotalItems(totalItems);
        response.setDocuments(documents);
        return response;
    }

}
