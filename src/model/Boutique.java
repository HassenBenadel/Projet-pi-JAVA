/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author DELL
 */
public class Boutique {
    private int idB;
    private String nomB;
    private String localisationB;
    private String description;

    public Boutique(String nomB, String localisationB, String description) {
        this.nomB = nomB;
        this.localisationB = localisationB;
        this.description = description;
    }

    public Boutique(int idB, String nomB, String localisationB, String description) {
        this.idB = idB;
        this.nomB = nomB;
        this.localisationB = localisationB;
        this.description = description;
    }
    
    

    public int getIdB() {
        return idB;
    }

    public String getNomB() {
        return nomB;
    }

    public String getLocalisationB() {
        return localisationB;
    }

    public String getDescription() {
        return description;
    }

  
    public void setNomB(String nomB) {
        this.nomB = nomB;
    }

    public void setLocalisationB(String localisationB) {
        this.localisationB = localisationB;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   
    
    
}
