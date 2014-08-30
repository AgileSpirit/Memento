package io.memento.infra.security.oauth;

import io.memento.application.exceptions.Http500InternalServerError;
import io.memento.infra.security.HttpHeadersAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Project: Memento
 * User:    Jérémy Buget
 * Email:   jbuget@agile-spirit.fr
 * Date:    29/08/2014
 */
@Named
public class OAuthWebFilter extends HttpHeadersAccessor implements Filter {

    private FilterConfig config = null;

    private static Logger LOGGER = LoggerFactory.getLogger(OAuthWebFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
        config.getServletContext().log("Initializing filter OAuthWebFilter");
    }

    @Override
    public void destroy() {
        config.getServletContext().log("Destroying filter OAuthWebFilter");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(request.getRequestURI());
        }

        if ("OPTIONS".equals(request.getMethod()) || request.getRequestURI().equals("/api/connection/login")) {
            /*
             * In case of preflight (OPTIONS method) or connection requests, we do nothing.
             * In all other cases, requests should have an access token into their headers.
             */
        } else {
            String accessToken = extractAccessToken(request);
            OAuthTokenData token = retrieveTokenData(accessToken);
            boolean isTokenValid = verifyTokenData(token);
            if (!isTokenValid) {
                tokenStore.remove(accessToken);
                LOGGER.error("A token was found but is no more valid ; then remove it from OAuth store and throw Exception.");
                throw new Http500InternalServerError();
            }
        }

        chain.doFilter(request, response);
    }

    /**
     *
     * @param tokenData
     * @return
     */
    private boolean verifyTokenData(OAuthTokenData tokenData) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Verify the token data");
        }

        // TODO
        boolean result = true;

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("The result of the verification is " + result);
        }

        return result;
    }

}
