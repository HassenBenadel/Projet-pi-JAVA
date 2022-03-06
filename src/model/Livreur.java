/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author hasse
 */
public class Livreur extends Utilisateur {

    private int id_livreur;
    private String secteur_activite;

    public Livreur(String nom, String prenom, String email, int telephone, String image, String pays, String ville, String password, String typeCompte, String secteur_activite) {
        super(nom, prenom, email, telephone, image, pays, ville, password, typeCompte);
        this.secteur_activite = secteur_activite;
    }

    public Livreur(int id_livreur, String nom, String prenom, String email, int telephone, String image, String pays, String ville, String password, String typeCompte, String secteur_activite) {
        super(nom, prenom, email, telephone, image, pays, ville, password, typeCompte);
        this.id_livreur = id_livreur;
        this.secteur_activite = secteur_activite;
    }

    public Livreur(int id_livreur, int id_user, String email, int admin, int ban, Date banexpiration) {
        super(id_user, email, admin, ban, banexpiration);
        this.id_livreur = id_livreur;
    }

    public Livreur(int id_user, String email, int admin, int ban, Date banexpiration) {
        super(id_user, email, admin, ban, banexpiration);
    }
    
    

    public int getId_livreur() {
        return id_livreur;
    }

    public void setId_livreur(int id_livreur) {
        this.id_livreur = id_livreur;
    }

    public String getSecteur_activite() {
        return secteur_activite;
    }

    public void setSecteur_activite(String secteur_activite) {
        this.secteur_activite = secteur_activite;
    }

    @Override
    public String toString() {
        return "Livreur{" + "id_livreur=" + id_livreur + ", secteur_activite=" + secteur_activite + '}';
    }

}
