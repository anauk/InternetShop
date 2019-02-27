package servlets;

import freemarker.FreemarkerCard;
import shopping.entity.CartInfo;
import shopping.entity.CartItem;
import shopping.sql.DaoCartItemSQL;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
    HashMap<String,Object> order = new HashMap<>();

    public FreeCardServlet(Connection conn, FreemarkerCard template) {
        this.conn = conn;
        this.template = template;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /* Cookie[] cookies = req.getCookies();
        if (cookies != null && cookies.length > 0) {
           for (Cookie c: cookies) {
               if( c.getName().equals("shop_cookie"){
               order.put("",c.getName().equals("shop_cookie");

               int idC = Integer.parseInt(c.getValue();
               String login=получить с дб.getLogin();
               order.put("login",login);

           }*/
        Collection<CartInfo> list = new DaoCartItemSQL(conn).getByUser(1);
//        list.forEach(p-> System.out.println(p.getPname()));

        order.put("card", list);

        template.render("cardf.ftl", order, resp);
    }
}
