/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class FXMLController implements Initializable {

    @FXML
    private Button supprimerbq;
    @FXML
    private Button supprimermg;
    @FXML
    private Button ajoutermg;
    @FXML
    private Button ajouterbq;
    @FXML
    private Label text2;
    @FXML
    private Label text1;
    @FXML
    private Label text3;
    @FXML
    private Label text4;
    @FXML
    private Label text5;
    @FXML
    private Label text6;
    @FXML
    private AnchorPane page1;
    @FXML
    private Button mD;
    @FXML
    private Button MF;
    @FXML
    private Button MM;
    @FXML
    private Button MA;
    @FXML
    private Button MG;
    @FXML
    private Button MW;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void supprimerBoutique(ActionEvent event) {
        
         AnchorPane create;
        try {
            create = FXMLLoader.load(getClass().getResource("INTERFACEsupp.fxml"));
            page1.getChildren().removeAll();
            page1.getChildren().setAll(create);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void supprimerMagasin(ActionEvent event) {
        
         AnchorPane create;
        try {
            create = FXMLLoader.load(getClass().getResource("interfaceSuppMg.fxml"));
            page1.getChildren().removeAll();
            page1.getChildren().setAll(create);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void ajouterMagasin(ActionEvent event) {

        AnchorPane create;
        try {
            create = FXMLLoader.load(getClass().getResource("interface2.fxml"));
            page1.getChildren().removeAll();
            page1.getChildren().setAll(create);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
        
        
        
    }

    @FXML
    private void ajouterBoutique(ActionEvent event) {
         AnchorPane create;
        try {
            create = FXMLLoader.load(getClass().getResource("interfaceAjoutBq.fxml"));
            page1.getChildren().removeAll();
            page1.getChildren().setAll(create);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void ModifierBoutique(ActionEvent event) {
    }

    @FXML
    private void ModifierMagasin(ActionEvent event) {
    }
    
    

}
