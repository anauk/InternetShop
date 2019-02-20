package order;

import commodity.Commodity;
import commodity.Order;
import user.DAO;

public class DaoOrderCookie implements DAO<Order> {
    @Override
    public Order get(int id) {
        return null;
    }

    @Override
    public void put(Order end) {
        //нужно сохранить заказ в куках?

    }
}
