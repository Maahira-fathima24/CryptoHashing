package com.example.cryptohash.Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {
    public static String generateString(String input , String algorithm){
        try{
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] hashBytes = md.digest(input.getBytes());
            StringBuilder hex = new StringBuilder();
            for(byte b : hashBytes){
                hex.append(String.format("%02x", b));

            }
            return hex.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Invalid algorithm or Error in Hashing");
        }
    }
}
