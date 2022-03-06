/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import javafx.collections.ObservableList;
import model.Commande;

/**
 *
 * @author EYA
 */
public interface IcommandeService {
      public ObservableList<Commande> afficherbyid(int iduser);
     public ObservableList<Commande>  afficher();
     public void insert(String meth,String code,int carte,int iduser);
    public void update(Commande c);
    public void supprimer(int id);
}
