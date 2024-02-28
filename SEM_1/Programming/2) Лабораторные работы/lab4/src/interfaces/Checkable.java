package interfaces;

import environment.Person;
import exception.InvalidatedException;

public interface Checkable {
    void check(String name) throws InvalidatedException;
}
