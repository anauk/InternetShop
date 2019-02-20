package user;

import commodity.Commodity;
import commodity.DaoCommoditySQL;
import database.DbConnection;

import java.sql.Connection;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Connection conn = new DbConnection().connection();
        DAO<User> dao1 = new DaoUserHashMap();
        DAO<User> dao2 = new DaoUserSQL(conn);
        //
        DAO<User> dao11 = DaoFabric.buildInMemory();
        DAO<User> dao22 = DaoFabric.buildInDatabase();
        //
        User u1 = new User(59, "Semen");
        User u2 = new User(60,"Maks", "Petrov", "qwerty", "q2w3e4");
        User u3 = new User(70,"Rita", "Pavlova", "asde", "p0o9i8");

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
        System.out.println(user2);
        DAO<Commodity> dBc = new DaoCommoditySQL(conn);
        dBc.put(new Commodity(148,"Viski", 45, 32));
        dBc.put(new Commodity(149,"Regata", 35, 29));
        dBc.put(new Commodity(150,"Rom", 33, 12));
        dBc.put(new Commodity(151,"Tecilla", 38, 10));
        List<Commodity> products = ((DaoCommoditySQL) dBc).getAllCommodities();
        for (Commodity product : products) {
            System.out.println(product.toString());
        }

    }
}
