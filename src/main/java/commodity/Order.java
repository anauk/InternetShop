package commodity;

import java.util.List;

// модель заказа
public class Order {
    private int id; //уникальный номер заказа
    private int userId; //уникальный номер покупателя(юзера)
    private List<Commodity> commodities; // список купленных товаров
    public Order(){

    }

    public Order(int id, int userId, List<Commodity> commodities) {
        this.id = id;
        this.userId = userId;
        this.commodities = commodities;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public List<Commodity> getCommodities() {
        return commodities;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCommodities(List<Commodity> commodities) {
        this.commodities = commodities;
    }
}
