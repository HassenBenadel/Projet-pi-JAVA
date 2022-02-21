/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;




/**
 *
 * @author Ibtihel
 */
public class Depot {
    
    
    //attributs
    
    private  int Id_depot; 
    private String  Adresse;
    private int Quantite;
    private  String Disponibilite; 
    private int Id_livraison;
    private int Id_produit;       
   
    //constructeurs

    public Depot(int Id_depot, String Adresse, int Quantite, String Disponibilite, int Id_livraison, int Id_produit) {
        this.Id_depot = Id_depot;
        this.Adresse = Adresse;
        this.Quantite = Quantite;
        this.Disponibilite = Disponibilite;
        this.Id_livraison = Id_livraison;
        this.Id_produit = Id_produit;
    }
    
    
    //..
    public Depot() {
        
    }
    
    //Getters & Setters

    public int getId_depot() {
        return Id_depot;
    }

    public void setId_depot(int Id_depot) {
        this.Id_depot = Id_depot;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public int getQuantite() {
        return Quantite;
    }

    public void setQuantite(int Quantite) {
        this.Quantite = Quantite;
    }

    public String getDisponibilite() {
        return Disponibilite;
    }

    public void setDisponibilite(String Disponibilite) {
        this.Disponibilite = Disponibilite;
    }

    public int getId_livraison() {
        return Id_livraison;
    }

    public void setId_livraison(int Id_livraison) {
        this.Id_livraison = Id_livraison;
    }

    public int getId_produit() {
        return Id_produit;
    }

    public void setId_produit(int Id_produit) {
        this.Id_produit = Id_produit;
    }
    
    
    //afficher

    @Override
    public String toString() {
        return "Depot{" + "Id_depot=" + Id_depot + ", Adresse=" + Adresse + ", Quantite=" + Quantite + ", Disponibilite=" + Disponibilite + ", Id_livraison=" + Id_livraison + ", Id_produit=" + Id_produit + '}';
    }
    
    
    
}
