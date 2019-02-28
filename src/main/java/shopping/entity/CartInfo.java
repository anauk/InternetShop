package shopping.entity;

public class CartInfo extends CartItem {
    private String pname;
    private int price;

    public CartInfo(int user_id, int commodity_id, String name, int quantity) {
        super(user_id, commodity_id, quantity);
        this.pname = name;
    }

    public CartInfo(int id, int user_id, int commodity_id, String name, int quantity, int price) {
        super(id, user_id, commodity_id, quantity);
        this.price = price;
        this.pname = name;
    }


    public String getPname() {
        return pname;
    }

    public void setPname(String name) {
        this.pname = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("CartInfo: id=%d, user_id=%d, commodity_id=%d, name=%s, quantity=%d, price=%d",
                id, user_id, commodity_id, pname, quantity, price);
    }
}
