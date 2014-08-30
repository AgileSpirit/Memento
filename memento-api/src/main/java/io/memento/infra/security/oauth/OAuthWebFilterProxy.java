package io.memento.infra.security.oauth;

import org.springframework.web.filter.DelegatingFilterProxy;

import javax.inject.Named;
import javax.servlet.annotation.WebFilter;

/**
 * Project: Memento
 * User:    Jérémy Buget
 * Email:   jbuget@agile-spirit.fr
 * Date:    25/08/2014
 */
@Named
@WebFilter(filterName = "OAuthWebFilter", urlPatterns = {"/api/*"}, description = "OAuth2 based authentication filter")
public class OAuthWebFilterProxy extends DelegatingFilterProxy {

}
