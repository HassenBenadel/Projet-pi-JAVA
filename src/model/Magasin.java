/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.collections.ObservableList;

/**
 *
 * @author DELL
 */
public class Magasin {

    public static void setItems(ObservableList<Magasin> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private int reference;
    private String nom;
    private String localisation;
    private String description;

    public Magasin(String nom, String localisation, String description) {
        this.nom = nom;
        this.localisation = localisation;
        this.description = description;
    }

    public Magasin(int reference, String nom, String localisation, String description) {
        this.reference = reference;
        this.nom = nom;
        this.localisation = localisation;
        this.description = description;
    }

  

    public String getNom() {
        return nom;
    }

    public String getLocalisation() {
        return localisation;
    }

    public String getDescription() {
        return description;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReference() {
        return reference;
    }



    
}
