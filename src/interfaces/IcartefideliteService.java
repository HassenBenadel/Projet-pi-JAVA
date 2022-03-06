/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import javafx.collections.ObservableList;
import model.CarteFidelite;


/**
 *
 * @author EYA
 */
public interface IcartefideliteService {
     public ObservableList<CarteFidelite> afficher();
    public void insert(String id);
    public void update(CarteFidelite carte);
    public void supprimer(int id);
    public String convertirlespoints(CarteFidelite Carte,int nbre);
      public CarteFidelite ChercherCartebyClient(String Num);
       public void regenererCarte(int num);
       public CarteFidelite afficherbyid(String Idclient);
           public void Pointparcommande(int ptajout,int num);
}
