package commodity;

import user.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DaoCard implements DAO<Commodity> {
    private List<Commodity> products = new ArrayList<>();//список товаров
    private int itemCount;//количество одного товара
    private int totalCount;//количество всех товаров
    private int itemPrice;//сумма одного товара
    private int totalPrice;//общая сумма товаров

//получить список товаров
    public List<Commodity> getAllProducts(){
        return products;
    }

//получить товар по индексу
    @Override
    public Commodity get(int id) {
        return products.get(id);
    }
//добавить товар
    @Override
    public void put(Commodity com) {
        products.add(com);
    }
    public void addToCard(int id, String itemName, int itemPrice, int quantity){
        Commodity item = new Commodity(id, itemName, itemPrice, quantity);
        totalPrice += (itemPrice*quantity);
        itemCount += quantity;
        products.add(item);
    }

    //удалить товар
    public boolean deleteCommodity(Commodity com){
        return products.remove(com);
    }
    //подсчитать количество одного продукта
    public int getProductCount(Commodity com){
        return 0;
    }
    //подсчитать количнство всех продуктов
    public int getTotalCount(){
        return products.size();
    }
    //подсчитать сумму одного продукта

    //показать список продуктов, их количество, сумму
    //показать сумму всех продуктов
    // сумма товаров в заказе
    public int getSumOfProductsInCard(List<Commodity> list){
        int sum = 0;
        for (Commodity commodity : list) {
            Integer price = (commodity.getPrice());
            if (price != null) {
                sum += price;
            }
        }
        return sum;
    }


}
