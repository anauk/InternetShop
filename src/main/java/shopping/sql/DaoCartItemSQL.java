package shopping.sql;
import shopping.exception.CardOrderNotFoundException;
import shopping.entity.CartItem;
import shopping.dao.DAO;


import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DaoCartItemSQL implements DAO<CartItem> {
    private final Connection conn;

    public DaoCartItemSQL(Connection conn) {
        this.conn = conn;
    }

    @Override
    public CartItem get(int id) throws CardOrderNotFoundException {
        try {
            PreparedStatement ps = conn.prepareStatement("select id_order, id_user, commodity_id, quantity from cardorder WHERE id_order = ?");
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new CartItem(
                        resultSet.getInt("id_order"),
                        resultSet.getInt("id_user"),
                        resultSet.getInt("commodity_id"),
                        resultSet.getInt("quantity")
                );
            } else {
                throw new CardOrderNotFoundException("CardOrder is empty!");
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("smth with cardOrder wrong", e);
        }
    }

    @Override
    public void put(CartItem ent) {
        try {
            PreparedStatement ps = conn.prepareStatement("insert into cardorder(id_user, commodity_id, quantity) values (?,?,?)");
            ps.setInt(1, ent.getUser_id());
            ps.setInt(2, ent.getCommodity_id());
            ps.setInt(3, ent.getQuantity());
            ps.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Something went wrong with put other.commodity.order to cardItem ");
        }
    }

    @Override
    public Collection<CartItem> all() {
        try (Statement statement = conn.createStatement()) {
            List<CartItem> orders = new ArrayList<>();
            ResultSet rs = statement.executeQuery("select id_order, id_user, commodity_id, quantity from fs7.public.cardorder");
            while (rs.next()) {
                int id= rs.getInt("id_order");
                 int user_id = rs.getInt("id_user");
                        int com_id = rs.getInt("commodity_id");
                        int qu = rs.getInt("quantity");
                CartItem cartItem = new CartItem(id, user_id, com_id, qu);
                orders.add(cartItem);
                System.out.printf("%d : %d : %d : %d\n", id,user_id,com_id, qu);
            }

            return orders;

        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void delete(int id) throws CardOrderNotFoundException {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM cardorder WHERE id_order=?");
            //get(id);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Order was not delete", e);
        }
    }

    public Collection<CartItem> getByUser(int user_id) {
        ArrayList<CartItem> cartItems = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select id_order, id_user, commodity_id, quantity from cardorder WHERE id_user = ?");
            ps.setInt(1, user_id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                int idOrder = resultSet.getInt("id_order");
                int idUser = resultSet.getInt("id_user");
                int idProduct = resultSet.getInt("commodity_id");
                int quantity = resultSet.getInt("quantity");

                CartItem cardItem = new CartItem(idOrder, idUser, idProduct, quantity);
                cartItems.add(cardItem);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("smth wrong with search cardItem by user_id ", e);
        }
        return cartItems;
    }
    public void setQuantity(int quantity, int id) {
        try{
            PreparedStatement ps = conn.prepareStatement("UPDATE cardorder SET quantity = ? WHERE commodity_id = ?");
            ps.setInt(1, quantity);
            ps.setInt(2, id);
            ps.executeQuery();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error in set quantity", e);
        }
    }

    public void incQuantity(int id) {
        try{
            PreparedStatement ps = conn.prepareStatement("UPDATE cardorder SET quantity = quantity+1 WHERE commodity_id = ?");
            ps.setInt(1, id);
            ps.executeQuery();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error in add quantity", e);
        }
    }

    public void decQuantity(int id) {
        try{
            PreparedStatement ps = conn.prepareStatement("UPDATE cardorder SET quantity = quantity-1 WHERE commodity_id = ?");
            ps.setInt(1, id);
            ps.executeQuery();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error in sub quantity", e);
        }
    }

}
