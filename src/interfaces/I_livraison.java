/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.Livraison;


/**
 *
 * @author Ibtihel
 */
public interface I_livraison {
    //add
   public void ajouterLivraison(Livraison Li);
  
   //select
    public List<Livraison> afficherlivraison();
    
    //update 
     public void modifierlivraison(Livraison Li);
   
     
     //delete
      public void supprimerlivraison(int Id_livraison);
      
      //afficher le nobre de livraison dispo
      public int getNbrLivraison();
      
      //traiter livraison
      public void  TraiterLivraison(Livraison Li);
    
}
