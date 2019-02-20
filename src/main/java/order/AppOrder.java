package order;

import commodity.OneOrder;
import database.DbConnection;
import user.*;

import java.sql.Connection;

public class AppOrder {
    public static void main(String[] args) {
        Connection conn = new DbConnection().connection();
        DAO<OneOrder> daoDB = new DaoOneOrderSQL(conn);
        //
        DAO<OneOrder> dao11 = DaoFabricOrder.buildInDatabase();
        OneOrder o1 = new OneOrder(1, 1, "Coca-cole", 2, 5, 10);
        System.out.println(o1);
        daoDB.put(o1);
        OneOrder oneOrder = daoDB.get(124);
        System.out.println(oneOrder);

    }
}
