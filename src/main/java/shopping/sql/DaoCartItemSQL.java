package shopping.sql;

import shopping.entity.CartInfo;
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
            PreparedStatement ps = conn.prepareStatement("select id, id_user, commodity_id, quantity from cardorder WHERE id = ?");
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new CartItem(
                        resultSet.getInt("id"),
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
            ResultSet rs = statement.executeQuery("select id, id_user, commodity_id, quantity from fs7.public.cardorder");
            while (rs.next()) {
                int id = rs.getInt("id");
                int user_id = rs.getInt("id_user");
                int com_id = rs.getInt("commodity_id");
                int qu = rs.getInt("quantity");
                CartItem cartItem = new CartItem(id, user_id, com_id, qu);
                orders.add(cartItem);
                System.out.printf("%d : %d : %d : %d\n", id, user_id, com_id, qu);
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
            PreparedStatement ps = conn.prepareStatement("DELETE FROM cardorder WHERE id=?");
            //get(id);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Order was not delete", e);
        }
    }

    @Override
    public boolean isEmpty() {
        int count = 0;
        try (Statement stmt = conn.createStatement()) {
            String query = "select count(*) from fs7.public.cardorder";
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                count = resultSet.getInt("COUNT(*)");
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException("smth went wrong", e);
        }
        return count == 0;

    }

    public Collection<CartInfo> getByUser(int user_id) {
        ArrayList<CartInfo> cartInfo = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "select * from cardorder"
                            + " join commodity on commodity.id=cardorder.commodity_id"
                            // + " join users on cardorder.id_user=users.id"
                            + " WHERE id_user = ?");
            ps.setInt(1, user_id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int idOrder = resultSet.getInt("id");
                int idUser = resultSet.getInt("id_user");
                int idProduct = resultSet.getInt("commodity_id");
                String name = resultSet.getString("name");
                int quantity = resultSet.getInt("quantity"); // commodity.id
                int price = resultSet.getInt("price");

                CartInfo cardInfo = new CartInfo(idOrder, idUser, idProduct, name, quantity, price);
                cartInfo.add(cardInfo);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("smth wrong with search cardItem by user_id ", e);
        }
        return cartInfo;
    }

    public void setQuantity(int quantity, int id) throws IllegalArgumentException {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be 1 or more");
        }
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE cardorder SET quantity = ? WHERE commodity_id = ?");
            ps.setInt(1, quantity);
            ps.setInt(2, id);
            ps.executeQuery();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error in set quantity", e);
        }
    }

    public void incQuantity(int id) throws IllegalArgumentException {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE cardorder SET quantity = quantity+1 WHERE commodity_id = ?");
            ps.setInt(1, id);
            ps.executeQuery();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error in add quantity", e);
        }
    }

    public void decQuantity(int id) throws IllegalArgumentException {
        try {
            if (get(id).getQuantity() > 1) {
                PreparedStatement ps = conn.prepareStatement("UPDATE cardorder SET quantity = quantity-1 WHERE commodity_id = ?");
                ps.setInt(1, id);
                ps.executeQuery();
            } else if (get(id).getQuantity() == 1) {
                delete(id);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Error in sub quantity", e);
        }
    }

}
