/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.Categorie;

/**
 *
 * @author hadir
 */
public interface ICategorieService {
     public void ajouterCategorie(Categorie C);
   
   public List<Categorie> afficherCategorie();
   public void suprimerCategorie(int idC);
    public void modifierCategorie(Categorie C);
    
}

