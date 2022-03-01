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
public class Client extends Utilisateur {

    private int id_client;

    public Client(int id_client, String nom, String prenom, String email, int telephone, String image, String pays, String ville, String password, String typeCompte, int code, int ban, Date banexpiration) {
        super(nom, prenom, email, telephone, image, pays, ville, password, typeCompte, code, ban, banexpiration);
        this.id_client = id_client;
    }

    public Client(int id_client, int id_user, String nom, String prenom, String email, int telephone, String image, String pays, String ville, String password, String typeCompte, int code, int ban, Date banexpiration) {
        super(id_user, nom, prenom, email, telephone, image, pays, ville, password, typeCompte, code, ban, banexpiration);
        this.id_client = id_client;
    }

    public Client(String nom, String prenom, String email, int telephone, String image, String pays, String ville, String password, String typeCompte, int code, int ban, Date banexpiration) {
        super(nom, prenom, email, telephone, image, pays, ville, password, typeCompte, code, ban, banexpiration);
    }

    public Client(String nom, String prenom, String email, int telephone, String image, String pays, String ville, String password, String typeCompte) {
        super(nom, prenom, email, telephone, image, pays, ville, password, typeCompte);
    }   //used to insert a  client in database 

    public Client(int id_client, String nom, String prenom, String email, int telephone, String image, String pays, String ville, String password, String typeCompte) {
        super(nom, prenom, email, telephone, image, pays, ville, password, typeCompte);
        this.id_client = id_client;
    }   //used to modify  a clinet in database using his ID

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

}
