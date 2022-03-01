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
public class Categorie {
    private int idC;
    private  String type;
    private String imageC;

    public Categorie( String type) {
        this.type = type;
    }

    public String getImageC() {
        return imageC;
    }

    public void setImageC(String imageC) {
        this.imageC = imageC;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    @Override
    public String toString() {
        return "Categorie{" + "idC=" + idC + ", type=" + type + ", imageC=" + imageC + '}';
    }

   

    public Categorie(int idC, String type) {
        this.idC = idC;
        this.type = type;
    }

    public Categorie(String type, String imageC) {
        this.type = type;
        this.imageC = imageC;
    }

    public Categorie(int idC, String type, String imageC) {
        this.idC = idC;
        this.type = type;
        this.imageC = imageC;
    }
    
    
}


