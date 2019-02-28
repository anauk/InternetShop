package other.order;

import other.commoddity.OneOrder;
import shopping.dao.DAO;
import shopping.exception.UserNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class DaoOneOrderSQL implements DAO<OneOrder> {
    private final Connection conn;

    public DaoOneOrderSQL(Connection conn) {
        this.conn = conn;
    }

    @Override
    public OneOrder get(int idOrder) {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from public.order where idOrder = ?");
            ps.setInt(1, idOrder);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
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

        } catch (SQLException e) {
            throw new IllegalArgumentException("smth with other.commodity.order wrong", e);
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
            throw new IllegalArgumentException("smth with other.commodity.order went wrong", e);
        }
    }

    @Override
    public Collection<OneOrder> all() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
