package servlets;

import freemarker.FreemarkerUser;
import shopping.dao.UserStorage;
import shopping.impl.UserStoragImpl;
import shopping.sql.DaoCommoditySQL;
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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class AuthServlet extends HttpServlet {
    private final String COOKIE_NAME = "shop_cookie";
    public static String f_n="name";
    public static String f_sn="surname";
    public static String f_lg="login";
    public static String f_ps="password";
    private Connection conn;
    private UserStoragImpl usi;
    FreemarkerUser fm = new FreemarkerUser();

    public AuthServlet(Connection conn) {
        this.conn = conn;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<User> list = new DaoUserSQL(conn).all();
        HashMap<String, Object> users = new HashMap<String, Object>() {{
            put("login", list);
        }};
        fm.render("auth.html", users, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<User> list = new DaoUserSQL(conn).all();
        DaoUserSQL daoUserSQL = new DaoUserSQL(conn);
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        String name = pfr.getStr("name");
        String surname = pfr.getStr("surname");
        String login = pfr.getStr("login");
        String password = pfr.getStr("password");

            User user = new User(name, surname, login, password);
            new DaoUserSQL(conn).put(user);
            usi.register(login, password);
        System.out.println(req.getParameterMap());
            resp.addCookie(new Cookie(COOKIE_NAME, Integer.toString(daoUserSQL.getId(login, password))));

        resp.sendRedirect("/listauth");

    }
}
