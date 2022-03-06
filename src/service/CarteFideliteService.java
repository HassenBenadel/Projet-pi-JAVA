/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import interfaces.IcartefideliteService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.CarteFidelite;
import util.MyConnexion;

/**
 *
 * @author EYA
 */
public class CarteFideliteService implements IcartefideliteService {

    Connection cnx = MyConnexion.getInstance().getCnx(); // appliquer la connexion 
    Scanner sc = new Scanner(System.in);


    @Override
    public ObservableList<CarteFidelite> afficher() {
        int count = 0;
        ObservableList<CarteFidelite> crtlist=FXCollections.observableArrayList();
        System.out.println("LISTE DES Cartes de Fidelite: ");
        try {

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("Select * from CarteFidelite");
            while (rs.next()) {

                int Numpoint = rs.getInt(5);
                int id_client = rs.getInt(2);
                int NumCarte = rs.getInt(1);
                Date Datefinvalidite = rs.getDate(3);
                Date Datecreation = rs.getDate(4);

                System.out.println("CarteFidelite num " + (++count) + " Numero de carte:" + NumCarte + " Id client :" + id_client + " Date de fin de validité: " + Datefinvalidite + " Date de creation: " + Datecreation + " Nombre des points: " + Numpoint); // Retreive database columns by Index 
                CarteFidelite carte= new CarteFidelite(NumCarte,id_client,Datecreation,Datefinvalidite,Numpoint);
                crtlist.add(carte);
            }

        } catch (SQLException e) {
        }
        return crtlist;

    }

