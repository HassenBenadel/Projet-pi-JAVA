/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi_project;

import interfaces.IcartefideliteService;
import interfaces.IcommandeService;
/**
 *
 * @author EYA
 */import util.MyConnexion;
import java.sql.Connection;
import java.util.Scanner;
import model.CarteFidelite;
import model.Commande;
import service.CarteFideliteService;
import service.CommandeService;
import service.MailingService;
public class Pi_project {

    /**
     * @param args the command line arguments
     * */





    /**
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        Connection cnx = MyConnexion.getInstance().getCnx(); // Create a conncetion with the database
        System.out.println("connexion établie avec succes");
        IcommandeService cmd= new CommandeService(); // Create a new commandeService
        IcartefideliteService crt= new CarteFideliteService(); // Create a new commandeService
 
        CarteFidelite carte=new CarteFidelite("14","54",63);
		
        int x,m;
          Scanner sc = new Scanner(System.in);
          int ch;
          System.out.println("Menu :");
          System.out.println("Tapez 1 pour la Gestion des commandes ou 2 pour la Gestion des cartes de fidelite :");
          ch = sc.nextInt();
          
          if(ch==1){
       do{ System.out.println("Gestion Commande :");
        System.out.println("1- Ajouter une commande:");
        System.out.println("2- Modifier une commande:");
        System.out.println("3- Supprimer une commande:");
        System.out.println("4- Lister les commandes:");
        System.out.println("5-Recevoir facture par mail:");
        System.out.println("0- Quitter:");
        System.out.println("donner votre choix \n");
  x = sc.nextInt();

switch(x){

case 1 :
 
    cmd.insert();
    
break;
case 2 :
   Commande c=new Commande(165,"cash","100");

    cmd.update(c);
break;
case 3 :
    cmd.supprimer();
break;
case 4 :
    cmd.afficher();
break;
case 5 :
    MailingService.sendMail("eyabensalem@gmail.com");
break;
       }
       
       
       }while(x!=0); 
  

    }else {
    
    
    do{ System.out.println("Gestion des cartes de fidelite :");
        System.out.println("1- Ajouter une carte:");
        System.out.println("2- Modifier une carte:");
        System.out.println("3- Supprimer une carte:");
        System.out.println("4- Lister les cartes:");
        System.out.println("5- Convertir vos points:");
        System.out.println("6- chercher by id:");
        System.out.println("7- Regenerer une carte:");
        System.out.println("8- Afficher by id:");
        System.out.println("0- Quitter:");
        System.out.println("donner votre choix \n");
  m= sc.nextInt();

switch(m){

case 1 :
    crt.insert();
break;
case 2 :

    crt.update(carte);
break;
case 3 :
    crt.supprimer();
break;
case 4 :
    crt.afficher();
break;
case 5 :
    crt.convertirlespoints(carte);
break;
case 6 :
    
    crt.ChercherCartebyClient("54");
break;
case 7 :
    
    crt.regenererCarte(carte);
break;
case 8 :
    
    crt.afficherbyid("54");
break;
       }
       
       
       }while(m!=0); 
  

    }}
    
    
    
  

    
    }



