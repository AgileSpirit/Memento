package io.memento.application;

import io.memento.application.request.ConnectionRequest;
import io.memento.application.response.ConnectionResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Project: Memento
 * User:    Jérémy Buget
 * Email:   jbuget@agile-spirit.fr
 * Date:    21/08/2014
 */
public interface ConnectionResource {

    ConnectionResponse login(ConnectionRequest request);

    HttpServletResponse logout(HttpServletRequest request);

}
