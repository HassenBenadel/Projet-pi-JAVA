/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import model.Livreur;

/**
 *
 * @author hasse
 */
public interface ILivreurService {

    public void afficherLivreur();

    public void ajouterLivreur(Livreur cl);

    public void modifierLivreur(Livreur cl, String attribute, String newValue);

    public void supprimerLivreur(Livreur cl);

}
