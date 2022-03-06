/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import interfaces.IcommandeService;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Commande;
import service.CommandeService;
import util.MyConnexion;

/**
 * FXML Controller class
 *
 * @author EYA
 */
public class CrudController implements Initializable {

    private Button btninsert;
    @FXML
    private TableColumn<Commande, Integer> colid;
    @FXML
    private TableColumn<Commande, Integer> colnum;
    @FXML
    private TableColumn<Commande, String> colcode;
    @FXML
    private TableColumn<Commande, String> colmeth;
    @FXML
    private TableColumn<Commande, Float> colpanier;
    @FXML
    private TableColumn<Commande, Date> coldate;
    @FXML
    private TableColumn<Commande, Float> colprix;
    private TextField tfid;
    private TextField tfcarte;
    private TextField tfcode;
    private TextField tfmethode;
    private Button btnupdate;
    private Button btndelete;
    @FXML
    private TableView<Commande> tvcommande;

    /**
     * Initializes the controller class.
     */
     Connection cnx = MyConnexion.getInstance().getCnx(); // appliquer la connexion 
    @FXML
        AnchorPane cmd;
           IcommandeService comd= new CommandeService(); // Create a new commandeService
    private TextField tfuser;
    @FXML
    private TableColumn<Commande,String> coluser;
    @FXML
    private Button cart;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficher();
    }    
 
  public void afficher()
  {
     ObservableList<Commande> list= comd.afficher();
    colid.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("id_commande"));
    colnum.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("NumCarte"));
    colcode.setCellValueFactory(new PropertyValueFactory<Commande,String>("code"));
    colmeth.setCellValueFactory(new PropertyValueFactory<Commande,String>("methpaiement"));
    colpanier.setCellValueFactory(new PropertyValueFactory<Commande,Float>("totalpanier"));
    coldate.setCellValueFactory(new PropertyValueFactory<Commande,Date>("DateCommande"));
    colprix.setCellValueFactory(new PropertyValueFactory<Commande,Float>("totalprix"));
    coluser.setCellValueFactory(new PropertyValueFactory<Commande,String>("iduser"));
    tvcommande.setItems(list);
  }


    @FXML
    private void handlemouseclickk(MouseEvent event) {
        Commande c=tvcommande.getSelectionModel().getSelectedItem();
        comd.supprimer(c.getId_commande());
        afficher();
    }

    @FXML
    private void click(ActionEvent event) {
          if(event.getSource() == cart)
        {
                                                AnchorPane create;
        try {
            create = FXMLLoader.load(getClass().getResource("cartefideliteadmin.fxml"));
            cmd.getChildren().removeAll();
           cmd.getChildren().setAll(create);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
}
