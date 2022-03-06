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
  private int id_client;
  private Date Datefinvalidite;
  private Date Datecreation;
  private int Numpoint;

    public CarteFidelite(int NumCarte, int IdClient, int Numpoint) {
        this.NumCarte = NumCarte;
        this.id_client = IdClient;
        this.Numpoint = Numpoint;
    }

 

    @Override
    public String toString() {
        String s="";
        //s+="Votre Carte de Fidelite: \n \n \n";
        return s  + "" + NumCarte + "\n \n Votre Id Client :           " + id_client + "\n \n  La date de du creation :            " + Datefinvalidite + "\n \n La Date du fin validité  :            " + Datecreation + "\n \n Nombre de points actuels:            " + Numpoint ;
    }


    

    public CarteFidelite() {
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
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

    public CarteFidelite(int NumCarte, int IdClient, Date Datefinvalidite, Date Datecreation, int Numpoint) {
        this.NumCarte = NumCarte;
        this.id_client = IdClient;
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
