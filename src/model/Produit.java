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
public class Produit {
    private int codeArticle;
	private int qteArticle;
	private int prixArticle;
	
	public int getCodeArticle() {
		return codeArticle;
	}
	public void setCodeArticle(int code) {
		this.codeArticle = code;
	}
	public int getQteArticle() {
		return qteArticle;
	}
	public void setQteArticle(int qte) {
		this.qteArticle = qte;
	}
	public int getPrixArticle() {
		return prixArticle;
	}
	public void setPrixArticle(int prix) {
		this.prixArticle = prix;
	}	
	Produit(int code, int qte, int prix)
	{
		this.setCodeArticle(code);
		this.setQteArticle(qte);
		this.setPrixArticle(prix);
	}	
	public void ajouterQuantite(int qte)
	{
		this.qteArticle += qte;
	}
	public int getPrixLigne()
	{
		int prix = this.getPrixArticle() * this.getQteArticle();
		return prix;
	}
}
