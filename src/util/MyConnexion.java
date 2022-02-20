
package util;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;


public class MyConnexion {

    //Credentials
    final static String url = "jdbc:mysql://localhost:3306/commande";
    final static String username = "root";
    final static String password = "";
//att
    private Connection cnx;
    //Singleton #1
    static MyConnexion instance = null ;  

    //Constructeur
    //Singleton #2 : private
    private MyConnexion() {
        try {
            cnx = DriverManager.getConnection(url, username, password); //initialiser
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getCnx() {
        return cnx;
    }
    //Singleton #3
    public static MyConnexion getInstance(){
        if(instance == null ){
            instance = new MyConnexion(); 
        }
        return instance ; 
    }

}
