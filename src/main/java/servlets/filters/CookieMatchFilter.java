package servlets.filters;

import org.eclipse.jetty.servlet.Source;
import utils.CookieMethods;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieMatchFilter implements Filter {
    private CookieMethods cm;
    public CookieMatchFilter(CookieMethods cm) {
        this.cm = cm;

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req;
        HttpServletResponse resp;
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            req = (HttpServletRequest) request;
            resp = (HttpServletResponse) response;
        } else {
            throw new IllegalArgumentException("ServletRequest and ServletResponse should be instance of HttpServletRequest and HttpServletResponse");
        }

        if (cm.cookieMatch(req)) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect("/reg");
        }

    }

    @Override
    public void destroy() {

    }
}
