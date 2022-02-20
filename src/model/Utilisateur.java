/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author hasse
 */
public class Utilisateur {

    private int id_user;
    private String nom;
    private String prenom;
    private String email;
    private int telephone;
    private String image;
    private String pays;
    private String ville;
    private String password;
    private String typeCompte;
    private String adressMac;

    public Utilisateur() {

    }

    public Utilisateur(String nom, String prenom, String email, int telephone, String image, String pays, String ville, String password, String typeCompte, String adressMac) {

        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.image = image;
        this.pays = pays;
        this.ville = ville;
        this.password = password;
        this.typeCompte = typeCompte;
        this.adressMac = adressMac;
    }

   

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(String typeCompte) {
        this.typeCompte = typeCompte;
    }

    public String getAdressMac() {
        return adressMac;
    }

    public void setAdressMac(String adressMac) {
        this.adressMac = adressMac;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id_user=" + id_user + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", telephone=" + telephone
                + ", image=" + image + ", pays=" + pays + ", ville=" + ville + ", password=" + password + ", typeCompte=" + typeCompte + ", adressMac=" + adressMac + '}';
    }

}