    @Override
    public void insert(int id) {
       
// DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd ");
        LocalDateTime now = LocalDateTime.now();
        //System.out.println(dtf.format(now));
        int a = LocalDateTime.now().getYear();
        int j = LocalDateTime.now().getDayOfMonth();
        int m =LocalDateTime.now().getMonthValue();
        int h =LocalDateTime.now().getHour();
        int min =LocalDateTime.now().getMinute();
        try {
            String sql = "INSERT INTO CarteFidelite (id_client, Datefinvalidite, Datecreation, Numpoint) VALUES (?, ?, ?, ?)";

            PreparedStatement statement = cnx.prepareStatement(sql);
           /* System.out.println("Donner Numero de la Carte de Fidelite:");
            String n1 = sc.nextLine();
            statement.setString(1, n1);*/
           //   System.out.println("Donner id client:");
          //  String n2 = sc.nextLine();
statement.setInt(1, id);
LocalDateTime gitt = LocalDateTime.of((a+1),m,j, h, min);
            statement.setString(2,gitt.toString());
           statement.setString(3,now.toString());
          //  System.out.println("Donner Nombre des points:");
          //  int Numpoint = sc.nextInt();
          int Numpoint=0;
            statement.setInt(4, Numpoint);
System.out.print("ajout points");

           

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new CarteFidelite was inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(CarteFidelite carte) {

   
       String sql = "update cartefidelite set id_client='" + carte.getId_client() + "',Numpoint='" + carte.getNumpoint() + "'WHERE NumCarte='" + carte.getNumCarte()+"'";

        try {
            Connection cnx = MyConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            int rs = st.executeUpdate(sql);
            if (rs > 0) {
                System.out.println("modfication faite avec succes");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void supprimer(int id) {
        try {
            String sql = "DELETE FROM CarteFidelite WHERE NumCarte=?";

            PreparedStatement statement = cnx.prepareStatement(sql);
           /* System.out.println("Donner numero de carte:");
            String n1 = sc.nextLine();*/
            statement.setInt(1,id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A CarteFidelite was deleted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
     public String convertirlespoints(CarteFidelite Carte,int nbre)
     {  String promo = "erreur";
         CodeReductionService code = new CodeReductionService();
         int pourcentage;
         if(Carte.getNumpoint() > 0)
         {
           // System.out.println("Choisir nombre de points a convertir:");
            //int nbre = sc.nextInt(); 
            System.out.println("lu");
            if(Carte.getNumpoint() >= nbre && nbre<= 100)
            {
                pourcentage=nbre/5;
                System.out.println(pourcentage);
                Integer int_random = ThreadLocalRandom.current().nextInt();  
                promo=nbre+int_random.toString() /*new java.util.Date().toString()*/;
                System.out.println(promo);
                code.insert(promo,pourcentage/*,Carte.getNumCarte()*/);
                 
                 Carte.setNumpoint(Carte.getNumpoint()-nbre);
                updatept(Carte);
                 
            }
         } 
         return promo;
     }
         public void updatept(CarteFidelite carte) {

   
       String sql = "update cartefidelite set Numpoint='" + carte.getNumpoint() + "'WHERE NumCarte='" + carte.getNumCarte()+"'";

        try {
            Connection cnx = MyConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            int rs = st.executeUpdate(sql);
            if (rs > 0) {
                System.out.println("modfication faite avec succes");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
     
    @Override
     public void regenererCarte(int num)
             { 
   
                 try{
                                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd ");
                         LocalDateTime now = LocalDateTime.now();
        //System.out.println(dtf.format(now));
        int a = LocalDateTime.now().getYear();
        int j = LocalDateTime.now().getDayOfMonth();
        int m =LocalDateTime.now().getMonthValue();
        int h =LocalDateTime.now().getHour();
        int min =LocalDateTime.now().getMinute();
                 
                  LocalDateTime gitt = LocalDateTime.of((a+2),m,j, h, min);
                  System.out.println(dtf.format(gitt));
                  //int cadeau=(Carte.getNumpoint())*2;
                  //System.out.println(cadeau);
        String sql="update cartefidelite set Datefinvalidite='" + gitt /*+ "',Numpoint='" + cadeau */+ "'WHERE NumCarte=" + num +"";
            Connection cnx = MyConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            int rs = st.executeUpdate(sql);
            //System.out.println("dkhal");
            if (rs > 0) {
                System.out.println("modfication faite avec succes");

            }
                 }
                 catch(Exception e)
                 {
                     
                 }
             }
    @Override
    public CarteFidelite ChercherCartebyClient(int Num)
    {
        CarteFidelite c=new CarteFidelite();
        
    try{
        String sql="Select * FROM CarteFidelite WHERE id_client = '" + Num + "'";
           Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
             while (rs.next()) {
                int Numpoint = rs.getInt(5);
                int id_client = rs.getInt(2);
                int NumCarte = rs.getInt(1);
                Date Datefinvdalidite = rs.getDate(3);
                Date Datecreation = rs.getDate(4);

CarteFidelite carte= new CarteFidelite(NumCarte,id_client,Datecreation,Datefinvdalidite,Numpoint);
return carte;
             }  
    }catch(SQLException e)
    {
        e.printStackTrace();
    }
    return c;
}
 
    
    
    
    @Override
       public CarteFidelite afficherbyid(int idclient) {
       CarteFidelite c=new CarteFidelite();
        
        try {

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("Select * from CarteFidelite WHERE id_client = '" + idclient + "'");
          while (rs.next()) {
                int Numpoint = rs.getInt(5);
                int id_client = rs.getInt(2);
                int NumCarte = rs.getInt(1);
                Date Datefinvdalidite = rs.getDate(3);
                Date Datecreation = rs.getDate(4);

CarteFidelite carte= new CarteFidelite(NumCarte,id_client,Datecreation,Datefinvdalidite,Numpoint);

                System.out.println("CarteFidelite " + " Numero de carte:" + NumCarte + " Id client :" + id_client + " Date de fin de validité: " + Datefinvdalidite + " Date de creation: " + Datecreation + " Nombre des points: " + Numpoint); // Retreive database columns by Index 
return carte;
          }

        } catch (SQLException e) {
            e.printStackTrace();
        }
return c;
    } 
    
    @Override
    public void Pointparcommande(int ptajout,int num)
    {
        int point=0;
        
    try{
        String sql="Select Numpoint FROM CarteFidelite WHERE NumCarte = '" + num + "'";
           Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
             while (rs.next()) {
               point= rs.getInt(1);
                  }}
                 catch(Exception e)
                 {
                     
                 }
    point+=ptajout;
     try{  String sql="update cartefidelite set Numpoint='" + point + "'WHERE NumCarte='" + num +"'";
     
            Statement st = cnx.createStatement();
            int rs = st.executeUpdate(sql);
            System.out.println("mm");
            if (rs > 0) {
                System.out.println("modfication faite avec succes");

            }
                 }
                 catch(Exception e)
                 {
                     
                 }
    }
    
    
    
    
    
    
    
}
