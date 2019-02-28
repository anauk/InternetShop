import database.DbConnection;
import freemarker.Freemarker;
import freemarker.FreemarkerCard;
import freemarker.FreemarkerUser;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import service.CardService;
import service.CommodityService;
import service.UserService;
import servlets.*;
import servlets.filters.IsSuchUser;
import servlets.filters.LoginPasswordFilter;
import servlets.filters.NoUsersFilter;
import servlets.registration.AuthServlet;
import servlets.registration.LogOutServlet;
import servlets.registration.LoginServlet;
import shopping.dao.UserStorage;
import shopping.impl.DaoUserHashMap;
import shopping.impl.UserStoragImpl;
import shopping.sql.DaoCartItemSQL;
import shopping.sql.DaoCommoditySQL;
import shopping.sql.DaoUserSQL;
import utils.CookieMethods;

import javax.servlet.DispatcherType;
import java.sql.Connection;
import java.util.EnumSet;

public class Main {
    public static void main(String[] args) throws Exception {
        ServletContextHandler handler = new ServletContextHandler();
        Connection connection = new DbConnection().connection();
        DaoUserHashMap db = new DaoUserHashMap();
        UserStorage security = new UserStoragImpl();

        UserService userService = new UserService(new DaoUserSQL(connection));
        CookieMethods cm = new CookieMethods("my_shop", userService);
        CardService cardService = new CardService(new DaoCartItemSQL(connection));
        CommodityService comService = new CommodityService(new DaoCommoditySQL(connection));
        Freemarker template = new Freemarker("./templ");
        FreemarkerCard template1 = new FreemarkerCard("./templ");


        handler.addServlet(AssertServlet.class, "/assert/*");

        handler.addServlet(new ServletHolder(new LoginServlet()), "/reg/*");
        handler.addServlet(new ServletHolder(new LogOutServlet()), "/logout/*");
        handler.addServlet(new ServletHolder(new ListServletUnknown(connection, template)), "/list/*");
        handler.addServlet(new ServletHolder(new ListServletAuth(connection, template)), "/listauth/*");
        handler.addServlet(new ServletHolder(new AuthServlet(userService)), "/auth/*");
        handler.addServlet(new ServletHolder(new ShopServlet(cardService, comService, cm)), "/shop/*");

        //handler.addFilter(new FilterHolder(new IsSuchUser(userService)),"/auth/*", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));
        //handler.addServlet(new ServletHolder(new FreeProductServlet(connection, template)), "/product");
        //handler.addFilter(new FilterHolder(new NoUsersFilter(userService)), "/reg", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new LoginPasswordFilter(userService)), "/reg", EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));
        handler.addServlet(new ServletHolder(new FreeUserServlet(connection)), "/user");
        handler.addServlet(new ServletHolder(new FreeCardServlet(cardService, userService, cm)), "/cardf");


        //handler.addFilter(LoginFilter.class, "/reg/*", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));
        //handler.addFilter(AuthFilter.class, "/auth/*", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));

        Server server = new Server(81);
        server.setHandler(handler);
        server.start();
        server.join();
    }
}
