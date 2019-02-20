package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoUserSQL implements DAO<User> {
    private final Connection conn;
    public DaoUserSQL(Connection conn) {
        this.conn = conn;
    }

    @Override
    public User get(int id) {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from public.users where id = ?");
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("sername"),
                        resultSet.getString("login"),
                        resultSet.getString("password")
                );
            } else {
                throw new UserNotFoundException("User is empty!");
            }

        }catch(SQLException e){
            throw new IllegalArgumentException("smth wrong", e);
        }
    }

    @Override
    public void put(User end) {
        try {
            PreparedStatement ps = conn.prepareStatement("insert into users(id,name,sername,login,password) values (?,?,?,?,?)");
            ps.setInt(1, end.getId());
            ps.setString(2, end.getName());
            ps.setString(3, end.getSurname());
            ps.setString(4, end.getLogin());
            ps.setString(5, end.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("smth went wrong", e);
        }

    }
}
