package shopping.entity;

public class CartItem {
    protected int id;
    protected int user_id;
    protected int commodity_id;
    protected int quantity;

    public CartItem(int user_id, int commodity_id, int quantity) {
        this.id = -1;
        this.user_id = user_id;
        this.commodity_id = commodity_id;
        this.quantity = quantity;
    }

    public CartItem(int id, int user_id, int commodity_id, int quantity) {
        this.id = id;
        this.user_id = user_id;
        this.commodity_id = commodity_id;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(int commodity_id) {
        this.commodity_id = commodity_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", commodity_id=" + commodity_id +
                ", quantity=" + quantity +
                '}';
    }
}
