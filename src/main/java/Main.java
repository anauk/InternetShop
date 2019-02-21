import database.DbConnection;
import freemarker.Freemarker;
import freemarker.FreemarkerCard;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.*;
import shopping.impl.DaoUserHashMap;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) throws Exception {
        ServletContextHandler handler = new ServletContextHandler();
        Connection connection = new DbConnection().connection();
        DaoUserHashMap db = new DaoUserHashMap();
        Freemarker template = new Freemarker("./templ");
        FreemarkerCard template1 = new FreemarkerCard("./templ");

        handler.addServlet(AssertServlet.class, "/assert/*");

        handler.addServlet(new ServletHolder(new LoginServlet(db)), "/reg/*");
        handler.addServlet(new ServletHolder(new LogOutServlet(template)), "/logout/*");
        handler.addServlet(new ServletHolder(new ListServletUnknown(connection, template)), "/list/*");
        handler.addServlet(new ServletHolder(new ListServletAuth(connection, template)), "/listauth/*");
        handler.addServlet(new ServletHolder(new AuthServlet(connection)), "/auth/*");
        handler.addServlet(new ServletHolder(new ShopServlet()), "/shop/*");
        handler.addServlet(new ServletHolder(new CardServlet()), "/card/*");
        handler.addServlet(new ServletHolder(new FreeProductServlet(connection, template)), "/product");
        handler.addServlet(new ServletHolder(new FreeUserServlet(connection)), "/user");
        handler.addServlet(new ServletHolder(new FreeCardServlet(connection,template1)), "/card");


        Server server = new Server(81);
        server.setHandler(handler);
        server.start();
        server.join();
    }
}
