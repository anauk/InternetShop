package servlets.registration;

import service.UserService;
import utils.ParameterFromRequest;
import shopping.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AuthServlet extends HttpServlet {
    private final String COOKIES_NAME="my_shop";
    private UserService userService;

    public AuthServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Files.copy(Paths.get("templ/auth.html"),resp.getOutputStream());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        String name = pfr.getStr("name");
        String surname = pfr.getStr("surname");
        String login = pfr.getStr("login");
        String password = pfr.getStr("password");

        User user = new User(login.hashCode(), name, surname, login, password);
        userService.putUser(user);
        System.out.println(user);

        resp.sendRedirect("/reg");
    }
}
