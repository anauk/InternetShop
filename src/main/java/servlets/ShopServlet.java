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

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ParameterFromRequest pfr = new ParameterFromRequest(req);
        int commodityId = pfr.getInt("id");

        try {
            int userId = Integer.parseInt(cm.getValue(req));
            cardService.putChecked(userId, commodityId);

        } catch (CookieUnavailableException | NumberFormatException e) {
            resp.getWriter().printf("<html> <a href=\"/shop\"> You have made a request in illegal way! %n %s </a></html>", e.getMessage());
        }
        doGet(req, resp);
    }
}

