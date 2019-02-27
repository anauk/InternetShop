package shopping.impl;

import shopping.dao.UserStorage;

import java.util.HashMap;

public class UserStoragImpl implements UserStorage {
    private final HashMap<String, String> st = new HashMap<>();

    @Override
    public void register(String login, String password) {
        st.put(login, password);
    }

    @Override
    public boolean check(String login, String password) {
        return st.get(login).equals(login) && st.get(password).equals(password);
    }
}
