package shopping.dao;


import java.util.Collection;
public interface DAO<T> {
    T get(int id);
    void put(T end);
    Collection<T> all();
    void delete(int id);

}
