/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import interfaces.IcartefideliteService;
import interfaces.IcommandeService;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import util.MyConnexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.CodeReduction;
import model.Commande;

public class CommandeService implements IcommandeService {

    Connection cnx = MyConnexion.getInstance().getCnx(); // appliquer la connexion 
    Scanner sc = new Scanner(System.in);

    @Override
    public ObservableList<Commande> afficher() {
        int count = 0;
        ObservableList<Commande> commandelist = FXCollections.observableArrayList();
        System.out.println("LISTE DES COMMANDES: ");
        try {

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("Select * from commande ORDER BY  DateCommande  DESC");
            while (rs.next()) {

                int id_commande = rs.getInt(1);
                String methpaiement = rs.getString(2);
                int NumCarte = rs.getInt(5);
                Float totalprix = rs.getFloat(3);
                Float totalpanier = rs.getFloat(4);
                Date DateCommande = rs.getDate(6);
                String code = rs.getString(7);
                String iduser = rs.getString(8);
                System.out.println("Commande num " + (++count) + " Id Commande: " + id_commande + " Methode de paiement: " + methpaiement + " Total prix: " + totalprix + " Total panier: " + totalpanier + " Numero de Carte: " + NumCarte + " Date de creation : " + DateCommande + " Code de reduction: " + code + "user" + iduser); // Retreive database columns by Index 
                Commande commande = new Commande(id_commande, methpaiement, totalprix, totalpanier, NumCarte, DateCommande, code, iduser); // Retreive database columns by Index       
                commandelist.add(commande);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commandelist;

    }

    @Override
    public void insert(String methpaiement, String code, int carte, String iduser) {

        int gain = 0;
        LocalDateTime now = LocalDateTime.now();
        CodeReduction c = new CodeReduction("20", 10/*,"12345"*/);
        try {
            String sql = "INSERT INTO commande(methpaiement, totalprix, totalpanier, NumCarte, DateCommande, code, iduser) VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = cnx.prepareStatement(sql);
//System.out.println("Idcommande");
//int  n1 = sc.nextInt();
//statement.setString(1,id_commande);
//String methpaiement= sc.nextLine();
//System.out.println("Choisir methode de paiement:en ligne ou bien cash");
//methpaiement= sc.nextLine();
            statement.setString(1, methpaiement);
//String n3 ;
//System.out.println("Entrez votre code");
// n3 = sc.nextLine();

            statement.setString(6, code);
            System.out.println("numero de carte");
//String carte ;
//carte = sc.nextLine();
            statement.setInt(4, carte);
            statement.setString(5, now.toString());

            statement.setFloat(3, this.calculpanier(iduser));
            float panier = this.calculpanier(iduser);
            Commande cmd = new Commande(methpaiement, carte, c);
            float prix = this.calculprix(panier, c);
            statement.setFloat(2, prix);
            statement.setString(7, iduser);
            gain = (int) (prix * 20 / 100);

            IcartefideliteService crt = new CarteFideliteService();
            crt.Pointparcommande(gain, carte);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new commande was inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Commande c) {

        String sql = "update commande set methpaiement=' " + c.getMethpaiement() + " ' WHERE Id_Commande=" + c.getId_commande();

        try {

            Statement st = cnx.createStatement();
            int rs = st.executeUpdate(sql);
            if (rs > 0) {
                System.out.println("modfication faite avec succes");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void supprimer(int id) {
        try {
            String sql = "DELETE FROM commande WHERE id_commande=?";

            PreparedStatement statement = cnx.prepareStatement(sql);
//System.out.println("Donner Id commande:");
            //String n1 = sc.nextLine();
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A commande was deleted successfully!");
            }
        } catch (SQLException e) {
        }
    }

    public float calculpanier(String iduser) {
        {
            float total = 0;
            try {
                String sql = "Select totalpanier FROM Panier where idclient= '" + iduser + "'";
                Statement st = cnx.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    Float totalpanier = rs.getFloat(1);
                    total += totalpanier;
                }

            } catch (Exception e) {

            }
            return total;
        }
    }

    public float calculprix(float totalpanier, CodeReduction c) {
        float prix = 0;
        try {
            prix = totalpanier;
            System.out.println(c.getPourcentage());
            float reduction = prix * (c.getPourcentage()) / 100;
            prix = prix - reduction;
            System.out.println(prix);
        } catch (Exception e) {

        }
        return prix;
    }

    @Override
    public ObservableList<Commande> afficherbyid(String idcl) {
        int count = 0;
        ObservableList<Commande> commandelist = FXCollections.observableArrayList();
        System.out.println("LISTE DES COMMANDES: ");
        try {

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("Select * from commande where iduser= '" + idcl + "'");
            while (rs.next()) {

                int id_commande = rs.getInt(1);
                String methpaiement = rs.getString(2);
                int NumCarte = rs.getInt(5);
                Float totalprix = rs.getFloat(3);
                Float totalpanier = rs.getFloat(4);
                Date DateCommande = rs.getDate(6);
                String code = rs.getString(7);
                String iduser = rs.getString(8);
                System.out.println("Commande num " + (++count) + " Id Commande: " + id_commande + " Methode de paiement: " + methpaiement + " Total prix: " + totalprix + " Total panier: " + totalpanier + " Numero de Carte: " + NumCarte + " Date de creation : " + DateCommande + " Code de reduction: " + code + "user" + iduser); // Retreive database columns by Index 
                Commande commande = new Commande(id_commande, methpaiement, totalprix, totalpanier, NumCarte, DateCommande, code, iduser); // Retreive database columns by Index       
                commandelist.add(commande);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commandelist;

    }

}
