package order;

import commodity.OneOrder;
import database.DbConnection;
import user.DAO;


import java.sql.Connection;

public class DaoFabricOrder {

        public static DAO<OneOrder> buildInDatabase(){
            Connection conn = new DbConnection().connection();
            return new DaoOneOrderSQL(conn);

    }
}
