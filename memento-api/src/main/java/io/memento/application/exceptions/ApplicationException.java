package io.memento.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 */
@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE, reason = "An error occurred during treatment processing")
public class ApplicationException extends RuntimeException {
}
