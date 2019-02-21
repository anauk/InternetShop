package other.order;

import other.commoddity.OneOrder;
import database.DbConnection;
import shopping.entity.CartItem;
import shopping.exception.CardOrderNotFoundException;
import shopping.sql.DaoCartItemSQL;
import shopping.dao.DAO;

import java.sql.Connection;

public class AppOrder {
    public static void main1(String[] args) throws CardOrderNotFoundException {
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

    public static void main(String[] args) throws CardOrderNotFoundException {
        Connection conn = new DbConnection().connection();
        DaoCartItemSQL dao = new DaoCartItemSQL(conn);
        // add other.commodity 5 to user 3 10pcs
        CartItem order1 = new CartItem(7, 140, 9);
        CartItem order2 = new CartItem(3, 132, 9);
        CartItem order3 = new CartItem(1, 140, 9);
        dao.put(order1);
        dao.put(order2);
        dao.put(order3);
        System.out.println(order1.toString());
        System.out.println(order2.toString());
        System.out.println(order3.toString());
        //System.out.println(dao.toString());
        dao.all();

        // get other.commodity for user 3
        dao.getByUser(4);
        CartItem ci1 = dao.get(6);
        System.out.println(ci1);

        // delete whole record by id
        dao.delete(1);

        // change quantity 20 for record id = 90
        dao.setQuantity(20, 5);
        dao.incQuantity(132);
        dao.decQuantity(5);
        System.out.println(dao);
    }
}
