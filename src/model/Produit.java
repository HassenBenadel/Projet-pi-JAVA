/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author hadir
 */
public class Produit {
    private int idP;
    private int reference;
    private  String nomProduit;
    private int Quantite;
    private int prix;
    private String image;
    

    public Produit(int reference, String nomProduit, int Quantite, int prix) {
        this.reference = reference;
        this.nomProduit = nomProduit;
        this.Quantite = Quantite;
        this.prix = prix;
    }
    public Produit()
    {
        
    }

    public Produit(int reference, String nomProduit, int Quantite, int prix, String image) {
        this.reference = reference;
        this.nomProduit = nomProduit;
        this.Quantite = Quantite;
        this.prix = prix;
        this.image = image;
    }
    

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public int getQuantite() {
        return Quantite;
    }

    public void setQuantite(int Quantite) {
        this.Quantite = Quantite;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit{" + "idP=" + idP + ", reference=" + reference + ", nomProduit=" + nomProduit + ", Quantite=" + Quantite + ", prix=" + prix + ", image=" + image + '}';
    }


    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public Produit(int idP, int Quantite, int prix) {
        this.idP = idP;
        this.Quantite = Quantite;
        this.prix = prix;
    }

    public Produit(int idP, int Quantite, int prix, String image) {
        this.idP = idP;
        this.Quantite = Quantite;
        this.prix = prix;
        this.image = image;
    }
    

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Produit(int idP, int reference, String nomProduit, int Quantite, int prix, String image) {
        this.idP = idP;
        this.reference = reference;
        this.nomProduit = nomProduit;
        this.Quantite = Quantite;
        this.prix = prix;
        this.image = image;
    }
    
    
    
    
    
}
