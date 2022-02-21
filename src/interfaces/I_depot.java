/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.Depot;
import model.Livraison;



/**
 *
 * @author Ibtihel
 */
public interface I_depot {
    
    
     //add
   public void ajouterDepot( Depot D);
    //select
    public List<Depot> afficherDepot();
    
    //update 
     public void modifierDepot(Depot D);
   
     
     //delete
      public void supprimerDepot(int Id_depot);
      
      //get addresse by Id
      public String getAdresseDepotbyId(int Id_depot);
      
      //affecter livraison à un depot
      public void AffecterLivraisonDepot(int Id_livraison,int Id_depot);
   
   
    
}
