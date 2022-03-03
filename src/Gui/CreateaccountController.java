/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import model.Client;
import model.Fournisseur;
import model.Livreur;
import service.ClientService;
import service.FournisseurService;
import service.LivreurService;
import service.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author hasse
 */
public class CreateaccountController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField pays;
    @FXML
    private TextField email;
    @FXML
    private TextField prenom;
    @FXML
    private Button signup;
    @FXML
    private TextField telephone;
    @FXML
    private TextField ville;
    @FXML
    private PasswordField password1;
    @FXML
    private PasswordField password2;
    @FXML
    private ListView<String> typecomptelist;
    @FXML
    private CheckBox checkbox;
    @FXML
    private Hyperlink conditions;
    @FXML
    private Button btnannuler;
    private TextField profilepic;
    @FXML
    private AnchorPane scenecreateaccount;

    String[] typecompte = {"client", "fournisseur", "livreur"};
    @FXML
    private Button retour;
    @FXML
    private ImageView fximg;
    @FXML
    private Button browse;

    String sPath;
    public static Client client;
    public static Fournisseur fournisseur;
    public static Livreur livreur;
    public static String username;

    UtilisateurService us = new UtilisateurService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typecomptelist.getItems().addAll(typecompte);

        telephone.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                telephone.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

    }

    @FXML
    private void uploadImage(ActionEvent event) {
        final FileChooser fc = new FileChooser();
        try {
            String path;
            File file = fc.showOpenDialog(null);
            path = file.getAbsolutePath();
            FileInputStream input = new FileInputStream(path);
            Image image = new Image(input);
            fximg.setImage(image);
            sPath = path;
            sPath = sPath.replace("\\", "\\\\");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void createaccount(ActionEvent event) {
        String type = typecomptelist.getSelectionModel().getSelectedItems().toString();

        Boolean passwordCompatible = false;
        Boolean checkboxticked = false;

        String name = nom.getText();
        String surname = prenom.getText();
        String mail = email.getText();
        int phone = Integer.parseInt(telephone.getText());
        String country = pays.getText();
        String town = ville.getText();
        String pass1 = password1.getText();
        String pass2 = password2.getText();

        if (checkbox.isSelected()) {
            checkboxticked = true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Conditions d'utilisation");
            alert.setContentText("Vous n'avez pas accepté les conditions d'utilisation");
            alert.showAndWait();

        }
        if (pass1.equals(pass2)) {
            passwordCompatible = true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Probléme au nvieau du mot de passe");
            alert.setContentText("Mot de passe et confimation mot de passe ne sont pas compatibles ");
            alert.showAndWait();

        }

        if (type.equals("[client]")) {

            if ((passwordCompatible == true) && (checkboxticked == true)) {
                client = new Client(name, surname, mail, phone, sPath, country, town, pass1, "client");
                System.out.println(client.getPassword());
                ClientService cs = new ClientService();
                cs.ajouterClient(client);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Compte créé");
                alert.setContentText("Votre compte a été créé avec succés");
                alert.showAndWait();

                try {
                    AnchorPane connect = FXMLLoader.load(getClass().getResource("Connect.fxml"));
                    scenecreateaccount.getChildren().removeAll();
                    scenecreateaccount.getChildren().setAll(connect);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }

        } else if (type.equals("[fournisseur]")) {

            if ((passwordCompatible == true) && (checkboxticked == true)) {

                fournisseur = new Fournisseur(name, surname, mail, phone, sPath, country, town, pass1, "fournisseur");
                FournisseurService fs = new FournisseurService();
                fs.ajouterFournisseur(fournisseur);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Compte créé");
                alert.setContentText("Votre compte a été créé avec succés");
                alert.showAndWait();

                try {
                    AnchorPane connect = FXMLLoader.load(getClass().getResource("Connect.fxml"));
                    scenecreateaccount.getChildren().removeAll();
                    scenecreateaccount.getChildren().setAll(connect);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }

        } else if (type.equals("[livreur]")) {

            if ((passwordCompatible == true) && (checkboxticked == true)) {
                TextInputDialog dialog = new TextInputDialog("exp : smartphones");
                dialog.setTitle("Saisir Secteur");
                dialog.setHeaderText("Entrez votre secteur d'activité");
                dialog.setContentText("Secteur d'activité :");
                Optional<String> result = dialog.showAndWait();
                String secteur = result.get();

                livreur = new Livreur(name, surname, mail, phone, sPath, country, town, pass1, "livreur", secteur);
                LivreurService ls = new LivreurService();
                ls.ajouterLivreur(livreur);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Compte créé");
                alert.setContentText("Votre compte a été créé avec succés");
                alert.showAndWait();

                try {
                    AnchorPane connect = FXMLLoader.load(getClass().getResource("Connect.fxml"));
                    scenecreateaccount.getChildren().removeAll();
                    scenecreateaccount.getChildren().setAll(connect);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        }

    }

    @FXML
    private void reset(ActionEvent event) {
        try {
            AnchorPane create = FXMLLoader.load(getClass().getResource("Createaccount.fxml"));
            scenecreateaccount.getChildren().removeAll();
            scenecreateaccount.getChildren().setAll(create);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void goback(ActionEvent event) {

        try {
            AnchorPane connect = FXMLLoader.load(getClass().getResource("Connect.fxml"));
            scenecreateaccount.getChildren().removeAll();
            scenecreateaccount.getChildren().setAll(connect);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
