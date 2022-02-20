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
public class Fournisseur extends Utilisateur {

    private int id_fournisseur;

    public Fournisseur(String nom, String prenom, String email, int telephone, String image, String pays, String ville, String password, String typeCompte, String adressMac) {
        super(nom, prenom, email, telephone, image, pays, ville, password, typeCompte, adressMac);
    }

    public Fournisseur(int id_fournisseur ,String nom, String prenom, String email, int telephone, String image, String pays, String ville, String password, String typeCompte, String adressMac) {
        super(nom, prenom, email, telephone, image, pays, ville, password, typeCompte, adressMac);
        this.id_fournisseur = id_fournisseur;

    }

    public int getId_fournisseur() {
        return id_fournisseur;
    }

    public void setId_fournisseur(int id_fournisseur) {
        this.id_fournisseur = id_fournisseur;
    }

    @Override
    public String toString() {
        return "Fournisseur{" + "id_fournisseur=" + id_fournisseur + '}';
    }

}
