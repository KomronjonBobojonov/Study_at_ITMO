package exception;

import java.security.PublicKey;

public class InvalidatedException extends Exception{
    public InvalidatedException(String message){
        super(message);
    }
}
