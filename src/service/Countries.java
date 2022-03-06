/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 *
 * @author hasse
 */
public class MacAddress {
    
     public String getMacAddress() {
        String mac = "";

        try {
            InetAddress address = InetAddress.getLocalHost();
            NetworkInterface ni = NetworkInterface.getByInetAddress(address);
            byte[] hardwareAddress = ni.getHardwareAddress();
            String[] hexadecimal = new String[hardwareAddress.length];
            for (int i = 0; i < hardwareAddress.length; i++) {
                hexadecimal[i] = String.format("%02X", hardwareAddress[i]);
            }
            mac= String.join("-", hexadecimal);
            

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mac;
    }
    
}
