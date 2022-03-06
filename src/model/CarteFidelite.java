/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;


/**
 *
 * @author EYA
 */
public class CarteFidelite {
 private int NumCarte;
  private String IdClient;
  private Date Datefinvalidite;
  private Date Datecreation;
  private int Numpoint;

    public CarteFidelite(int NumCarte, String IdClient, int Numpoint) {
        this.NumCarte = NumCarte;
        this.IdClient = IdClient;
        this.Numpoint = Numpoint;
    }

 

    @Override
    public String toString() {
        String s="";
        //s+="Votre Carte de Fidelite: \n \n \n";
        return s  + "" + NumCarte + "\n \n Votre Id Client :           " + IdClient + "\n \n  La date de du creation :            " + Datefinvalidite + "\n \n La Date du fin validité  :            " + Datecreation + "\n \n Nombre de points actuels:            " + Numpoint ;
    }


    

    public CarteFidelite() {
    }

    public String getIdClient() {
        return IdClient;
    }

    public void setIdClient(String IdClient) {
        this.IdClient = IdClient;
    }



    public int getNumpoint() {
        return Numpoint;
    }

    public void setNumpoint(int Numpoint) {
        this.Numpoint = Numpoint;
    }

    public int getNumCarte() {
        return NumCarte;
    }

    public CarteFidelite(int NumCarte, String IdClient, Date Datefinvalidite, Date Datecreation, int Numpoint) {
        this.NumCarte = NumCarte;
        this.IdClient = IdClient;
        this.Datefinvalidite = Datefinvalidite;
        this.Datecreation = Datecreation;
        this.Numpoint = Numpoint;
    }

    public void setNumCarte(int NumCarte) {
        this.NumCarte = NumCarte;
    }

    public Date getDatefinvalidite() {
        return Datefinvalidite;
    }

    public void setDatefinvalidite(Date Datefinvalidite) {
        this.Datefinvalidite = Datefinvalidite;
    }

    public Date getDatecreation() {
        return Datecreation;
    }

    public void setDatecreation(Date Datecreation) {
        this.Datecreation = Datecreation;
    }

   /* public CarteFidelite(String Datecreation,String Datefinvalidite) {
        this.Datecreation = Datecreation;
        this.Datefinvalidite = Datefinvalidite;
    }*/

 
}
