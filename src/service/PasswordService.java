/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

//import org.apache.commons.codec.binary.Base64;
import java.util.Base64;

/**
 *
 * @author hasse
 */
public class PasswordService {

    public String passwordEncryption(String password) {

        // Encode data on your side using BASE64
        String encodedString = Base64.getEncoder().encodeToString(password.getBytes());
        return encodedString;

    }

}
