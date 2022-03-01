/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import static Gui.ConnectController.client;
import static Gui.ConnectController.fournisseur;
import static Gui.ConnectController.livreur;
import static Gui.ConnectController.typecompte;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import static Gui.ConnectController.username;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import service.ClientService;
import service.FournisseurService;
import service.LivreurService;

/**
 * FXML Controller class
 *
 * @author hasse
 */
public class ProfileController implements Initializable {

    @FXML
    private TextArea nom;
    @FXML
    private TextArea ville;
    @FXML
    private TextArea email;
    @FXML
    private TextArea pays;
    @FXML
    private TextArea telephone;
    @FXML
    private TextArea prenom;
    @FXML
    private Label user;
    @FXML
    private AnchorPane profilescene;
    @FXML
    private Button supprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user.setText(username);
        System.out.println(username);
    }

    @FXML
    private void changerTelephone(ActionEvent event) {
        AnchorPane modify;
        try {
            modify = FXMLLoader.load(getClass().getResource("ChangerTelephone.fxml"));
            profilescene.getChildren().removeAll();
            profilescene.getChildren().setAll(modify);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void changerPays(ActionEvent event) {
        AnchorPane modify;
        try {
            modify = FXMLLoader.load(getClass().getResource("ChangerPays.fxml"));
            profilescene.getChildren().removeAll();
            profilescene.getChildren().setAll(modify);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void changerVille(ActionEvent event) {
        AnchorPane modify;
        try {
            modify = FXMLLoader.load(getClass().getResource("ChangerVille.fxml"));
            profilescene.getChildren().removeAll();
            profilescene.getChildren().setAll(modify);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void changerEmail(ActionEvent event) {
        AnchorPane modify;
        try {
            modify = FXMLLoader.load(getClass().getResource("ChangerEmail.fxml"));
            profilescene.getChildren().removeAll();
            profilescene.getChildren().setAll(modify);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void changerPrenom(ActionEvent event) {

        AnchorPane modify;
        try {
            modify = FXMLLoader.load(getClass().getResource("ChangerPrenom.fxml"));
            profilescene.getChildren().removeAll();
            profilescene.getChildren().setAll(modify);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void changerNom(ActionEvent event) {
        AnchorPane modify;
        try {
            modify = FXMLLoader.load(getClass().getResource("ChangerNom.fxml"));
            profilescene.getChildren().removeAll();
            profilescene.getChildren().setAll(modify);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void changerNom(MouseEvent event) {
    }

    @FXML
    private void deleteAccount(ActionEvent event) {
        if (typecompte.equals("client")) {
            ClientService cs = new ClientService();
            System.out.println(client);
            cs.supprimerClient(client);
        } else if (typecompte.equals("fournisseur")) {
            FournisseurService fs = new FournisseurService();
            fs.supprimerFournisseur(fournisseur);
        } else if (typecompte.equals("livreur")) {
            LivreurService cs = new LivreurService();
            cs.supprimerLivreur(livreur);
        }
    }

}
