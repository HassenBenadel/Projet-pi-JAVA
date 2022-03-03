/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

//import org.apache.commons.codec.binary.Base64;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hasse
 */
public class PasswordService {

    public String passwordEncryption(String password) {

        // Encode data on your side using BASE64
        byte[] encodedBytes = Base64.getEncoder().encode(password.getBytes());
        String encodedString;
        try {
            encodedString = new String(encodedBytes, "UTF-8");
            return encodedString;
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        return null;

    }

    public String passwordDecryprion(String password) {
        byte[] decodedBytes = Base64.getDecoder().decode(password);
        String decodedString;
        try {
            decodedString = new String(decodedBytes, "UTF-8");
            return decodedString;
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
