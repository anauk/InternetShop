package user;

public interface DAO<T> {
    T get(int id);
    void put(T end);
}
