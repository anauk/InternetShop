package shopping.sql;

import shopping.entity.Commodity;
import shopping.dao.DAO;
import shopping.exception.UserNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DaoCommoditySQL implements DAO<Commodity> {
    private final Connection connection;
    public DaoCommoditySQL(Connection conn) {
        this.connection = conn;
    }

    @Override
    public Commodity get(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from public.commodities where id = ?");
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new Commodity(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getInt("quantity")
                );
            } else {
                throw new UserNotFoundException("Commodity is empty!");
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("smth went wrong", e);
        }
    }

    @Override
    public void put(Commodity com) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into fs7.public.commodities(id,name,price,quantity) values (?,?,?,?)");
            ps.setInt(1, com.getId());
            ps.setString(2, com.getName());
            ps.setInt(3, com.getPrice());
            ps.setInt(4, com.getQuantity());
            ps.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Something wrong", e);
        }
    }

    @Override
    public Collection<Commodity> all() {
        try (Statement statement = connection.createStatement()) {
            List<Commodity> products = new ArrayList<>();
            ResultSet rs = statement.executeQuery("select * from fs7.public.commodities");
            while (rs.next()) {
                products.add(new Commodity(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("price")));
            }
            return products;

        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    @Override
    public void delete(int id) {
        try (PreparedStatement ps = connection.prepareStatement("delete from fs7.public.commodities where id = ?")) {
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Commodity was not delete", e);
        }
    }
}
