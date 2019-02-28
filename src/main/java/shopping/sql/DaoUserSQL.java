package shopping.sql;

import shopping.dao.DAO;
import shopping.entity.User;
import shopping.exception.UserNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DaoUserSQL implements DAO<User> {
    private final Connection conn;

    public DaoUserSQL(Connection conn) {
        this.conn = conn;
    }

    public Collection<User> all() {
        List<User> list = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from fs7.public.users");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                User user = new User(id, name, surname, login, password);
                list.add(user);

                //System.out.printf("%d : %s : %s : %s: %s\n", id,name,surname, login, password);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("smth with all table wrong", e);
        }
        System.out.println(list);
        return list;
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = conn.prepareStatement("delete from fs7.public.users where id = ?")) {
            get(id);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException("User was not delete", e);
        }
    }

    @Override
    public boolean isEmpty() {
        int count = 0;
        try {
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("select count (*) from fs7.public.users ");
        while(resultSet.next()){
            count = resultSet.getInt("count(*)");
        }
        //resultSet.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException("smth wrong", e);
        }
        System.out.println(count);
        return count == 0;
    }

    @Override
    public User get(int id) {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from fs7.public.users where id = ?");
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("login"),
                        resultSet.getString("password")
                );
            } else {
                throw new UserNotFoundException("User is empty!");
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException("smth wrong", e);
        }
    }
    /*public int get(String login, String password){
        try {
            PreparedStatement ps = conn.prepareStatement("select * from fs7.public.users where (login, password) = (?,?)");
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            } else {
                throw new UserNotFoundException("Such user is emty!");
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("smth with method detId user wrong", e);
        }
    }*/

    @Override
    public void put(User end) {
        try {
            PreparedStatement ps = conn.prepareStatement("insert into users(name,surname,login,password,id) values (?,?,?,?,?)");
            ps.setString(1, end.getName());
            ps.setString(2, end.getSurname());
            ps.setString(3, end.getLogin());
            ps.setString(4, end.getPassword());
            ps.setInt(5, end.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException("smth went wrong", e);
        }
    }


}
