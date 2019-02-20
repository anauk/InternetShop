import database.DbConnection;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.*;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) throws Exception {
        ServletContextHandler handler = new ServletContextHandler();
        Connection connection = new DbConnection().connection();

        handler.addServlet(new ServletHolder(new LoginServlet()), "/reg/*");
        handler.addServlet(new ServletHolder(new LogOutServlet()), "/logout/*");
        handler.addServlet(new ServletHolder(new ListServlet(connection)), "/list/*");
        handler.addServlet(new ServletHolder(new AuthServlet(connection)), "/auth/*");
        handler.addServlet(new ServletHolder(new ShopServlet()), "/shop/*");
        handler.addServlet(new ServletHolder(new CardServlet()), "/card/*");

        Server server = new Server(81);
        server.setHandler(handler);
        server.start();
        server.join();
    }
}
