/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author hasse
 */
public class Visiteur {
    private String addressMac ; 
    private String favorie ;

    public Visiteur(String addressMac, String favorie) {
        this.addressMac = addressMac;
        this.favorie = favorie;
    }

    public String getAddressMac() {
        return addressMac;
    }

    public void setAddressMac(String addressMac) {
        this.addressMac = addressMac;
    }

    public String getFavorie() {
        return favorie;
    }

    public void setFavorie(String favorie) {
        this.favorie = favorie;
    }
    
    
    
}
