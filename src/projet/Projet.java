/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.sql.Connection;
import java.sql.Date;
import model.Depot;
import model.Livraison;
import services.DepotServices;

import services.LivraisonServices;
import util.MaConnexion;

/**
 *
 * @author Ibtihel
 */
public class Projet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
       // Connection cnx = MaConnexion.getInstance().getCnx();
      
         
         //livraisonServices
         LivraisonServices Li= new LivraisonServices();
         
         //livraison
         //Livraison Liv = new Livraison(0, new Date (System.currentTimeMillis()), 5, "carte bancaire", 7, 2, 3);
        
         //insert 
         //Li.ajouterLivraison(Liv);
         
          //select
         //System.out.println(Li.afficherlivraison()); 
         
          //update 
        //Livraison Livr = new Livraison(4, new Date (System.currentTimeMillis()), 5, "espéce", 2, 3, 3);
        //Li.modifierlivraison(Livr);
        
        //delete
        //Li.supprimerlivraison(2);
        
        
        //afficher le nbre de livraison disponibles 
       //System.out.println( Li.getNbrLivraison());
       
       //traiter livraisons 
       Livraison Livr = new Livraison(4, new Date (System.currentTimeMillis()), 5, "espéce", 2, 3, 3);
       Li.TraiterLivraison(Livr);
        
        
        //depotservices 
         //DepotServices D= new DepotServices();
        
         //Depot 
         //Depot DE = new Depot(0, "b", 2, "oui disponible",1,2);
        
         //insert 
        // D.ajouterDepot(DE);
         
         
          //select
        // System.out.println(D.afficherDepot()); 
        
        //update 
        //Depot DE = new Depot(3, "tataouine", 1, "non  disponible",1,2);
        //D.modifierDepot(DE);
        
        
          //delete
        //D.supprimerDepot(2);
        
        //getAdresseDepotbyId
        //Depot DE = new Depot(1, "tataouine", 1, "non  disponible",1,2);
       //System.out.println(D.getAdresseDepotbyId(3));
       
       //AffecterLivraisonDepot
     // D.AffecterLivraisonDepot(3, 3);
        
        
         
         
         
    }
    
}
