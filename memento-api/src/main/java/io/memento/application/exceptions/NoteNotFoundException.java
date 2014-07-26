package io.memento.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Note not found or not existing")
public class NoteNotFoundException extends DocumentNotFoundException {
}
