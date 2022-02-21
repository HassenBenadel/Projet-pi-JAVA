/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3a3;

import interfaces.ICategorieService;
import interfaces.IFavorieService;
import java.sql.Connection;
import model.Categorie;
import model.Produit;
import model.favorie;
import service.FavorieService;
import service.ProduitService;
import service.categorieService;
import util.Maconnexion;

/**
 *
 * @author hadir
 */
public class JavaApplication3A3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IFavorieService FS= new FavorieService();
        ProduitService Ps= new ProduitService();
        ICategorieService Cs= new categorieService();
        
        //produit
        //Produit P= new Produit(8, "ordinateur", 04, 27);
        //Ajout
        //Ps.ajouterProduit(P);
        //afficher
        //System.out.println(Ps.afficherProduit()); 
        //suprimer
        //Ps.suprimerProduit(6);
        //modifier
        //Produit Pr= new Produit(5,10,9);
        //Ps.modifierProduit(Pr);
        
        //Categories
        //ajout
        //Categorie C= new Categorie("ordinateur");
        //Cs.ajouterCategorie(C);
        //afficher
        //System.out.println(Cs.afficherCategorie());
        //supp
        //Cs.suprimerCategorie(1);
        //modifier
        //Categorie Cr= new Categorie(2,1204,"telephone");
        //Cs.modifierCategorie(Cr);
        
        //favorie
        
        favorie F= new favorie(12,4);
        FS.ajouterFavorie(F);
        
        //System.out.println(FS.afficherFavorie(2));
        
        
        
        
    }
    
}
