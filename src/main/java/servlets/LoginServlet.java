package servlets;

import shopping.dao.UserStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoginServlet extends HttpServlet {
    public static String f_lg ="login";
    public static String f_pw = "password";
    private final UserStorage us;

    public LoginServlet(UserStorage us) {
        this.us = us;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Files.copy(Paths.get("login.html"), resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        String login = pfr.getStr("login");
        String password = pfr.getStr("password");

        us.register(login, password);
        System.out.println(req.getParameterMap());

        resp.sendRedirect("/list");
    }
}
