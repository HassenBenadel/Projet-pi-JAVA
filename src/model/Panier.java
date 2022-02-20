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
    private String id_panier;
    private float totalpanier;
   private List<Produit> articles;

    public Panier(List<Produit> articles) {
        this.articles = articles;
        
    }

    public Panier() {
        this.articles = new ArrayList<Produit>();
    }
	

	public void ajouterArticle(int code, int qte, int prix)
	{
		Produit ligne = this.getArticle(code);
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
	public Produit getArticle(int code)
	{
		Produit article;
		Iterator<Produit> il = this.articles.iterator();
		while (il.hasNext())
		{
			article = il.next();
			if (article.getCodeArticle() == code)
				return article;	
		}
		article  = new Produit(code, 0, 0);
		return article;
	}	
	public void supprimerArticle(int code)
	{
		Iterator<Produit> il = this.articles.iterator();
		while (il.hasNext())
		{
			if (il.next().getCodeArticle() == code)
				il.remove();
		}
	}	
	public int calculerPanier()
	{
		int total = 0;
		Iterator<Produit> il = this.articles.iterator();
		while (il.hasNext())
		{
			total += il.next().getPrixLigne();
		}
		return total;
	}
}
