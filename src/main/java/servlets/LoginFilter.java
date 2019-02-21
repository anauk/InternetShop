package servlets;

import org.eclipse.jetty.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req;
        if (servletRequest instanceof HttpServletRequest) {
            req = (HttpServletRequest)servletRequest;
        } else {
            throw new IllegalArgumentException("ServletRequest should be instance of HttpServletRequest");
        }

        if (HttpMethod.POST.name().equals(req.getMethod()) &&
                !new Params(req).containsAll(LoginServlet.f_lg, LoginServlet.f_pw)) {
            ((HttpServletResponse)servletResponse).sendRedirect("/reg");
            } else {
            filterChain.doFilter(servletRequest, servletResponse);
                //servletResponse.getWriter().print("Your login or password wrong");
        }
    }

    @Override
    public void destroy() {
    }
}
