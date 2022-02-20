/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import model.Commande;

/**
 *
 * @author EYA
 */
public interface IcommandeService {
     public void afficher();
     public void insert();
    public void update(Commande c);
    public void supprimer();
}
