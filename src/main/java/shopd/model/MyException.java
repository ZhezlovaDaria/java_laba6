package shopd.model;

public class MyException extends Exception{
    public MyException(String message, String args) {
        super(message + args);
    }
}
