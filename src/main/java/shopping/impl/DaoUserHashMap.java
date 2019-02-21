package shopping.impl;

import shopping.dao.DAO;
import shopping.dao.UserStorage;
import shopping.entity.User;

import java.util.Collection;
import java.util.HashMap;

public class DaoUserHashMap implements DAO<User>, UserStorage {
    private final HashMap<Integer, User> storage = new HashMap<>();
    private final HashMap<String, String> cookies = new HashMap<>();

    @Override
    public User get(int id) {
        return storage.get(id);
    }

    @Override
    public void put(User end) {
        storage.put(end.getId(), end);

    }

    @Override
    public Collection<User> all() {
        return storage.values();
    }

    @Override
    public void delete(int id) {
        storage.remove(id);
    }

    @Override
    public void register(String login, String password) {
        cookies.put(login, password);
    }

    @Override
    public boolean check(String login, String password) {
        return cookies.get(login).equals(password);
    }
}
