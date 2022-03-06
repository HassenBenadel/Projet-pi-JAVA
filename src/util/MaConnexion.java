/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author hasse
 */
public class MaConnexion {

    //Credentials
    final static String url = "jdbc:mysql://127.0.0.1:3306/blog";
    final static String username = "root";
    final static String password = "";

    private Connection cnx;
    //Singleton #1
    static MaConnexion instance = null ;  

    //Constructeur
    //Singleton #2 : private
    private MaConnexion() {
        try {
            cnx = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getCnx() {
        return cnx;
    }
    //Singleton #3
    public static MaConnexion getInstance(){
        if(instance == null ){
            instance = new MaConnexion(); 
        }
        return instance ; 
    }

}
