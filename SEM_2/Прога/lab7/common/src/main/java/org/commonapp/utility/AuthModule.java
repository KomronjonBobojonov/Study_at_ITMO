package org.commonapp.utility;

import java.security.NoSuchAlgorithmException;

public class AuthModule {
    public static String login = "";
    public static String password = "";
    public static Boolean isAuthenticated = false;

    public static void setData(String login, String password) {
        AuthModule.login = login;
        try {
            AuthModule.password = Hashing.hashString(password);
            System.out.println(AuthModule.password);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        AuthModule.isAuthenticated = true;
    }
}
