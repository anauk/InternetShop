package order;

import commodity.Commodity;

import java.util.ArrayList;
import java.util.List;

public class CardInfo {
    private int idOrder;
    private int idUser;
    private List<Commodity> cardProducts = new ArrayList<>();
    public CardInfo(){

    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public List<Commodity> getCardProducts() {
        return cardProducts;
    }

    public void setCardProducts(List<Commodity> cardProducts) {
        this.cardProducts = cardProducts;
    }
    //найти продукт по id
    private Commodity findProductById(int id){
        for(Commodity com:cardProducts){
            if(com.getId() == (id)){
                return com;
            }
        }
        return null;
    }
    //добавить продукт
    public void addProduct(Commodity com, int quantity){
        Commodity product = findProductById(com.getId());
        if(product == null){
            product = new Commodity();
            product.setQuantity(0);
            product.setName(com.getName());
            cardProducts.add(product);
        }
        int newQuantity=product.getQuantity()+quantity;
        if(newQuantity<=0){
            cardProducts.remove(product);
        } else{
            product.setQuantity(newQuantity);
        }
    }
    //удалить продукт
    public void removeProduct(Commodity com){
        Commodity productById = findProductById(com.getId());
        if(productById != null){
            cardProducts.remove(productById);
        }
    }
    //получить общее количество продукта
    public int getQuantityTotal(){
        int quantity = 0;
        for( Commodity com: cardProducts){
            quantity += com.getQuantity();
        }
        return quantity;
    }
    //получить общую сумму продукта
    public int getTotalPrice(){
        int total = 0;
        for(Commodity com: cardProducts){
            total+=com.getPrice();
        }
        return total;
    }
    //обновить количество товара
    public void updateQuantity(CardInfo card){
        if(card!=null){
            List<Commodity> cardProducts = card.getCardProducts();
            for(Commodity cardProduct:cardProducts){
                updateProduct(cardProduct.getId(),cardProduct.getQuantity());
            }
        }
    }
    // обновить продукт
    private void updateProduct(int id, int quantity) {
        Commodity line = findProductById(id);

        if (line != null) {
            if (quantity <= 0) {
                cardProducts.remove(line);
            } else {
                line.setQuantity(quantity);
            }
        }
    }

}
