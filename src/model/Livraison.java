/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Ibtihel
 */
public class Livraison {
    
     //attributs
    
    private  int Id_livraison; 
    private Date  Date_livraison;
    private float Prix_total;
    private  String Mode_paiement; 
    private int Id_livreur;
    private int Id_commande;       
    private int Id_client;
    
    
     //constructeurs

    public Livraison(int Id_livraison, Date Date_livraison, float Prix_total, String Mode_paiement, int Id_livreur, int Id_commande, int Id_client) {
        this.Id_livraison = Id_livraison;
        this.Date_livraison = Date_livraison;
        this.Prix_total = Prix_total;
        this.Mode_paiement = Mode_paiement;
        this.Id_livreur = Id_livreur;
        this.Id_commande = Id_commande;
        this.Id_client = Id_client;
    }
    
    //..
    public Livraison(){
        
    }
    
    
    //Getters & Setters

    public int getId_livraison() {
        return Id_livraison;
    }

    public void setId_livraison(int Id_livraison) {
        this.Id_livraison = Id_livraison;
    }

    public Date getDate_livraison() {
        return Date_livraison;
    }

    public void setDate_livraison(Date Date_livraison) {
        this.Date_livraison = Date_livraison;
    }

    public float getPrix_total() {
        return Prix_total;
    }

    public void setPrix_total(float Prix_total) {
        this.Prix_total = Prix_total;
    }

    public String getMode_paiement() {
        return Mode_paiement;
    }

    public void setMode_paiement(String Mode_paiement) {
        this.Mode_paiement = Mode_paiement;
    }

    public int getId_livreur() {
        return Id_livreur;
    }

    public void setId_livreur(int Id_livreur) {
        this.Id_livreur = Id_livreur;
    }

    public int getId_commande() {
        return Id_commande;
    }

    public void setId_commande(int Id_commande) {
        this.Id_commande = Id_commande;
    }

    public int getId_client() {
        return Id_client;
    }

    public void setId_client(int Id_client) {
        this.Id_client = Id_client;
    }
    
    
      //afficher 

    @Override
    public String toString() {
        return "Livraison{" + "Id_livraison=" + Id_livraison + ", Date_livraison=" + Date_livraison + ", Prix_total=" + Prix_total + ", Mode_paiement=" + Mode_paiement + ", Id_livreur=" + Id_livreur + ", Id_commande=" + Id_commande + ", Id_client=" + Id_client + '}';
    }

  

    

    
    
}
