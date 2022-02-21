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
public class favorie {
    private int idP;
    private int idUser;
    private String categorie;
    private String nomProduit;
    private int Quantite;
    private int prix;


    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "favorie{" + "idP=" + idP + ", categorie=" + categorie + ", nomProduit=" + nomProduit + ", Quantite=" + Quantite + ", prix=" + prix + '}';
    }
    
    public favorie(int idP, String categorie, String nomProduit, int Quantite, int prix) {
        this.idP = idP;
        this.categorie = categorie;
        this.nomProduit = nomProduit;
        this.Quantite = Quantite;
        this.prix = prix;
    }

    public favorie(int idP, int idUser) {
        this.idP = idP;
        this.idUser = idUser;
    }
    
    public favorie() {
    }
    
    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
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
    
    
    
}
