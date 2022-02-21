/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Ibtihel
 */
public class MaConnexion {
    //BD credentials
    final static String URL = "jdbc:mysql://127.0.0.1:3306/projet";
    final static String USERNAME = "root";
    final static String MDP = "";
    
    //att
    private Connection cnx;
    //singleton #1
    static MaConnexion instance = null;
    
    //constructeurs
    //singleton #2 : private
      private MaConnexion() {
        try {
            cnx = DriverManager.getConnection(URL, USERNAME, MDP);
            System.out.println("connexion avec sucées");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

   }
    //Getters & Setters 

    public Connection getCnx() {
        return cnx;
    }

  //singleton #3

    public static MaConnexion getInstance() {
        if (instance == null){
            instance = new MaConnexion();
        }
        return instance;
    }
    
    
}
