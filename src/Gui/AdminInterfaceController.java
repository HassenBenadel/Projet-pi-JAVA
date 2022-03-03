/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import static Gui.ConnectController.client;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import service.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author hasse
 */
public class AdminInterfaceController implements Initializable {
    
    @FXML
    private ListView<String> listOfUsers;
    @FXML
    private ListView<String> listOfAdmins;
    
    ObservableList<String> users = FXCollections.observableArrayList();
    ObservableList<String> admins = FXCollections.observableArrayList();
    
    @FXML
    private Label country;
    @FXML
    private Label town;
    @FXML
    private Label surname;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private AnchorPane adminUI;
    @FXML
    private Label countryadmin;
    @FXML
    private Label townadmin;
    @FXML
    private Label surnameadmin;
    @FXML
    private Label nameadmin;
    @FXML
    private Label phoneadmin;
    @FXML
    private Button banBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UtilisateurService us = new UtilisateurService();
        
        users = us.getUsersEmail(users);
        admins = us.getAdminsEmail(admins);
        listOfUsers.setItems(users);
        listOfAdmins.setItems(admins);
        
        listOfUsers.setOnMouseClicked(event -> {
            String mail = listOfUsers.getSelectionModel().getSelectedItem();
            name.setText(us.getNomByEmail(mail));
            surname.setText(us.getPreomByEmail(mail));
            phone.setText(String.valueOf(us.getTelByEmail(mail)));
            country.setText(us.getPaysByEmail(mail));
            town.setText(us.getVilleByEmail(mail));
        });
        listOfAdmins.setOnMouseClicked(event -> {
            String mail = listOfAdmins.getSelectionModel().getSelectedItem();
            nameadmin.setText(us.getNomByEmail(mail));
            surnameadmin.setText(us.getPreomByEmail(mail));
            phoneadmin.setText(String.valueOf(us.getTelByEmail(mail)));
            countryadmin.setText(us.getPaysByEmail(mail));
            townadmin.setText(us.getVilleByEmail(mail));
        });
        
    }
    
    @FXML
    private void addAdmin(ActionEvent event) {
        
        UtilisateurService us = new UtilisateurService();
        us.grantToAdmin(listOfUsers.getSelectionModel().getSelectedItem());
    }
    
    @FXML
    private void deleteUser(ActionEvent event) {
        UtilisateurService us = new UtilisateurService();
        us.deleteUser(listOfUsers.getSelectionModel().getSelectedItem());
        
    }

    @FXML
    private void banUser(ActionEvent event) {
    }
    
}
