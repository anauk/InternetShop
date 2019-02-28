package servlets.registration;

import freemarker.Freemarker;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LogOutServlet extends HttpServlet {
    private final String COOKIES_NAME="my_shop";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie(COOKIES_NAME,"");
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
        resp.sendRedirect("/list");
    }
}
