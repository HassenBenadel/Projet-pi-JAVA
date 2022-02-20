/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.project;

import java.sql.Connection;
import model.Client;
import model.Fournisseur;
import model.Livreur;
import model.Utilisateur;
import model.Visiteur;
import util.MaConnexion;
import service.ClientService;
import service.FournisseurService;
import service.LivreurService;

/**
 *
 * @author hasse
 */
public class PidevProject {

    public static void main(String[] args) {
        String attribute = "nom";
        String newValue = "foulen";
        Connection cnx = MaConnexion.getInstance().getCnx();
        System.out.println("connexion établie avec succes");
        System.out.println("______________________________________________________");
        ClientService cs = new ClientService();
        FournisseurService fs = new FournisseurService();
        LivreurService ls = new LivreurService();
        Client cl = new Client(2, "steph", "cirru", "steph@gmail.com", 929292952, "www.steph.com", "usa", "la", "steph123", "client", "ssteph");
        Fournisseur fr = new Fournisseur(2, "souuuuu", "ba", "asma@gmail.com", 2020202020, "www.asma.com", "tunis", "mourouj6", "asma46", "fournisseur", "asmmmmmma");
        Livreur lv = new Livreur(3, "mohamed", "mr", "mohamed@gmail.com", 59241756, "www.med.com", "tunis", "sousse", "med9696", "livreur", "mohammmmmed", "chocolat");

        // cs.ajouterClient(cl);
        // fs.ajouterFournisseur(fr);
        // ls.ajouterLivreur(lv);
        
        //cs.modifierClient(cl);
        //fs.modifierFournisseur(fr);
        //ls.modifierLivreur(lv);
        
        // ls.supprimerLivreur(lv);
        //cs.supprimerClient(cl);
        // fs.supprimerFournisseur(fr);
        
        
        
        
        // cs.afficherClientById(2);
        //cs.afficherClient();
        //ls.afficherLivreurById(1);
        //fs.afficherFournisseurtById(1);
    }

}
