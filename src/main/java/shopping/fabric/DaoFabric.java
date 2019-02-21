package shopping.fabric;

import database.DbConnection;
import shopping.entity.User;
import shopping.impl.DaoUserHashMap;
import shopping.sql.DaoUserSQL;
import shopping.dao.DAO;

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
