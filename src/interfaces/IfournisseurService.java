/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import model.Fournisseur;

/**
 *
 * @author hasse
 */
public interface IfournisseurService {

    public void afficherFournisseur();

    public void ajouterFournisseur(Fournisseur fr);

    public void modifierFournisseur(Fournisseur cl, String attribute, String newValue);

    public void supprimerFournisseur(Fournisseur cl);

}
