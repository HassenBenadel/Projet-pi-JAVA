/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import model.Client;
import model.Fournisseur;
import model.Livreur;
import service.ClientService;
import service.Connect;
import service.ForgetPassword;
import service.FournisseurService;
import service.LivreurService;
import service.MailingService;
import service.PasswordService;
import service.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author hasse
 */
public class ConnectController implements Initializable {

    @FXML
    private Button connect;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Hyperlink forgot;
    @FXML
    private Button create;
    @FXML
    private AnchorPane sceneconnect;

    public static String username;
    public static String typecompte;
    public static String fpmail;
    public static Client client;
    public static Fournisseur fournisseur;
    public static Livreur livreur;
    PasswordService ps = new PasswordService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void SeConneceterHandler(ActionEvent event) {
        AnchorPane profile;

        boolean verify;
        boolean bantest;
        Connect m = new Connect();
        verify = m.verify(email.getText(), password.getText());

        if (verify == false) {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("connection impossible");
            alert.setContentText("Veuillez vérifier votre email et mot de passe ");
            alert.showAndWait();

        } else {

            try {

                UtilisateurService us = new UtilisateurService();
                bantest = us.compareDate(email.getText());
                if (bantest == false) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("connection impossible");
                    alert.setContentText("Vous ne pouvez pas accéder  à votre compte car vous étes banni  ");
                    alert.showAndWait();

                } else {

                    username = us.getNomByEmail(email.getText()) + " " + us.getPreomByEmail(email.getText());
                    typecompte = us.getTypecompteByEmail(email.getText());

                    if (typecompte.equals("client")) {
                        ClientService cs = new ClientService();
                        client = cs.selectClientByEmail(email.getText());

                    } else if (typecompte.equals("fournisseur")) {
                        FournisseurService fs = new FournisseurService();
                        fournisseur = fs.selectFournisseurByEmail(email.getText());

                    } else if (typecompte.equals("livreur")) {
                        LivreurService ls = new LivreurService();
                        livreur = ls.selectLivreurByEmail(email.getText());
                    }

                    profile = FXMLLoader.load(getClass().getResource("Profile.fxml"));
                    sceneconnect.getChildren().removeAll();
                    sceneconnect.getChildren().setAll(profile);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    private void ForgotPassword(ActionEvent event) {
        AnchorPane connect;
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Recupération du mot de passe");
        dialog.setHeaderText("Entrez votre email");
        dialog.setContentText("email :");
        Optional<String> result = dialog.showAndWait();
        fpmail = result.get();
        ForgetPassword fp = new ForgetPassword();
        int code = fp.generateCode();

        try {
            MailingService ms = new MailingService();
            ms.sendMail("hassenbenadel37@gmail.com", code);
            fp.insertCodeInDB(fpmail, code);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            connect = FXMLLoader.load(getClass().getResource("ForgotPassword.fxml"));
            sceneconnect.getChildren().removeAll();
            sceneconnect.getChildren().setAll(connect);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void CreateAccountHandler(ActionEvent event) {

        AnchorPane create;
        try {
            create = FXMLLoader.load(getClass().getResource("Createaccount.fxml"));
            sceneconnect.getChildren().removeAll();
            sceneconnect.getChildren().setAll(create);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
