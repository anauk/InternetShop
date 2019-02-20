package user;

import database.DbConnection;

import java.sql.Connection;

public class DaoFabric {
    public static DAO<User> buildInMemory(){
        return new DaoUserHashMap();
    }
    public static DAO<User> buildInDatabase(){
        Connection conn = new DbConnection().connection();
        return new DaoUserSQL(conn);
    }
}
