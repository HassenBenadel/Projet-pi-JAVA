/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import model.CarteFidelite;
import model.CodeReduction;

/**
 *
 * @author EYA
 */
public interface IcartefideliteService {
     public void afficher();
    public void insert();
    public void update(CarteFidelite carte);
    public void supprimer();
    public String convertirlespoints(CarteFidelite Carte);
    public void ChercherCartebyClient(String IdClient);
       public void regenererCarte(CarteFidelite Carte);
       public void afficherbyid(String Idclient);
}
