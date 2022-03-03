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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import static Gui.ConnectController.username;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private Label user;
    @FXML
    private AnchorPane profilescene;
    @FXML
    private Button supprimer;
    @FXML
    private Label name;
    @FXML
    private Label surname;
    @FXML
    private Label mail;
    @FXML
    private Label town;
    @FXML
    private Label country;
    @FXML
    private Label phone;
    @FXML
    private ImageView userimg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user.setText(username);
        if (typecompte.equals("client")) {

            name.setText(client.getNom());
            surname.setText(client.getPrenom());
            mail.setText(client.getEmail());
            town.setText(client.getVille());
            country.setText(client.getPays());
            phone.setText(String.valueOf(client.getTelephone()));
            try {
                String path = client.getImage();
                FileInputStream input = new FileInputStream(path);
                Image image = new Image(input);
                userimg.setImage(image);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (typecompte.equals("fournisseur")) {

            name.setText(fournisseur.getNom());
            surname.setText(fournisseur.getPrenom());
            mail.setText(fournisseur.getEmail());
            town.setText(fournisseur.getVille());
            country.setText(fournisseur.getPays());
            phone.setText(String.valueOf(fournisseur.getTelephone()));

            try {
                String path = fournisseur.getImage();
                FileInputStream input = new FileInputStream(path);
                Image image = new Image(input);
                userimg.setImage(image);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (typecompte.equals("livreur")) {

            name.setText(livreur.getNom());
            surname.setText(livreur.getPrenom());
            mail.setText(livreur.getEmail());
            town.setText(livreur.getVille());
            country.setText(livreur.getPays());
            phone.setText(String.valueOf(livreur.getTelephone()));

            try {
                String path = livreur.getImage();
                FileInputStream input = new FileInputStream(path);
                Image image = new Image(input);
                userimg.setImage(image);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @FXML
    private void changerTelephone(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Modification de votre numero de telephone");
        dialog.setHeaderText("Entrez votre nouveau Numero de telephone");
        dialog.setContentText("Numero de telephone :");
        Optional<String> result = dialog.showAndWait();

        int tel = Integer.parseInt(result.get());

        if (typecompte.equals("client")) {
            client.setTelephone(tel);
            ClientService cs = new ClientService();
            cs.modifierClient(client);

        } else if (typecompte.equals("fournisseur")) {
            fournisseur.setTelephone(tel);
            FournisseurService fs = new FournisseurService();
            fs.modifierFournisseur(fournisseur);

        } else if (typecompte.equals("livreur")) {
            livreur.setTelephone(tel);
            LivreurService ls = new LivreurService();
            ls.modifierLivreur(livreur);

        }

    }

    @FXML
    private void changerPays(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Modification de votre Pays");
        dialog.setHeaderText("Entrez un novueau pays");
        dialog.setContentText("Pays : ");
        Optional<String> result = dialog.showAndWait();

        String pays = result.get();

        if (typecompte.equals("client")) {
            client.setPays(pays);
            ClientService cs = new ClientService();
            cs.modifierClient(client);

        } else if (typecompte.equals("fournisseur")) {
            fournisseur.setPays(pays);
            FournisseurService fs = new FournisseurService();
            fs.modifierFournisseur(fournisseur);

        } else if (typecompte.equals("livreur")) {
            livreur.setPays(pays);
            LivreurService ls = new LivreurService();
            ls.modifierLivreur(livreur);

        }

    }

    @FXML
    private void changerVille(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Modification de votre ville");
        dialog.setHeaderText("Entrez une nouvelle ville");
        dialog.setContentText("Ville : ");
        Optional<String> result = dialog.showAndWait();

        String ville = result.get();

        if (typecompte.equals("client")) {
            client.setVille(ville);
            ClientService cs = new ClientService();
            cs.modifierClient(client);

        } else if (typecompte.equals("fournisseur")) {
            fournisseur.setVille(ville);
            FournisseurService fs = new FournisseurService();
            fs.modifierFournisseur(fournisseur);

        } else if (typecompte.equals("livreur")) {
            livreur.setVille(ville);
            LivreurService ls = new LivreurService();
            ls.modifierLivreur(livreur);

        }

    }

    @FXML
    private void changerEmail(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Modification de votre Email");
        dialog.setHeaderText("Entrez un nouveau Email");
        dialog.setContentText("Email : ");
        Optional<String> result = dialog.showAndWait();

        String email = result.get();

        if (typecompte.equals("client")) {
            client.setEmail(email);
            ClientService cs = new ClientService();
            cs.modifierClient(client);

        } else if (typecompte.equals("fournisseur")) {
            fournisseur.setEmail(email);
            FournisseurService fs = new FournisseurService();
            fs.modifierFournisseur(fournisseur);

        } else if (typecompte.equals("livreur")) {
            livreur.setEmail(email);
            LivreurService ls = new LivreurService();
            ls.modifierLivreur(livreur);

        }

    }

    @FXML
    private void changerPrenom(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Modification de votre Prenom");
        dialog.setHeaderText("Entrez un nouveau Prenom");
        dialog.setContentText("Prenom : ");
        Optional<String> result = dialog.showAndWait();

        String prenom = result.get();

        if (typecompte.equals("client")) {
            client.setPrenom(prenom);
            ClientService cs = new ClientService();
            cs.modifierClient(client);

        } else if (typecompte.equals("fournisseur")) {
            fournisseur.setPrenom(prenom);
            FournisseurService fs = new FournisseurService();
            fs.modifierFournisseur(fournisseur);

        } else if (typecompte.equals("livreur")) {
            livreur.setPrenom(prenom);
            LivreurService ls = new LivreurService();
            ls.modifierLivreur(livreur);

        }

    }

    @FXML
    private void changerNom(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Modification de votre Nom");
        dialog.setHeaderText("Entrez un novueau Nom");
        dialog.setContentText("Nom : ");
        Optional<String> result = dialog.showAndWait();

        String nom = result.get();

        if (typecompte.equals("client")) {
            client.setNom(nom);
            ClientService cs = new ClientService();
            cs.modifierClient(client);

        } else if (typecompte.equals("fournisseur")) {
            fournisseur.setNom(nom);
            FournisseurService fs = new FournisseurService();
            fs.modifierFournisseur(fournisseur);

        } else if (typecompte.equals("livreur")) {
            livreur.setNom(nom);
            LivreurService ls = new LivreurService();
            ls.modifierLivreur(livreur);

        }
    }

    @FXML
    private void deleteAccount(ActionEvent event) {
        try {
            AnchorPane verifypass = FXMLLoader.load(getClass().getResource("SupprimerVerifPassword.fxml"));
            profilescene.getChildren().removeAll();
            profilescene.getChildren().setAll(verifypass);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
