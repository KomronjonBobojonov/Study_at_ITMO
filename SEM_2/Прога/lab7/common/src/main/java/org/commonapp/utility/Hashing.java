package org.commonapp.utility;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {
    public static String hashString(String value) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] messageDigest = md.digest(value.getBytes());

        // Convert the byte array to a hexadecimal string
        BigInteger no = new BigInteger(1, messageDigest);
        String md5Hash = no.toString(16);

        while (md5Hash.length() < 32) {
            md5Hash = "0" + md5Hash;
        }
        return md5Hash;
    }
}
