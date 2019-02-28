package servlets;


import com.sun.deploy.net.cookie.CookieUnavailableException;
import freemarker.Freemarker;
import service.CardService;
import service.UserService;
import shopping.dao.UserStorage;
import shopping.entity.CartInfo;
import shopping.entity.CartItem;
import shopping.sql.DaoCartItemSQL;
import utils.CookieMethods;
import utils.ParameterFromRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;

public class CardOperationServlet extends HttpServlet {
    private final CardService cardService;
    private final UserService userService;
    private final CookieMethods cm;

    public CardOperationServlet(CardService cardService, UserService userService, CookieMethods cm) {
        this.cardService = cardService;
        this.userService = userService;
        this.cm = cm;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Freemarker f = new Freemarker();
        int user_id = -1;
        try {
            user_id = Integer.parseInt(cm.getValue(req));
        } catch (CookieUnavailableException | NumberFormatException e) {
            resp.getWriter().printf("<html> <a href=\"/shop\"> You have tried to enter to cart in illegal way! %n %s </a></html>", e.getMessage());
        }
        String userName = userService.getUser(user_id).getLogin();

        Collection<CartInfo> card = cardService.getByUser(user_id);
        int summ = card.stream().mapToInt(cie -> cie.getPrice() * cie.getQuantity()).sum();
        int qntSum = card.stream().mapToInt(CartItem::getQuantity).sum();
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", userName);
        data.put("card", card);
        data.put("qntSum", qntSum);
        data.put("summ", summ);

        f.render("cardf.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        int id = pfr.getInt("id");
        String action = pfr.getStr("action");

        switch (action) {
            case "add":
                cardService.incQuantity(id);
                break;
            case "sub":
                cardService.decQuantity(id);
                break;
            case "delete":
                cardService.delete(id);
                break;
        }

        resp.sendRedirect("cardf");


    }
}
