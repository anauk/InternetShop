package user;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String mes){
        super(mes);
    }
}
