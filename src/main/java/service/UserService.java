package service;

import shopping.dao.DAO;
import shopping.entity.User;

import java.util.Collection;

public class UserService {
    private DAO<User> users;

    public UserService(DAO<User> users) {
        this.users = users;
    }
    public void putUser(User user){
        users.put(user);
    }
    public void putUser(String name, String surname, String login, String password){
        User user = new User(name, surname, login, password);
        users.put(user);
    }
    public void deleteUser(int id){
        users.delete(id);
    }
    public Collection<User> all(){
        return users.all();
    }
    public User getUser(int id){
        return users.get(id);
    }
    public boolean isUserEmpty(){
        return users.isEmpty();
    }
    public boolean checkUserPass(String login, String password){
        User user = users.get(login.hashCode());
        return user.check(password);
    }
}
