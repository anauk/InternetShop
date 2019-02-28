package shopping.exception;

public class ElementNotFoundInDbException extends RuntimeException {
    private ElementNotFoundInDbException(String s){
        super(s);
    }
}
