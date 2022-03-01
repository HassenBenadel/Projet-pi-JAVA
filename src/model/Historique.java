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
public class Historique {

    private String ipAddress;
    private String town;
    private String country;

    public Historique(String ipAdress, String town, String country) {
        this.ipAddress = ipAdress;
        this.town = town;
        this.country = country;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAddress = ipAddress;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
