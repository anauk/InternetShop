package commodity;

import java.util.List;
import java.util.Objects;

public class OrderManager {
    private List<Commodity> commodities;
    private List<Order> orders;

    //создать заказ
    public Order createOrder(){
        Order order = new Order();
        return order;
    }
    //создать товар
    public Commodity createCommodity(String name, int price){
        Commodity commodity = new Commodity();
        commodity.setName(name);
        commodity.setPrice(price);
        return commodity;
    }
    //добавить товар в конзину
    public boolean addCommodityToOrder(){
        return false;
    }
    //найти товар по id
    public Commodity getCommodityById(int id){
        Commodity product = null;
        for(Commodity com:commodities){
            if(com.getId() == id){
                product = com;
            }
        }
        return product;
    }
    //вычисляем количество товара, что остался после продаж
    public int getCountSolidCommodity(){
        int count = 0;
        for(Order order:orders){
            count += order.getCommodities().size();
        }
        return count;
    }
    // сумма товаров в заказе
    public int getSumOfSoldCommodityInOrder(Order order){
        return order.getCommodities().stream()
                .map(commodity -> getCommodityById(commodity.getPrice()))
                .filter(Objects::nonNull).mapToInt(Commodity::getPrice)
                .sum();
    }

}
