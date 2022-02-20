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
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
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
    public void afficher() {
        int count = 0;
        System.out.println("LISTE DES Cartes de Fidelite: ");
        try {

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("Select * from CarteFidelite");
            while (rs.next()) {

                int Numpoint = rs.getInt(5);
                String IdClient = rs.getString(2);
                String NumCarte = rs.getString(1);
                Date Datefinvdalidite = rs.getDate(3);
                Date Datecreation = rs.getDate(4);

                System.out.println("CarteFidelite num " + (++count) + " Numero de carte:" + NumCarte + " Id client :" + IdClient + " Date de fin de validité: " + Datefinvdalidite + " Date de creation: " + Datecreation + " Nombre des points: " + Numpoint); // Retreive database columns by Index 
            }

        } catch (SQLException e) {
        }

    }

    @Override
    public void insert() {
       
// DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd ");
        LocalDateTime now = LocalDateTime.now();
        //System.out.println(dtf.format(now));
        int a = LocalDateTime.now().getYear();
        int j = LocalDateTime.now().getDayOfMonth();
        int m =LocalDateTime.now().getMonthValue();
        int h =LocalDateTime.now().getHour();
        int min =LocalDateTime.now().getMinute();
        try {
            String sql = "INSERT INTO CarteFidelite VALUES (?, ?, ?, ?, ?)";

            PreparedStatement statement = cnx.prepareStatement(sql);
            System.out.println("Donner Numero de la Carte de Fidelite:");
            String n1 = sc.nextLine();
            statement.setString(1, n1);
              System.out.println("Donner id client:");
            String n2 = sc.nextLine();
statement.setString(2, n2);

          
 LocalDateTime gitt = LocalDateTime.of((a+1),m,j, h, min);
            statement.setString(3,gitt.toString());
            
            statement.setString(4,now.toString());
            System.out.println("Donner Nombre des points:");
            int Numpoint = sc.nextInt();
            statement.setInt(5, Numpoint);
System.out.print("ajout points");

           

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new CarteFidelite was inserted successfully!");
            }
        } catch (SQLException e) {
        }
    }

    @Override
    public void update(CarteFidelite carte) {

   
       String sql = "update cartefidelite set IdClient='" + carte.getIdClient() + "',Numpoint='" + carte.getNumpoint() + "'WHERE NumCarte='" + carte.getNumCarte()+"'";

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
    public void supprimer() {
        try {
            String sql = "DELETE FROM CarteFidelite WHERE NumCarte=?";

            PreparedStatement statement = cnx.prepareStatement(sql);
            System.out.println("Donner numero de carte:");
            String n1 = sc.nextLine();
            statement.setString(1, n1);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A CarteFidelite was deleted successfully!");
            }
        } catch (SQLException e) {
        }
    }
     public String convertirlespoints(CarteFidelite Carte)
     {  String promo = "erreur";
         CodeReductionService code = new CodeReductionService();
         int pointconvertir,pourcentage;
         if(Carte.getNumpoint() != 0)
         {
            System.out.println("Choisir nombre de points a convertir:");
            int nbre = sc.nextInt(); 
            if(Carte.getNumpoint() >= nbre)
            {
                pourcentage=nbre/5;
                promo=nbre  + "2022";
                code.insert(promo,pourcentage/*,Carte.getNumCarte()*/);
                 
                 Carte.setNumpoint(Carte.getNumpoint()-nbre);
            }
         } 
         return promo;
     }
    @Override
     public void regenererCarte(CarteFidelite Carte)
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
                  int cadeau=(Carte.getNumpoint())*2;
                  System.out.println(cadeau);
        String sql="update cartefidelite set Datefinvalidite='" + gitt + "',Numpoint='" + cadeau + "'WHERE NumCarte='" + Carte.getNumCarte()+"'";
            Connection cnx = MyConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            int rs = st.executeUpdate(sql);
            System.out.println("dkhal");
            if (rs > 0) {
                System.out.println("modfication faite avec succes");

            }
                 }
                 catch(Exception e)
                 {
                     
                 }
             }
    @Override
    public void ChercherCartebyClient(String IdClient)
{
    try{
        String sql="SELECT * FROM cartefidelite WHERE IdClient = ' " + IdClient + "'";
           Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.last();
            int rows = rs.getRow();
            if (rows!=1) {
            System.out.println("Carte Trouve");
            } 
    }catch(SQLException e)
    {
        
    }
}
 
    
    
    
    @Override
       public void afficherbyid(String Idclient) {
       
        
        try {

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("Select * from CarteFidelite WHERE IdClient = '" + Idclient + "'");
          while (rs.next()) {
                int Numpoint = rs.getInt(5);
                String IdClient = rs.getString(2);
                String NumCarte = rs.getString(1);
                Date Datefinvdalidite = rs.getDate(3);
                Date Datecreation = rs.getDate(4);


                System.out.println("CarteFidelite " + " Numero de carte:" + NumCarte + " Id client :" + IdClient + " Date de fin de validité: " + Datefinvdalidite + " Date de creation: " + Datecreation + " Nombre des points: " + Numpoint); // Retreive database columns by Index 
}

        } catch (SQLException e) {
            e.printStackTrace();
        }

    } 
    
    public void Pointparcommande(int ptajout,String num)
    {
     try{  String sql="update cartefidelite set Numpoint='" + ptajout + "'WHERE NumCarte='" + num +"'";
        Connection cnx = MyConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            int rs = st.executeUpdate(sql);
            System.out.println("dkhal");
            if (rs > 0) {
                System.out.println("modfication faite avec succes");

            }
                 }
                 catch(Exception e)
                 {
                     
                 }
    }
    
    
    
    
    
    
    
}
