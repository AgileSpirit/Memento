package io.memento.infra.security.oauth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

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
public class OAuthWebFilter implements Filter {

    private FilterConfig config = null;

    private static Logger LOGGER = LoggerFactory.getLogger(OAuthWebFilterProxy.class);

    private static String X_ACCESS_TOKEN = "X-Access-Token";

    @Inject
    private OAuthTokenStore tokenStore;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
        config.getServletContext().log("Initializing filter OAuthWebFilter");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        LOGGER.debug(request.getRequestURI());

        String accessToken = null;
        // In case of connection request, we don't try to get the access_token but to generate one
        if (request.getRequestURI().equals("/api/connection/login")) {
            // Do nothing
        }
        // In all other cases, requests should have an access_token into their headers
        else {

            if ("OPTIONS".equals(request.getMethod())) {
                // Do nothing
            } else {
                accessToken = extractAccessToken(request);
                OAuthTokenData token = retrieveTokenData(accessToken);
                boolean isTokenValid = verifyTokenData(token);
                if (!isTokenValid) {
                    tokenStore.remove(accessToken);
                    throw new RuntimeException("A token was found but is no more valid ; then remove it from OAuth store and throw Exception.");
                }
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        config.getServletContext().log("Destroying filter OAuthWebFilter");
    }

    private String extractAccessToken(HttpServletRequest request) {
        LOGGER.debug("Extract access_token from request");
        String accessToken = null;

        // Service-side Cookie based method
/*
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            String name = cookies[i].getName();
            String value = cookies[i].getValue();
            if (X_ACCESS_TOKEN.equals(name)) {
                accessToken = value;
            }
        }
*/
        // Request headers based method
        //accessToken = request.getHeader(X_ACCESS_TOKEN.toLowerCase());
        String bearer = request.getHeader("Authorization");
        accessToken = bearer.replace("Bearer ", "");

        // The request is secured and required authentication but the access_token was not set in the HTTP request headers
        if (accessToken == null) {
            LOGGER.error("access_token is null");
            throw new RuntimeException("ERROR ! ERROR ! ERROR !");
        }

        LOGGER.debug("The access_token " + accessToken + " was extracted");
        return accessToken;
    }

    private OAuthTokenData retrieveTokenData(String accessToken) {
        LOGGER.debug("Retrieving token data from access_token " + accessToken);
        OAuthTokenData token = tokenStore.get(accessToken);
        if (token == null) {
            LOGGER.error("No token was found the the given access_token " + accessToken);
            throw new RuntimeException("ERROR ! ERROR ! ERROR !");
        }
        LOGGER.debug("The token data was retrieved for access_token " + accessToken);
        return token;
    }

    private boolean verifyTokenData(OAuthTokenData tokenData) {
        LOGGER.debug("Verify the token data");
        // TODO
        boolean result = true;
        LOGGER.debug("The result of the verification is " + result);
        return result;
    }

}
