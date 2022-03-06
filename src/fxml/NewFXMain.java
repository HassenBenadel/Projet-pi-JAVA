/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author EYA
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       Parent root;
        try {
           //root = FXMLLoader.load(getClass().getResource("ProduitFxInterface.fxml"));
         // root = FXMLLoader.load(getClass().getResource("cartefideliteclient2.fxml"));
root = FXMLLoader.load(getClass().getResource("panieruser.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setTitle("Gestion commande");
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
