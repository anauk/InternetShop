package user;

import java.util.HashMap;

public class DaoUserHashMap implements DAO<User> {
    private final HashMap<Integer, User> storage = new HashMap<>();

    @Override
    public User get(int id) {
        return storage.get(id);
    }

    @Override
    public void put(User end) {
        storage.put(end.getId(), end);

    }

}
