package io.memento.infra.security.jwt;

/**
 * Project: Memento
 * User:    Jérémy Buget
 * Email:   jbuget@agile-spirit.fr
 * Date:    30/08/2014
 */

import com.google.gson.JsonObject;
import net.oauth.jsontoken.Checker;

import java.security.SignatureException;

/**
 * Allows any audience (even null).
 */
public class IgnoreAudience implements Checker {

    @Override
    public void check(JsonObject payload) throws SignatureException {
        // don't throw - allow anything
    }
}
