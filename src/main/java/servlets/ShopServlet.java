package servlets;

import commodity.DaoCard;
import commodity.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ShopServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Files.copy(Paths.get("listCom.html"), resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DaoCard order = new DaoCard();
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
        }

    }
}
