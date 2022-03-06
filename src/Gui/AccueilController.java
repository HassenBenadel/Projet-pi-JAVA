/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import service.PanierService;
import service.lignecService;

/**
 * FXML Controller class
 *
 * @author EYA
 */
public class AccueilController implements Initializable {

    @FXML
    private Button voirpanier;
    @FXML
    private Button cartecoordonnees;
    @FXML
    private Button ajouterligne;
    @FXML
    private TextField quantite;

    /**
     * Initializes the controller class.
     */
    lignecService ligne=new lignecService();
    PanierService p= new PanierService();
    @FXML
    private AnchorPane accueil;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickedbutton(ActionEvent event) {
        if(event.getSource() == voirpanier)
        {
                          AnchorPane create;
        try {
            create = FXMLLoader.load(getClass().getResource("panieruser.fxml"));
            accueil.getChildren().removeAll();
            accueil.getChildren().setAll(create);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        }else if(event.getSource() == ajouterligne)
        { int trouve=ligne.chercherprod(15,"11");
        if(trouve!=0)
        {ligne.augmenterquantite(15,"11");
        }else{
            ligne.insert("11",15,Integer.parseInt(quantite.getText()));
        }
         
            Float prix=p.calcultotal("11");
        p.update(prix,"11");

        }else if(event.getSource() == cartecoordonnees)
        {
                                    AnchorPane onecarte;
        try {
            onecarte = FXMLLoader.load(getClass().getResource("cartefideliteclient2.fxml"));
            accueil.getChildren().removeAll();
            accueil.getChildren().setAll(onecarte);
        } catch (IOException ex) {
            ex.printStackTrace();
        }  
        }
    }
    
}
