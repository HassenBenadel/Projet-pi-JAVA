/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


/**
 *
 * @author EYA
 */
public class CarteFidelite {
 private String NumCarte;
  private String IdClient;
  private String Datefinvalidite;
  private String Datecreation;
  private int Numpoint;

    public CarteFidelite(String NumCarte) {
        this.NumCarte = NumCarte;
    }

    public CarteFidelite(String NumCarte, String IdClient, int Numpoint) {
        this.NumCarte = NumCarte;
        this.IdClient = IdClient;
        this.Numpoint = Numpoint;
    }

 

    @Override
    public String toString() {
        return "CarteFidelite{" + "NumCarte=" + NumCarte + ", IdClient=" + IdClient + ", Datefinvalidite=" + Datefinvalidite + ", Datecreation=" + Datecreation + ", Numpoint=" + Numpoint + '}';
    }

    public CarteFidelite(String NumCarte, String IdClient, String Datefinvalidite, String Datecreation, int Numpoint) {
        this.NumCarte = NumCarte;
        this.IdClient = IdClient;
        this.Datefinvalidite = Datefinvalidite;
        this.Datecreation = Datecreation;
        this.Numpoint = Numpoint;
    }

    public CarteFidelite() {
    }

    public String getNumCarte() {
        return NumCarte;
    }

    public void setNumCarte(String NumCarte) {
        this.NumCarte = NumCarte;
    }

    public String getIdClient() {
        return IdClient;
    }

    public void setIdClient(String IdClient) {
        this.IdClient = IdClient;
    }

    public String getDatefinvalidite() {
        return Datefinvalidite;
    }

    public void setDatefinvalidite(String Datefinvalidite) {
        this.Datefinvalidite = Datefinvalidite;
    }

    public String getDatecreation() {
        return Datecreation;
    }

    public void setDatecreation(String Datecreation) {
        this.Datecreation = Datecreation;
    }

    public int getNumpoint() {
        return Numpoint;
    }

    public void setNumpoint(int Numpoint) {
        this.Numpoint = Numpoint;
    }

    public CarteFidelite(String Datecreation,String Datefinvalidite) {
        this.Datecreation = Datecreation;
        this.Datefinvalidite = Datefinvalidite;
    }

 
}
