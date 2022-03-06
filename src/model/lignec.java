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
public class lignec {
    private String id_panier;
     private int idP;
     private int quantite;
private String prod;
private Float prix;

    public lignec(String prod, int quantite, Float prix) {
        this.quantite = quantite;
        this.prod = prod;
        this.prix = prix;
    }

    public String getProd() {
        return prod;
    }

    public void setProd(String prod) {
        this.prod = prod;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public lignec(String id_panier, int idP, int quantite) {
        this.id_panier = id_panier;
        this.idP = idP;
        this.quantite = quantite;
    }

    public String getId_panier() {
        return id_panier;
    }

    public void setId_panier(String id_panier) {
        this.id_panier = id_panier;
    }


    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    	public void ajouterQuantite(int qte)
	{
		this.quantite += qte;
	}
    	public void dimQuantite(int qte)
	{
		this.quantite -= qte;
	}
	public float getPrixLigne()
	{
                Produit p=new Produit();
     float prix;
        prix = p.getbyid(this.idP) * this.getQuantite();
		return prix;
	}
        public float prixbypanier(String idpanier)
        { float prix;
            if(this.id_panier==idpanier)
            {
              return prix=this.getPrixLigne();
            }
            return 0;
        }

}
