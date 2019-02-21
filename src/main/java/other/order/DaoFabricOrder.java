package other.order;

import other.commoddity.OneOrder;
import database.DbConnection;
import shopping.dao.DAO;


import java.sql.Connection;

public class DaoFabricOrder {

        public static DAO<OneOrder> buildInDatabase(){
            Connection conn = new DbConnection().connection();
            return new DaoOneOrderSQL(conn);

    }
}
