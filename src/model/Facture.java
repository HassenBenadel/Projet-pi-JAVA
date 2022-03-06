/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author EYA
 */
public class Facture {
    private int numf;
    private float totalprix;
    private String methpaiement;
    private float totalpanier;
    private int NumCarte;
    private String code;
    private Date DateCommande;
       private List<lignec> lignes;

    public Facture(int numf, float totalprix, String methpaiement, float totalpanier, int NumCarte, String code, Date DateCommande, List<lignec> lignes) {
        this.numf = numf;
        this.totalprix = totalprix;
        this.methpaiement = methpaiement;
        this.totalpanier = totalpanier;
        this.NumCarte = NumCarte;
        this.code = code;
        this.DateCommande = DateCommande;
        this.lignes = lignes;
    }

    public int getNumf() {
        return numf;
    }

    public void setNumf(int numf) {
        this.numf = numf;
    }

    public float getTotalprix() {
        return totalprix;
    }

    public void setTotalprix(float totalprix) {
        this.totalprix = totalprix;
    }

    public String getMethpaiement() {
        return methpaiement;
    }

    public void setMethpaiement(String methpaiement) {
        this.methpaiement = methpaiement;
    }

    public float getTotalpanier() {
        return totalpanier;
    }

    public void setTotalpanier(float totalpanier) {
        this.totalpanier = totalpanier;
    }

    public int getNumCarte() {
        return NumCarte;
    }

    public void setNumCarte(int NumCarte) {
        this.NumCarte = NumCarte;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDateCommande() {
        return DateCommande;
    }

    public void setDateCommande(Date DateCommande) {
        this.DateCommande = DateCommande;
    }

    public List<lignec> getLignes() {
        return lignes;
    }

    public void setLignes(List<lignec> lignes) {
        this.lignes = lignes;
    }

    @Override
    public String toString() {
        return "Facture{" + "numf=" + numf + ", totalprix=" + totalprix + ", methpaiement=" + methpaiement + ", totalpanier=" + totalpanier + ", NumCarte=" + NumCarte + ", code=" + code + ", DateCommande=" + DateCommande + ", lignes=" + lignes + '}';
    }
}
