package servlets;

import com.sun.deploy.net.cookie.CookieUnavailableException;
import freemarker.Freemarker;
import service.CardService;
import service.CommodityService;
import shopping.entity.Commodity;
import utils.CookieMethods;
import utils.ParameterFromRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

public class ShopServlet extends HttpServlet {
    private final CardService cardService;
    private final CommodityService cmS;
    private final CookieMethods cm;

    public ShopServlet(CardService cardService, CommodityService cmS, CookieMethods cm) {
        this.cardService = cardService;
        this.cmS = cmS;
        this.cm = cm;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Freemarker f = new Freemarker();
        Collection<Commodity> a2 = cmS.all();
        HashMap<String, Object> data = new HashMap<>();
        data.put("products", a2);
        if (cm.cookieMatch(req)) {
            data.put("registered", "true");
        }
        f.render("listCom.ftl", data, resp);

        //Files.copy(Paths.get("listCom.ftl"), resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*DaoCard order = new DaoCard();
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        String action = pfr.getStr("action");
        int id = pfr.getInt("id");
        String name = pfr.getStr("name");
        int price = pfr.getInt("price");
        int quantity = 1;
        //добавить или удалить товар
        if ((action!=null)&&(id != 0)){
            if("add".equals(action)){
                order.addToCard(id, name,price,quantity);
            } else if("remove".equals(action)){
                order.addToCard(id, name, price, quantity);
            }
        }*/
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        int item_id = pfr.getInt("item_id");

        try {
            int user_id = Integer.parseInt(cm.getValue(req));
            cardService.putChecked(user_id, item_id);

        } catch (CookieUnavailableException | NumberFormatException e) {
            resp.getWriter().printf("<html> <a href=\"/shop\"> You have made a request in illegal way! %n %s </a></html>", e.getMessage());
        }
        doGet(req, resp);
    }
}

