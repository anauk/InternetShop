package other.order;

import other.commoddity.Order;
import shopping.dao.DAO;

import java.util.Collection;

public class DaoOrderCookie implements DAO<Order> {
    @Override
    public Order get(int id) {
        return null;
    }

    @Override
    public void put(Order end) {
        //нужно сохранить заказ в куках?

    }

    @Override
    public Collection<Order> all() {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
