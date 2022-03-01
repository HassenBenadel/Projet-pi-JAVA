/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.Post;

/**
 *
 * @author El Ghali Omar
 */
public interface IPost {
    public List<Post> afficher();
    public Post afficherParTitre(String titre);
    public void ajouter(Post p);
    public void supprimer(int id);
    public void modifier(Post p);
}
