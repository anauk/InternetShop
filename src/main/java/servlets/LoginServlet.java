package servlets;

import freemarker.FreemarkerUser;
import shopping.dao.UserStorage;
import shopping.entity.User;
import shopping.impl.UserStoragImpl;
import shopping.sql.DaoUserSQL;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.Collection;

public class LoginServlet extends HttpServlet {
    private final FreemarkerUser template;
    public static String f_lg = "login";
    public static String f_pw = "password";
    private final UserStorage us;
    private UserStoragImpl usi;
    private Connection conn;

    public LoginServlet(FreemarkerUser template, UserStorage us) {
        this.template = template;
        this.us = us;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Files.copy(Paths.get("login.html"), resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Collection<User> list = new DaoUserSQL(conn).all();
        DaoUserSQL daoUserSQL = new DaoUserSQL(conn);
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        String login = pfr.getStr(f_lg);
        String password = pfr.getStr(f_pw);
      /* us.register(login, password);
        System.out.println(req.getParameterMap());*/
        if (!usi.check(login,password)) {
            System.out.println("such login is empty");
            resp.sendRedirect("/auth");

        } else {
            resp.sendRedirect("/listauth");
        }
    }
}
