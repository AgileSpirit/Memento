package io.memento.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED, reason = "The resource is not yet implemented")
public class NotYetImplementedException extends RuntimeException {
}
