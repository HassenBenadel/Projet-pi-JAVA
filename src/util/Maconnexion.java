/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hadir
 */
public class Maconnexion {
    //connection a la bd
    final static String URL = "jdbc:mysql://127.0.0.1:3306/Projet";
    final static String USERNAME = "root";
    final static String PWD = "";
    
    private Connection cnx;
    static Maconnexion instance = null;
    private Maconnexion() {
        try {
            cnx = (Connection) DriverManager.getConnection(URL, USERNAME, PWD);
            System.out.println("Connexion avec succes");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getCnx() {
        return cnx;
    }

    public static Maconnexion getInstance() {
        if(instance == null) {
            instance = new Maconnexion();
        }
        return instance;
    }
}
