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
public class Commande {
    private int Id_Commande;
    private String methpaiement;
    private float totalprix;
    private float totalpanier;
    private String NumCarte;
    private CodeReduction code;

    public Commande(int Id_Commande, String methpaiement) {
        this.Id_Commande = Id_Commande;
        this.methpaiement = methpaiement;
        this.totalprix =this.calculprix();
        this.totalpanier = this.calculpanier();
    }

    public Commande(int Id_Commande, String methpaiement, String NumCarte) {
        this.Id_Commande = Id_Commande;
        this.methpaiement = methpaiement;
       this.totalpanier = this.calculpanier();
        this.NumCarte = NumCarte;
         this.totalprix =this.calculprix();
        
    }

    public Commande(int Id_Commande, String methpaiement,String NumCarte, CodeReduction code) {
        this.Id_Commande = Id_Commande;
        this.methpaiement = methpaiement;
        this.totalpanier = this.calculpanier();
        this.NumCarte = NumCarte;
        this.code = code;
    }

    public Commande(int Id_Commande) {
        this.Id_Commande = Id_Commande;
    }

 



    public int getId_Commande() {
        return Id_Commande;
    }

    public void setId_Commande(int Id_Commande) {
        this.Id_Commande = Id_Commande;
    }

    public String getMethpaiement() {
        return methpaiement;
    }

    public void setMethpaiement(String methpaiement) {
        this.methpaiement = methpaiement;
    }

    public float calculprix()
    { float prix=0;
     try{ 
        prix=this.totalpanier;
        System.out.println(this.code.getPourcentage());
        float reduction= prix*(this.code.getPourcentage() ) / 100;
       prix=prix - reduction ;
   System.out.println(prix);
     }catch(Exception e)
     {
         
     }
       return prix;
    }

    public float calculpanier()
    {
              Panier panier = new Panier();
                panier.ajouterArticle(1, 2, 50);
		panier.ajouterArticle(2, 2, 100);
		panier.ajouterArticle(3, 1, 150);
		panier.ajouterArticle(1, 3, 50);
		System.out.println(panier.calculerPanier());
		panier.supprimerArticle(2);
		System.out.println(panier.calculerPanier());
                float totalcommande=panier.calculerPanier();
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
        return "Commande{" + "Id_Commande=" + Id_Commande + ", methpaiement=" + methpaiement + ", totalprix=" + totalprix + ", totalpanier=" + totalpanier + '}';
    }
     
}
