/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import service.PanierService;

/**
 *
 * @author EYA
 */
public class Commande {
    private int id_commande;
    private int id_client;

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    private String methpaiement;
    private float totalprix;
    private float totalpanier;
    private int NumCarte;
    private CodeReduction c;
    private String code;
    private Date DateCommande;

    public Commande(int Id_commande, String methpaiement) {
        this.id_commande = Id_commande;
        this.methpaiement = methpaiement;
       // this.totalprix =this.calculprix();
        //this.totalpanier = this.calculpanier();
    }

    public Commande(int id_commande, String methpaiement, int NumCarte) {
        this.id_commande = id_commande;
        this.methpaiement = methpaiement;
       //this.totalpanier = this.calculpanier();
        this.NumCarte = NumCarte;
         //this.totalprix =this.calculprix();
        
    }

    public Commande(String methpaiement,int NumCarte, CodeReduction c) {
        //this.id_commande = Id_Commande;
        this.methpaiement = methpaiement;
            //this.totalprix =panier;
           //this.totalprix =this.calculprix();
        this.NumCarte = NumCarte;
        this.c = c;
    }

    public Commande(int Id_Commande) {
        this.id_commande = Id_Commande;
    }

    public Commande(int id_commande, String methpaiement, Float totalprix, Float totalpanier, int NumCarte, Date DateCommande, String code,int iduser) {
       this.id_commande = id_commande;
        this.methpaiement = methpaiement;
         this.totalprix=totalprix;
        this.totalpanier = totalpanier;
        this.NumCarte = NumCarte;
         this.DateCommande=DateCommande;
        this.code= code;
        this.id_client= iduser;
    }

    public int getNumCarte() {
        return NumCarte;
    }

    public void setNumCarte(int NumCarte) {
        this.NumCarte = NumCarte;
    }



    public Date getDateCommande() {
        return DateCommande;
    }

    public void setDateCommande(Date datecommande) {
        this.DateCommande = datecommande;
    }

 



    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public String getMethpaiement() {
        return methpaiement;
    }

    public void setMethpaiement(String methpaiement) {
        this.methpaiement = methpaiement;
    }



    public CodeReduction getC() {
        return c;
    }

    public void setC(CodeReduction c) {
        this.c = c;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float calculpanier(String idpanier)
    {
              PanierService panier = new PanierService();
             
                float totalcommande=panier.calcultotal(idpanier);
                return totalcommande;
    }
    
    public float getTotalprix() {
        return totalprix;
    }

    public void setTotalprix(float totalprix) {
        this.totalprix = totalprix;
    }

    public float getTotalpanier() {
        return totalpanier;
    }

    public void setTotalpanier(float totalpanier) {
        this.totalpanier = totalpanier;
    }

    @Override
    public String toString() {
        return "Commande{" + "Id_Commande=" + id_commande + ", methpaiement=" + methpaiement + ", totalprix=" + totalprix + ", totalpanier=" + totalpanier + '}';
    }
     
}
