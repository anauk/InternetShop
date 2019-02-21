package shopping.entity;

import java.util.ArrayList;
import java.util.List;

public class Commodity {
    private int id;
    private String name;
    private int price;
    private int quantity;
    public Commodity(){

    }
    public Commodity(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Commodity(int id, String name, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

//private List<OrderCommodity> oc = new ArrayList<>();

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Roduct: %s | Price: %d", this.id, this.name, this.price);
    }
}
