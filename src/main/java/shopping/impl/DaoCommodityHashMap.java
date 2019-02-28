package shopping.impl;

import shopping.entity.Commodity;
import shopping.dao.DAO;

import java.util.Collection;
import java.util.HashMap;

public class DaoCommodityHashMap implements DAO<Commodity> {
    private final HashMap<Integer,Commodity> storage = new HashMap<>();
    @Override
    public Commodity get(int id) {
        return storage.get(id);
    }

    @Override
    public void put(Commodity end) {
        storage.put(end.getId(), end);

    }

    @Override
    public Collection<Commodity> all() {
        return storage.values();
    }

    @Override
    public void delete(int id) {
        storage.remove(id);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }


}
