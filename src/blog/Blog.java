/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog;

import java.sql.Connection;
import model.Commentaire;
import model.Post;
import service.SCommentaire;
import service.SPost;
import util.ConnectionDB;

/**
 *
 * @author El Ghali Omar
 */
public class Blog {

    /**
     * @param args the command line arguments
     */
    // public static void main(String[] args) {
        // SPost sp = new SPost();
        // SCommentaire sc = new SCommentaire();
        // SPostAimer PAS = new SPostAimer();
        
        /* Insert into table */
        // Post p = new Post(12, "jdid", "jdidd", "jdidd", "ok", 565656);
        // sp.ajouter(p);
        /* ----------------- */
        
        /* select from table */
        // System.out.println(sp.afficher());
        /* ----------------- */
        
        /* select from table Par Titre */
        // System.out.println(sp.afficherParTitre("jamil"));
        /* ----------------- */
        
        /* delete from table */
        // sp.supprimer(4);
        /* ----------------- */
        
        /* modify from table */
        // Post p = new Post(3, 1, "kkkk", "kkkk", "kkk", "9esat 7ayet jemil", 11111);
        // sp.modifier(p);
        /* ----------------- */
        
        // ########
        
        /* Insert into table */
        // Commentaire c = new Commentaire(3, "sami", "commentaire mta samir");
        // sc.ajouter(c);
        /* ----------------- */
        
        /* select from table */
        // System.out.println(sc.afficher());
        /* ----------------- */
        
        /* delete from table */
        // sc.supprimer(1);
        /* ----------------- */
        
        /* modify from table */
        // Commentaire c = new Commentaire(1, 3, "Nom jdid", "contenu jdid");
        // sc.modifier(c);
        /* ----------------- */
        
        /* Like / dislike */
        // PostAimer PS= new PostAimer(3,2);
        // sc.ajouter(c);
        /* ----------------- */
        
    // }
}