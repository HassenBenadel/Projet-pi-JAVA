/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import util.MyConnexion;

/**
 *
 * @author EYA
 */
public class CodeReductionService {
  Connection cnx = MyConnexion.getInstance().getCnx(); // appliquer la connexion 
    Scanner sc = new Scanner(System.in);
    public void insert(String code,int pourcentage)
    {
         try {
            String sql = "INSERT INTO codereduction VALUES (?, ?)";

            PreparedStatement statement = cnx.prepareStatement(sql);
          System.out.println("jout");
 
          statement.setString(1, code);
          //  statement.setString(3, numcarte);
            statement.setInt(2, pourcentage);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Votre code promo est disponible tapez "+ code + " pour l'utiliser et obtenir jusqu'a "+pourcentage + " % de reduction sur l'achat");
            }
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }
    

    public void supprimer(String codeutilise)
    {
                try {
            String sql = "DELETE FROM codereduction WHERE code=?";

            PreparedStatement statement = cnx.prepareStatement(sql);

            statement.setString(1, codeutilise);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A Code reduction was deleted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int pourcentagecode(String code) 
    {
        int p=0;
        try{
            String sql="Select pourcentage From codereduction WHERE code = '" + code + "'";
               Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
             while (rs.next()) {
                p=rs.getInt(1);
        }}catch(SQLException e) {
        }
        return p;
    }
    
}
