/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import model.Produit;
import service.ProduitService;
import util.Maconnexion;

/**
 * FXML Controller class
 *
 * @author hadir
 */
public class FXMLAjouterProduitController implements Initializable {
    final FileChooser fc = new FileChooser();
    @FXML
    private ComboBox FxReference;
    @FXML
    private TextField FxPrix;
    @FXML
    private TextField FxQuantite;
    @FXML
    private TextField FxNom;
    @FXML
    private Button FxAjouter;
    @FXML
    private Button FxAddPhoto;
    @FXML
    private ImageView FxPhoto;
    @FXML
    private AnchorPane interfaceDajout;
    @FXML
    private Button annulerAjout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /* get categorie */
        List<String> catego = new ArrayList<>();
        Connection cnx = Maconnexion.getInstance().getCnx();
        String req ="SELECT type FROM Categorie";
        try {
            Statement st=cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                catego.add(rs.getString("type"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        ObservableList<String> categ = FXCollections.observableArrayList(catego);
        FxReference.setItems(categ);
        /* END */

    }    
    String path;
    @FXML
    private void ajouterPhoto(ActionEvent event) throws FileNotFoundException {
        File file = fc.showOpenDialog(null);
        path = file.getAbsolutePath();
        FileInputStream input = new FileInputStream(path);
        Image image = new Image(input);
        FxPhoto.setImage(image);
    }
    @FXML
    private void ajouterProduit(ActionEvent event) {
        //int reference = Integer.parseInt(FxReference.getText());
        /* get reference */
        String typeCategorie = FxReference.getSelectionModel().getSelectedItem().toString();
        int ref = 0;
        Connection cnx = Maconnexion.getInstance().getCnx();
        String req ="select idC from categorie where type='"+typeCategorie+"'";
        try {
            Statement st=cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                ref=rs.getInt("idC");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(ref);
        /* END */
        String nomProduit = FxNom.getText();
        int Quantite = Integer.parseInt(FxQuantite.getText());
        int prix =Integer.parseInt(FxPrix.getText());
        
        String sPath = path;
        sPath = sPath.replace("\\", "\\\\");
        Produit p = new Produit(ref,nomProduit,Quantite,prix,sPath);
        ProduitService Ps = new ProduitService();
        Ps.ajouterProduit(p);
        AnchorPane cp;
            try {
                cp = FXMLLoader.load(getClass().getResource("ProduitFxInterface.fxml"));
                interfaceDajout.getChildren().removeAll();
                interfaceDajout.getChildren().setAll(cp);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        
    }

    @FXML
    private void annulerAjout(ActionEvent event) {
         AnchorPane cp;
            try {
                cp = FXMLLoader.load(getClass().getResource("ProduitFxInterface.fxml"));
                interfaceDajout.getChildren().removeAll();
                interfaceDajout.getChildren().setAll(cp);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        
    }
    
}
