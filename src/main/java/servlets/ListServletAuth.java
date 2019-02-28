package servlets;

import shopping.entity.Commodity;
import shopping.sql.DaoCommoditySQL;
import freemarker.Freemarker;
import utils.ParameterFromRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;

public class ListServletAuth extends HttpServlet {
    private Connection conn;
    private final Freemarker fm;


    public ListServletAuth(Connection conn, Freemarker fm) {
        this.conn = conn;
        this.fm = fm;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<Commodity> list = new DaoCommoditySQL(conn).all();
        HashMap<String,Object> products = new HashMap<>();
        products.put("product", list);

        fm.render("injectionProduct.ftl", products, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        String action = pfr.getStr("add");
        //хочу остаться в списке товаров
        if(action.equalsIgnoreCase("add")) {
            // сделать добавление в корзину
            resp.sendRedirect("/listauth");
        }
    }
}
