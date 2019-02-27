package servlets;

import freemarker.FreemarkerCard;
import shopping.dao.UserStorage;
import shopping.entity.CartInfo;
import shopping.sql.DaoCartItemSQL;
import shopping.sql.DaoCommoditySQL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.Collection;

public class CardOperationServlet extends HttpServlet {
    private final UserStorage security;
    private final Connection connection;
    private final FreemarkerCard template1;

    public CardOperationServlet(UserStorage security, Connection connection, FreemarkerCard template1) {
        this.security = security;
        this.connection=connection;
        this.template1=template1;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Files.copy(Paths.get("cardf.ftl"), resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParameterFromRequest pfr = new ParameterFromRequest(req);
        String op = pfr.getStr("op");
        int id = pfr.getInt("id");

        if(op.equalsIgnoreCase("sum")){
            new DaoCartItemSQL(connection).incQuantity(id);
        } else if(op.equalsIgnoreCase("sub")){
            new DaoCartItemSQL(connection).decQuantity(id);
        } else if(op.equalsIgnoreCase("delete")){
            new DaoCartItemSQL(connection).delete(id);
        }

    }
}
