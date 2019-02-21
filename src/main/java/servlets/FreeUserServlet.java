package servlets;

import freemarker.FreemarkerUser;
import shopping.sql.DaoUserSQL;
import shopping.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;

public class FreeUserServlet extends HttpServlet {
    private Connection conn;

    public FreeUserServlet(Connection conn) {
        this.conn = conn;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FreemarkerUser fm = new FreemarkerUser();
        Collection<User> list = new DaoUserSQL(conn).all();
        //list.add(0, new DaoUserSQL(conn).get(17));

        HashMap<String,Object> users = new HashMap<>();
        users.put("users",list);
        System.out.println(list);
        fm.render("injectionUser.ftl", users, resp);
    }
}
