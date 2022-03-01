/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.net.InetAddress;

/**
 *
 * @author hasse
 */
public class HistoriqueService {

    public String getIpAdress() {
        String ip = "";

        try {
            ip = InetAddress.getLocalHost().getHostAddress();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ip;
    }

}
