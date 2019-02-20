package user;

public class User {
    private int id;
    private String name;
    private String surname;
    private String login;
    private String password;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(String name, String surname, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }
    public User(int id, String name, String surname, String login, String password) {
        this.id=id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }

    public User(String name){
        this(-1, name);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
