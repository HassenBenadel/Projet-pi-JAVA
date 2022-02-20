/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.MaConnexion;

/**
 *
 * @author hasse
 */
public class Visiteur {

    private String addressMac;
    private Date date;
    // private Cookies cookies;

    public Visiteur() {
    }

    public Visiteur(String addressMac) {
        this.addressMac = addressMac;
    }

    public String getAddressMac() {
        return addressMac;
    }

    public void setAddressMac(String addressMac) {
        this.addressMac = addressMac;
    }


    /*public Cookies getCookies() {
        return cookies;
    }

    public void setCookies(Cookies cookies) {
        this.cookies = cookies;
    }*/
   
}
