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
    private int code;
    private int admin ;
    private int ban;
    private Date banexpiration;
    

    public Utilisateur(String nom, String prenom, String email, int telephone, String image, String pays, String ville, String password, String typeCompte, int code, int ban, Date banexpiration) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.image = image;
        this.pays = pays;
        this.ville = ville;
        this.password = password;
        this.typeCompte = typeCompte;
        this.code = code;
        this.ban = ban;
        this.banexpiration = banexpiration;
    }

    public Utilisateur(int id_user, String nom, String prenom, String email, int telephone, String image, String pays, String ville, String password, String typeCompte, int code, int admin, int ban, Date banexpiration) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.image = image;
        this.pays = pays;
        this.ville = ville;
        this.password = password;
        this.typeCompte = typeCompte;
        this.code = code;
        this.admin = admin;
        this.ban = ban;
        this.banexpiration = banexpiration;
    }

    public Utilisateur(int id_user, String email, int admin, int ban, Date banexpiration) {
        this.id_user = id_user;
        this.email = email;
        this.admin = admin;
        this.ban = ban;
        this.banexpiration = banexpiration;
    }
    
    

    public Utilisateur(int id_user, String nom, String prenom, String email, int telephone, String image, String pays, String ville, String password, String typeCompte, int code, int ban, Date banexpiration) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.image = image;
        this.pays = pays;
        this.ville = ville;
        this.password = password;
        this.typeCompte = typeCompte;
        this.code = code;
        this.ban = ban;
        this.banexpiration = banexpiration;
    }

    public Utilisateur(String nom, String prenom, String email, int telephone, String image, String pays, String ville, String password, String typeCompte) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.image = image;
        this.pays = pays;
        this.ville = ville;
        this.password = password;
        this.typeCompte = typeCompte;
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public int getBan() {
        return ban;
    }

    public void setBan(int ban) {
        this.ban = ban;
    }

    public Date getBanexpiration() {
        return banexpiration;
    }

    public void setBanexpiration(Date banexpiration) {
        this.banexpiration = banexpiration;
    }

}
