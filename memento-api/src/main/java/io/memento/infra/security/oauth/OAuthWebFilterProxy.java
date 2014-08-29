package io.memento.infra.security.oauth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Project: Memento
 * User:    Jérémy Buget
 * Email:   jbuget@agile-spirit.fr
 * Date:    25/08/2014
 */
@Named
@WebFilter(filterName = "OAuthWebFilter", urlPatterns = {"/api/*"}, description = "Authentication filter")
public class OAuthWebFilterProxy extends DelegatingFilterProxy {

}
