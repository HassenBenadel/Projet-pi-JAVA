/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.Commentaire;

/**
 *
 * @author El Ghali Omar
 */
public interface ICommentaire {
    public void ajouter(Commentaire c);
    public List<Commentaire> afficher();
    public void supprimer(int id);
    public void modifier(Commentaire c);
}
