package service;

import shopping.entity.CartInfo;
import shopping.entity.CartItem;
import shopping.entity.Commodity;
import shopping.sql.DaoCartItemSQL;

import java.util.Collection;

public class CardService {
    private final DaoCartItemSQL cards;

    public CardService(DaoCartItemSQL cards) {
        this.cards = cards;
    }
    public CartItem get(int id){
        return cards.get(id);
    }
    public void put(CartItem elem){
        cards.put(elem);
    }
    public Collection<CartItem> all(){
        return cards.all();
    }
    public void delete(int id){
        cards.delete(id);
    }
    public boolean isEmpty(){
        return cards.isEmpty();
    }
    public void putChecked(int userId, int commodityId) {

        final int[] id = {0};

        cards.getByUser(userId).stream()
                .filter(ci -> ci.getCommodity_id() == commodityId)
                .findFirst()
                .ifPresent(ci -> id[0] = ci.getId());

        if (id[0] == 0){
            cards.put(new CartItem(userId, commodityId, 1));
        }
        else {
            cards.incQuantity(id[0]);
        }
    }
    public void setQuantity(int quantity, int id){
        cards.setQuantity(quantity,id);
    }
    public void incQuantity(int id){
        cards.incQuantity(id);
    }
    public void decQuantity(int id){
        cards.decQuantity(id);
    }
    public Collection<CartInfo> getByUser(int userId){
        return cards.getByUser(userId);
    }


}
