package io.memento.application;

import io.memento.application.request.ConnectionRequest;
import io.memento.application.response.ConnectionResponse;

/**
 * Project: Memento
 * User:    Jérémy Buget
 * Email:   jbuget@agile-spirit.fr
 * Date:    21/08/2014
 */
public interface ConnectionResource {

    ConnectionResponse login(ConnectionRequest request);

    ConnectionResponse logout(ConnectionRequest request);

}
