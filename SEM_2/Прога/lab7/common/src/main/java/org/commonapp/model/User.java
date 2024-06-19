package org.commonapp.model;

import org.commonapp.utility.Hashing;
import org.commonapp.utility.Validatable;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

public class User implements Validatable, Serializable {
    public final String login;
    public final String password;

    public User(String login, String password) {
        this.login = login;
        try {
            this.password = Hashing.hashString(password);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean validate() {
        return !login.isEmpty() && !password.isEmpty();
    }
}
