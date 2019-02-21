package servlets;

import shopping.sql.DaoUserSQL;
import shopping.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;

public class AuthServlet extends HttpServlet {
    private Connection conn;

    public AuthServlet(Connection conn) {
        this.conn = conn;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Files.copy(Paths.get("auth.html"), resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        String name = pfr.getStr("name");
        String surname = pfr.getStr("surname");
        String login = pfr.getStr("login");
        String password = pfr.getStr("password");

        User user = new User(name, surname, login, password);
        new DaoUserSQL(conn).put(user);
        resp.addCookie(new Cookie("uname", login));

        resp.sendRedirect("/listauth");
    }
}
