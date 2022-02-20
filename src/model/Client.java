/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.Statement;
import util.MaConnexion;

/**
 *
 * @author hasse
 */
public class Client extends Utilisateur {

    private int id_client;
    private int id_user;

    public Client(String nom, String prenom, String email, int telephone, String image, String pays, String ville, String password, String typeCompte, String adressMac) {
        super(nom, prenom, email, telephone, image, pays, ville, password, typeCompte, adressMac);
        this.id_user = super.getId_user();
    }

    public Client(int id_client, String nom, String prenom, String email, int telephone, String image, String pays, String ville, String password, String typeCompte, String adressMac) {
        super(nom, prenom, email, telephone, image, pays, ville, password, typeCompte, adressMac);
        this.id_client = id_client;
        this.id_user = super.getId_user();
    }

    public int getId_client() {

        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    @Override
    public String toString() {
        return "Client{" + "id_client=" + id_client + '}';
    }

}
