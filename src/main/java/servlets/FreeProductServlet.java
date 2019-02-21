package servlets;

import shopping.entity.Commodity;
import shopping.sql.DaoCommoditySQL;
import freemarker.Freemarker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;

public class FreeProductServlet extends HttpServlet {
    private Connection conn;
    private final Freemarker template;

    public FreeProductServlet(Connection conn, Freemarker template) {
        this.conn = conn;
        this.template = template;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Collection<Commodity> list = new DaoCommoditySQL(conn).all();
        HashMap<String,Object> products = new HashMap<>();
        products.put("product", list);

        template.render("injectionProduct.ftl", products, resp);
    }
}
