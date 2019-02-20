package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListServlet extends HttpServlet {
    private Connection conn;

    public ListServlet(Connection conn) {
        this.conn = conn;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        try {
            String sql = "SELECT * FROM commodities";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
              int id = resultSet.getInt("id");
              String name = resultSet.getString("name");
              int price = resultSet.getInt("price");
              int quantity = resultSet.getInt("quantity");
              sb.append(id).append(name).append(price).append(quantity);
            }
        } catch (SQLException e) {
           throw new IllegalArgumentException("Db table commodity empty",e);
        }
        resp.sendRedirect("/list");
    }
}
