package io.memento.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;

/**
 * Project: Memento
 * User:    Jérémy Buget
 * Email:   jbuget@agile-spirit.fr
 * Date:    29/08/2014
 */

@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
public class Http500InternalServerError extends RuntimeException {
}
