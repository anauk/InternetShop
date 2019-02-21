package shopping.dao;

public interface UserStorage {
    void register(String login, String password);
    boolean check(String login, String password);
}
