/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.Produit;

/**
 *
 * @author EYA
 */
public interface IProduitService {
    public void ajouterProduit(Produit p);
    public List<Produit> afficherProduit();
    public void suprimerProduit(int idP);
    public void modifierProduit(Produit p) ;
}
