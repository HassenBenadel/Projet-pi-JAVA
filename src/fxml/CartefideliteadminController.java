/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import interfaces.IcartefideliteService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.CarteFidelite;
import service.CarteFideliteService;
import util.MyConnexion;

/**
 * FXML Controller class
 *
 * @author EYA
 */
public class CartefideliteadminController implements Initializable {

    @FXML
    private TableView<CarteFidelite> crttable;
    @FXML
    private TableColumn<CarteFidelite, Integer> Numcarte;
    @FXML
    private TableColumn<CarteFidelite, String> idclient;
    @FXML
    private TableColumn<CarteFidelite, Date> datecr;
    @FXML
    private TableColumn<CarteFidelite, Date> datefi;
    @FXML
    private TableColumn<CarteFidelite, Integer> nbr;

    /**
     * Initializes the controller class.
     */
        Connection cnx = MyConnexion.getInstance().getCnx(); // appliquer la connexion 
        IcartefideliteService crt= new CarteFideliteService(); 
    @FXML
    private AnchorPane gestioncarte;
    @FXML
    private Button commande;
    @FXML
    private Button regenerer;
    @FXML
    private TextField idreg;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       afficher();
    }    
      public void afficher()
  {
     ObservableList<CarteFidelite> list= crt.afficher();
    Numcarte.setCellValueFactory(new PropertyValueFactory<CarteFidelite,Integer>("NumCarte"));
    idclient.setCellValueFactory(new PropertyValueFactory<CarteFidelite,String>("IdClient"));
    datecr.setCellValueFactory(new PropertyValueFactory<CarteFidelite,Date>("Datecreation"));
    datefi.setCellValueFactory(new PropertyValueFactory<CarteFidelite,Date>("Datefinvalidite"));
    nbr.setCellValueFactory(new PropertyValueFactory<CarteFidelite,Integer>("Numpoint"));
    
    crttable.setItems(list);
  }


    @FXML
    private void doubleclick(MouseEvent event) {
          CarteFidelite c=crttable.getSelectionModel().getSelectedItem();
        crt.supprimer(c.getNumCarte());
        afficher();
    }

    @FXML
    private void clicked(ActionEvent event) {
         if(event.getSource() == commande)
        {
                                                AnchorPane create;
        try {
            create = FXMLLoader.load(getClass().getResource("crud.fxml"));
            gestioncarte.getChildren().removeAll();
            gestioncarte.getChildren().setAll(create);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }else if(event.getSource() == regenerer)
    {
      crt.regenererCarte(Integer.parseInt(idreg.getText()));  
    }
         
         
    }


    
}
