package other.commoddity;

import shopping.entity.Commodity;

public class OrderCommodity {
private int id;
private Commodity commodity;
private Order order;
private int quantity;

    public int getId() {
        return id;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public Order getOrder() {
        return order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
