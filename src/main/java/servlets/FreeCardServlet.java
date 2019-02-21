package servlets;

import freemarker.FreemarkerCard;
import shopping.entity.CartItem;
import shopping.sql.DaoCartItemSQL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;

public class FreeCardServlet extends HttpServlet {
    private Connection conn;
    private final FreemarkerCard template;

    public FreeCardServlet(Connection conn, FreemarkerCard template) {
        this.conn = conn;
        this.template = template;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Collection<CartItem> list = new DaoCartItemSQL(conn).all();
        HashMap<String,Object> order = new HashMap<>();
        order.put("card", list);

        template.render("card.ftl", order, resp);
    }
}
