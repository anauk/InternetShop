package servlets.filters;

import org.eclipse.jetty.http.HttpMethod;
import service.UserService;
import shopping.exception.ElementNotFoundInDbException;
import utils.ParameterFromRequest;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

public class IsSuchUser implements Filter {
    private UserService userService;

    public IsSuchUser(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req;
        if(request instanceof HttpServletRequest){
            req = (HttpServletRequest) request;
        } else {
            throw new IllegalArgumentException("ServletRequest should be instance of HttpServletRequest");
        }
        if(!HttpMethod.POST.name().equalsIgnoreCase(req.getMethod())){
            chain.doFilter(request,response);
            return;
        }
        if (!HttpMethod.POST.name().equalsIgnoreCase(req.getMethod())) {
            chain.doFilter(request, response);
            return;
        }

        ParameterFromRequest pfr = new ParameterFromRequest(req);
        PrintWriter writer = response.getWriter();
        String login = pfr.getStr("login").trim();
        if (userService.isUserEmpty()) {
            chain.doFilter(request, response);
        }

        try {
            userService.getUser(login.hashCode());
            String message = "The user with this login is already exist";
            writer.printf("<html> <a href=\"/registration\"> %s </a></html>", message);
        } catch (ElementNotFoundInDbException e) {
            try {
                chain.doFilter(request, response);
            } catch (IOException | ServletException ex) {
                ex.printStackTrace();
            }
        }


    }

    @Override
    public void destroy() {

    }
}
