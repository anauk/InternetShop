package service;

import shopping.dao.DAO;
import shopping.entity.Commodity;

import java.util.Collection;

public class CommodityService {
    DAO<Commodity> commodities;

    public CommodityService(DAO<Commodity> commodities) {
        this.commodities = commodities;
    }
    public void put(Commodity com){
        commodities.put(com);
    }
    public void put(int id, String name, int price){
        Commodity commodity = new Commodity(id, name, price);
        commodities.put(commodity);
    }
    public void get(int id){
        commodities.get(id);
    }
    public Collection<Commodity> all(){
        return commodities.all();
    }
    public void delete(int id){
        commodities.delete(id);
    }
    public boolean isCommodityEmpty(){
        return commodities.isEmpty();
    }

}
