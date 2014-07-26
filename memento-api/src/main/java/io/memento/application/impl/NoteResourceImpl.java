package io.memento.application.impl;

import io.memento.application.NoteResource;
import io.memento.application.exceptions.ApplicationException;
import io.memento.application.exceptions.BadRequestParametersException;
import io.memento.application.exceptions.NoteNotFoundException;
import io.memento.domain.model.Bookmark;
import io.memento.domain.model.Note;
import io.memento.domain.model.Note;
import io.memento.domain.model.Note;
import io.memento.domain.services.NoteService;
import io.memento.domain.services.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

@Controller
@RequestMapping("/api/notes")
public class NoteResourceImpl implements NoteResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoteResourceImpl.class);

    @Inject
    NoteService noteService;

    /*
     * -----------------------------------------------------------------------
     * NOTES
     * -----------------------------------------------------------------------
     */

    @Override
    @RequestMapping(method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public Note getNote(Long id) {
        // Log
        LOGGER.info("getNote(" + id + ")");

        // Check input
        if (id == null) {
            throw new BadRequestParametersException("Missing note ID");
        }

        // Process
        Note note = noteService.findOne(id);

        // Check output
        if (note == null) {
            throw new NoteNotFoundException();
        }

        // Return
        return note;
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, produces="application/json")
    @ResponseBody
    public Note saveNote(@RequestBody Note note) {
        // Log
        LOGGER.info("saveNote([note.id] " + note.getId() + " )");

        // Check Input
//        checkParametersForCreate(note);

        // Process
        Note entity = noteService.save(note);

        // Check Output
        if (entity == null) {
            throw new ApplicationException();
        }

        // Return
        return entity;
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT, produces="application/json")
    @ResponseBody
    public Note updateNote(@RequestBody Note note) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    @RequestMapping(method = RequestMethod.DELETE, produces="application/json")
    @ResponseBody
    public void removeNote(Long id) {
        // Log
        LOGGER.info("removeNote(" + id + ")");

        // Check input
        if (id == null) {
            throw new BadRequestParametersException("Missing note ID");
        }

        // Process
        noteService.delete(id);
    }

}
