/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.favorie;
/**
 *
 * @author hadir
 */
public interface IFavorieService {
    public void ajouterFavorie(favorie F);
   
   public List<favorie> afficherFavorie(int user);
}
