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

public class ListServletUnknown extends HttpServlet {
    private Connection conn;
    private final Freemarker fm;


    public ListServletUnknown(Connection conn, Freemarker fm) {
        this.conn = conn;
        this.fm = fm;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Collection<Commodity> list = new DaoCommoditySQL(conn).all();
        HashMap<String,Object> products = new HashMap<>();
        products.put("product", list);

        fm.render("injectionProductUnknown.ftl", products, resp);
    }
}
