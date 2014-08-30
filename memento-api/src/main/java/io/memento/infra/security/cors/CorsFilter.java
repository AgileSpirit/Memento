package io.memento.infra.security.cors;

import io.memento.infra.security.HttpHeadersAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Named
@WebFilter(urlPatterns = {"/*"}, description = "CORS filter")
public class CorsFilter extends HttpHeadersAccessor implements Filter {

    private static Logger LOGGER = LoggerFactory.getLogger(CorsFilter.class);

    private FilterConfig config = null;

    @Override
    public void init(FilterConfig filterConfig) {
        this.config = filterConfig;
        config.getServletContext().log("Initializing servlet filter " + getClass().getName());
    }

    @Override
    public void destroy() {
        config.getServletContext().log("Destroying servlet filter " + getClass().getName());
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("\r\n\r\n" + request.getMethod() + " " + request.getRequestURI());
        }

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS, DELETE, PUT, POST");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization, X-Access-Token");

        chain.doFilter(request, response);
    }

}