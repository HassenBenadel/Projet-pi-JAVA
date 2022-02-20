/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author EYA
 */
public class CodeReduction {
    private String code;
    private int pourcentage;
    //private String NumCarte;

 /*   public String getNumCarte() {
        return NumCarte;
    }

    public void setNumCarte(String NumCarte) {
        this.NumCarte = NumCarte;
    }*/
    
    public CodeReduction() {
    }

    public CodeReduction(String code, int pourcentage) {
        this.code = code;
        this.pourcentage = pourcentage;
    }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
    }
    
}
