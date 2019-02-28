package servlets.filters;

import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.servlet.Source;
import service.UserService;
import utils.ParameterFromRequest;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LoginPasswordFilter implements Filter {
    private UserService users;
    public LoginPasswordFilter(UserService users) {
        this.users= users;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req;
        if (request instanceof HttpServletRequest) {
            req = (HttpServletRequest) request;
        } else {
            throw new IllegalArgumentException("ServletRequest should be instance of HttpServletRequest");
        }

        if (!HttpMethod.POST.name().equalsIgnoreCase(req.getMethod())) {
            chain.doFilter(request, response);
            return;
        }
        //проверка введенного пароля
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        String login = pfr.getStr("login");
        String password = pfr.getStr("password");
        try{
            if(!users.checkUserPass(login, password)) {
                throw new IllegalArgumentException("The user login or password is incorrect");
            }
                chain.doFilter(request, response);
        } catch (IllegalArgumentException e){
            response.getWriter().println("<html><a href=\"/reg\">The user login or password is incorrect</a></html>");
        }


    }

    @Override
    public void destroy() {

    }
}
