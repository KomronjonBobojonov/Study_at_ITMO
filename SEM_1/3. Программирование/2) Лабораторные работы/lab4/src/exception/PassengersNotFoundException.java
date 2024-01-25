package exception;

public class PassengersNotFoundException extends RuntimeException{
    public PassengersNotFoundException(String message){
        super(message);
    }
}
