/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author EYA
 */
public class Panier {
    private String idclient;
private String id_panier;
private float totalpanier;
   private List<lignec> lignes;

    public Panier(String idclient, String id_panier, float totalpanier) {
            this.idclient = idclient;
        this.id_panier = id_panier;
        this.totalpanier =totalpanier;
    }

    public String getIdclient() {
        return idclient;
    }

    public void setIdclient(String idclient) {
        this.idclient = idclient;
    }

    public String getId_panier() {
        return id_panier;
    }

    public void setId_panier(String id_panier) {
        this.id_panier = id_panier;
    }

    public float getTotalpanier() {
        return totalpanier;
    }

    public void setTotalpanier(float totalpanier) {
        this.totalpanier = totalpanier;
    }
    public String getidclient() {
        return idclient;
    }

    public void setidclient(String idclient) {
        this.idclient = idclient;
    }

    public String getid_panier() {
        return id_panier;
    }

    public void setid_panier(String id_panier) {
        this.id_panier = id_panier;
    }

    public float gettotalpanier() {
        return totalpanier;
    }

    public void setyotalpanier(float totalpanier) {
        this.totalpanier = totalpanier;
    }

    public Panier(String idclient, String id_panier) {
        this.idclient = idclient;
        this.id_panier = id_panier;
        this.totalpanier = this.calculerPanier();
    }



    public Panier(List<lignec> lignes) {
        this.lignes= lignes;
        
    }

    public Panier() {
        this.lignes = new ArrayList<lignec>();
         this.totalpanier = this.calculerPanier();
    }
	



    public List<lignec> getLignes() {
        return lignes;
    }

/*
    public void ajouterArticle(int code, int qte, int prix)
    {
    lignec ligne = this.getArticle(code);
    if (ligne.getQteArticle() == 0)
    {
    ligne = new Produit(code, qte, prix);
    }
    else
    {
    ligne.ajouterQuantite(qte);
    this.supprimerArticle(code);
    }
    this.articles.add(ligne);
    }
    public lignec getArticle(int code)
    {
    lignec article;
    Iterator<lignec> il = this.lignes.iterator();
    while (il.hasNext())
    {
    lignes = il.next();
    if (lignes.getCodeArticle() == code)
    return lignes;
    }
    lignes  = new Produit(code, 0, 0);
    return lignes;
    }
    public void supprimerArticle(int code)
    {
    Iterator<lignec> il = this.lignes.iterator();
    while (il.hasNext())
    {
    if (il.next().getCodeArticle() == code)
    il.remove();
    }
    }*/
    public void setLignes(List<lignec> lignes) {
        this.lignes = lignes;
    }

    public float calculerPanier() {
        float total = 0;
        Iterator<lignec> il = this.lignes.iterator();
        while (il.hasNext())
        {
            total += il.next().prixbypanier(this.getid_panier());
        }
        return total;
    }
}
