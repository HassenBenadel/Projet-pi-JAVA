/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.project;

import java.sql.Connection;
import java.util.Base64;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Client;
import model.Fournisseur;
import model.Livreur;
import util.MaConnexion;
import service.ClientService;
import service.Connect;
import service.FournisseurService;
import service.LivreurService;
import service.PasswordService;
import service.UtilisateurService;

/**
 *
 * @author hasse
 */
public class PidevProject {

    public static void main(String[] args) throws Exception {

        String attribute = "nom";
        String newValue = "foulen";
        Connection cnx = MaConnexion.getInstance().getCnx();
        System.out.println("connexion établie avec succes");
        System.out.println("______________________________________________________");
        UtilisateurService us = new UtilisateurService();
        ClientService cs = new ClientService();
        FournisseurService fs = new FournisseurService();
        LivreurService ls = new LivreurService();
        Client cl = new Client(15, "Mohamed", "Hassen", "MohamedHassenBenadel@gmail.com", 25361247, "imagehassen", "Tunis", "Tunis", "hassen", "client");
        Fournisseur fr = new Fournisseur(7, "Riadh", "Chibeni", "RiadhChibni@gmail.com", 52385909, "imageriadh", "Tunis", "Tunis", "riadhchb", "fournisseur");
        Livreur lv = new Livreur(7, "James", "King", "JamesKing@hotmail.com", 253612589, "imagejames", "usa", "Texas", "James", "livreur", "Phones");

        Connect m = new Connect();
        //boolean verify = m.verify("MalekFitouri@gmail.com", "malek");
        PasswordService ps = new PasswordService();

        ObservableList<String> items = FXCollections.observableArrayList();
        ObservableList<String> items1 = us.getUsersEmail(items);

        
        us.banUtilisateur("malek@gmail.com");
        //ls.ajouterLivreur(lv);
        //ls.modifierLivreur(lv);
        //ls.supprimerLivreur(lv);
        //fs.ajouterFournisseur(fr);
        //fs.modifierFournisseur(fr);
        //fs.supprimerFournisseur(fr);
        //cs.ajouterClient(cl);
        //cs.modifierClient(cl);
        //cs.supprimerClient(cl);

        /* MacAddress mc = new MacAddress();
        System.out.println(mc.getMacAddress().toString());*/
 /* Mail m = new Mail();
        System.out.println(m.verify("viper@gmail.com", "steph123 "));*/
        //System.out.println(cs.getIdByEmail("viper@gmail.com"));
        //System.out.println(cs.selectClientByEmail("viper@gmail.com"));
        // cs.afficherClient();
        //ForgetPassword fp = new ForgetPassword();
        //fp.modifypassword("raidh@gmail.com", "password");
        /* int code = fp.generateCode();
        fp.insertCodeInDB("raidh@gmail.com", code);
        MailingService ms = new MailingService();
        ms.sendMail("hassenbenadel37@gmail.com", code);*/
        //System.out.println(fp.VerifyCode(757, "raidh@gmail.com"));
        //HistoriqueService hs = new HistoriqueService();
        //System.out.println(hs.getIpAdress());
        //ms.sendMail("hassenbenadel37@gmail.com");
    }

}
