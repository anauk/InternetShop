package servlets.registration;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /*HttpServletRequest hs = (HttpServletRequest) request;
        if (hs.getMethod().equalsIgnoreCase("POST")
                && !new Params(hs).containsAll(AuthServlet.name, AuthServlet.surname, AuthServlet.f_lg, AuthServlet.f_ps)) {
            ((HttpServletResponse)response).sendRedirect("/reg");
        } else {
            chain.doFilter(request, response);
        }*/
    }

    @Override
    public void destroy() {

    }
}
