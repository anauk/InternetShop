package servlets;

import com.sun.deploy.net.cookie.CookieUnavailableException;
import freemarker.FreemarkerCard;
import service.CardService;
import service.UserService;
import shopping.entity.CartInfo;
import shopping.entity.CartItem;
import utils.CookieMethods;
import utils.ParameterFromRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

public class FreeCardServlet extends HttpServlet {
    private final CardService cardService;
    private final UserService userService;
    private final CookieMethods cookieMethods;

    public FreeCardServlet(CardService cardService, UserService userService, CookieMethods cookieMethods) {
        this.cardService = cardService;
        this.userService = userService;
        this.cookieMethods = cookieMethods;
    }

    /*private Connection conn;
        private final FreemarkerCard template;
        HashMap<String,Object> order = new HashMap<>();

        public FreeCardServlet(Connection conn, FreemarkerCard template) {
            this.conn = conn;
            this.template = template;
        }*/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FreemarkerCard template = new FreemarkerCard();
        int userId = -1;
        try {
            userId = Integer.parseInt(cookieMethods.getValue(req));
            System.out.println(userId);
        } catch (CookieUnavailableException | NumberFormatException e) {
            resp.getWriter().printf("<html> <a href=\"/shop\"> You have tried to enter to cart in illegal way! %n %s </a></html>", e.getMessage());
        }
        String userName = userService.getUser(userId).getLogin();

        Collection<CartInfo> card = cardService.getByUser(userId);
        int summ = card.stream().mapToInt(cie -> cie.getPrice() * cie.getQuantity()).sum();
        int qntSum = card.stream().mapToInt(CartItem::getQuantity).sum();
        HashMap<String, Object> order = new HashMap<>();
        order.put("name", userName);
        order.put("card", card);
        order.put("qntSum", qntSum);
        order.put("summ", summ);

        template.render("cardf.ftl", order, resp);

        /*Collection<CartInfo> list = new DaoCartItemSQL(conn).getByUser(1);
//        list.forEach(p-> System.out.println(p.getPname()));

        order.put("card", list);

        template.render("cardf.ftl", order, resp);*/
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        int id = pfr.getInt("id");
        String action = pfr.getStr("action");

        switch (action) {
            case "sum":
                cardService.incQuantity(id);
                break;
            case "sub":
                cardService.decQuantity(id);
                break;
            case "delete":
                cardService.delete(id);
                break;
        }

        resp.sendRedirect("/cardf");
    }
}

