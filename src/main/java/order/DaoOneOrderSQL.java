package order;

import commodity.OneOrder;
import user.DAO;
import user.UserNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoOneOrderSQL implements DAO<OneOrder> {
    private final Connection conn;
    public DaoOneOrderSQL(Connection conn) {
        this.conn = conn;
    }
    @Override
    public OneOrder get(int idOrder) {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from public.order where idOrder = ?");
            ps.setInt(1,idOrder);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                return new OneOrder(
                        resultSet.getInt("idOrder"),
                        resultSet.getInt("idProduct"),
                        resultSet.getString("productName"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("price"),
                        resultSet.getInt("totalPrice")
                );
            } else {
                throw new UserNotFoundException("User is empty!");
            }

        }catch(SQLException e){
            throw new IllegalArgumentException("smth with order wrong", e);
        }
    }



    @Override
    public void put(OneOrder end) {
        try {
            PreparedStatement ps = conn.prepareStatement("insert into order (idOrder, idProduct, productName, quantity, price, totalPrice) values (?,?,?,?,?,?)");
            ps.setInt(1, end.getIdOrder());
            ps.setInt(2, end.getProductId());
            ps.setString(3, end.getProductName());
            ps.setInt(4, end.getQuantity());
            ps.setInt(5, end.getPrice());
            ps.setInt(6, end.getTotalPrice());
            ps.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException("smth with order went wrong", e);
        }
    }
}
