/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

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
import model.CodeReduction;
import model.Commande;


public class CommandeService implements IcommandeService{
  Connection cnx = MyConnexion.getInstance().getCnx(); // appliquer la connexion 
  Scanner sc = new Scanner(System.in);
  @Override
    public void afficher() {
int count=0;
System.out.println("LISTE DES COMMANDES: ");
        try {
            
            Statement st =cnx.createStatement();
            ResultSet rs = st.executeQuery("Select * from commande ORDER BY  DateCommande  DESC");
            while (rs.next()){
                
                  int id_commande = rs.getInt(1);
    String methpaiement = rs.getString(2);
     String NumCarte = rs.getString(5);
    Float totalprix = rs.getFloat(3);
    Float totalpanier = rs.getFloat(4);
 Date DateCommande = rs.getDate(6);
  String code = rs.getString(7);
    System.out.println("Commande num " + (++count) + " Id Commande: " + id_commande + " Methode de paiement: "+ methpaiement +" Total prix: " + totalprix + " Total panier: " +totalpanier + " Numero de Carte: " + NumCarte + " Date de creation : " + DateCommande + " Code de reduction: " + code ); // Retreive database columns by Index 
            }

        } catch (SQLException e) {
        }

    }
  @Override
        public void insert() {


        LocalDateTime now = LocalDateTime.now();
        CodeReduction code=new CodeReduction("202022",4/*,"12345"*/);
  try {  String sql = "INSERT INTO commande VALUES (?, ?, ?, ?, ?, ?, ?)";


PreparedStatement statement = cnx.prepareStatement(sql);
System.out.println("Donner Id commande:");
 int n1 = sc.nextInt();
statement.setInt(1, n1);
String methpaiement= sc.nextLine();
System.out.println("Choisir methode de paiement:en ligne ou bien cash");
methpaiement= sc.nextLine();
statement.setString(2, methpaiement);
String n3 = sc.nextLine();
System.out.println("Entrez votre code");
 n3 = sc.nextLine();
statement.setString(7, n3);
System.out.println("Entrez numero de carte");
String carte = sc.nextLine();
statement.setString(5, carte);
 statement.setString(6,now.toString());
  Commande cmd=new Commande(n1,methpaiement,carte,code);
  statement.setFloat(4,cmd.calculpanier());
statement.setFloat(3,cmd.calculprix());
 int rowsInserted = statement.executeUpdate();
if (rowsInserted > 0) {
    System.out.println("A new commande was inserted successfully!");
}}catch(SQLException e) {
        }
        }
        
        

@Override
    public void update(Commande c) {

      String sql = "update commande set methpaiement=' " + c.getMethpaiement() + " ' ,totalprix= " + c.getTotalprix() + ",totalpanier= " + c.getTotalpanier() + "WHERE Id_Commande=" + c.getId_Commande();

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
  public void supprimer()
{
try{
String sql = "DELETE FROM commande WHERE id_commande=?";
 
PreparedStatement statement = cnx.prepareStatement(sql);
System.out.println("Donner Id commande:");
 int n1 = sc.nextInt();
statement.setInt(1, n1);
 
int rowsDeleted = statement.executeUpdate();
if (rowsDeleted > 0) {
    System.out.println("A commande was deleted successfully!");
}}catch(SQLException e) {
        }
}

























}
