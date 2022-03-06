/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static gui.ConnectController.client;
import static gui.ConnectController.fournisseur;
import static gui.ConnectController.livreur;
import static gui.ConnectController.typecompte;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import service.ClientService;
import service.Connect;
import service.FournisseurService;
import service.LivreurService;

/**
 * FXML Controller class
 *
 * @author hasse
 */
public class SupprimerVerifPasswordController implements Initializable {

    @FXML
    private PasswordField password;
    @FXML
    private Button deletebtn;
    @FXML
    private AnchorPane deleteacc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void deleteaccount(ActionEvent event) {
        boolean test;
        if (typecompte.equals("client")) {
            Connect co = new Connect();
            test = co.verify(client.getEmail(), password.getText());
            if (test) {
                ClientService cs = new ClientService();
                cs.supprimerClient(client);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Compte Supprimé");
                alert.setContentText("Votre compte a été supprimé avec succés ");
                alert.showAndWait();

                try {
                    AnchorPane connect = FXMLLoader.load(getClass().getResource("Connect.fxml"));
                    deleteacc.getChildren().removeAll();
                    deleteacc.getChildren().setAll(connect);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Supression impossible");
                alert.setContentText("Veuillez vérifier votre mot de passe ");
                alert.showAndWait();

            }

        }
        
         if (typecompte.equals("fournisseur")) {
            Connect co = new Connect();
            test = co.verify(fournisseur.getEmail(), password.getText());
            if (test) {
                FournisseurService fs = new FournisseurService();
                fs.supprimerFournisseur(fournisseur);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Compte Supprimé");
                alert.setContentText("Votre compte a été supprimé avec succés ");
                alert.showAndWait();

                try {
                    AnchorPane connect = FXMLLoader.load(getClass().getResource("Connect.fxml"));
                    deleteacc.getChildren().removeAll();
                    deleteacc.getChildren().setAll(connect);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Supression impossible");
                alert.setContentText("Veuillez vérifier votre mot de passe ");
                alert.showAndWait();

            }

        } if (typecompte.equals("livreur")) {
            Connect co = new Connect();
            test = co.verify(livreur.getEmail(), password.getText());
            if (test) {
                LivreurService ls = new LivreurService();
                ls.supprimerLivreur(livreur);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Compte Supprimé");
                alert.setContentText("Votre compte a été supprimé avec succés ");
                alert.showAndWait();

                try {
                    AnchorPane connect = FXMLLoader.load(getClass().getResource("Connect.fxml"));
                    deleteacc.getChildren().removeAll();
                    deleteacc.getChildren().setAll(connect);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Supression impossible");
                alert.setContentText("Veuillez vérifier votre mot de passe ");
                alert.showAndWait();

            }

        }

    }

}
