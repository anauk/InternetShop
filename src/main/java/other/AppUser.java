package other;

import shopping.entity.Commodity;
import shopping.entity.User;
import shopping.fabric.DaoFabric;
import shopping.impl.DaoUserHashMap;
import shopping.sql.DaoCommoditySQL;
import database.DbConnection;
import shopping.sql.DaoUserSQL;
import shopping.dao.DAO;

import java.sql.Connection;

public class AppUser {
    public static void main(String[] args) {
       Connection conn = new DbConnection().connection();
         /*DAO<User> dao1 = new DaoUserHashMap();
        DAO<User> dao2 = new DaoUserSQL(conn);
        //
        DAO<User> dao11 = DaoFabric.buildInMemory();
        DAO<User> dao22 = DaoFabric.buildInDatabase();
        //
        User u1 = new User(71, "Semen");
        User u2 = new User(72,"Maks", "Petrov", "qwerty", "q2w3e4");
        User u3 = new User(73,"Rita", "Pavlova", "asde", "p0o9i8");

        System.out.println(u1);
        dao1.put(u1);
        dao2.put(u1);
        dao2.put(u2);
        dao2.put(u3);
        System.out.println(u2);
        System.out.println(u3);

        User user1 = dao1.get(1);
        System.out.println(user1);
        User user2 = dao2.get(1);
        System.out.println(user2);*/
        DAO<Commodity> dBc = new DaoCommoditySQL(conn);
        dBc.put(new Commodity(152,"Viski", 45, 32));
        dBc.put(new Commodity(153,"Regata", 35, 29));
        dBc.put(new Commodity(154,"Rom", 33, 12));
        dBc.put(new Commodity(155,"Tecilla", 38, 10));
        dBc.all();


    }
}
