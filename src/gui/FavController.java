/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import interfaces.clickListener;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import model.Produit;
import util.Maconnexion;

/**
 * FXML Controller class
 *
 * @author hadir
 */
public class FavController implements Initializable {

    Produit produit =new Produit();
    List<Produit> produits = new ArrayList<>();
    int userId;
    final FileChooser fc = new FileChooser();
    private clickListener myListener;
    String path;
    
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane Grid;
    @FXML
    private AnchorPane anchor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setData();
    }    
    
    public void setData(){
        produits.addAll(getData());
        /*if(produits.size() > 0) {
           myListener = new clickListener() {
               @Override
               public void onClickListener(Produit produit) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Fav.fxml"));
                    try {
                        Parent root = loader.load();
                        FavController otherController = loader.getController();
                        otherController.setData();
                        anchor.getScene().setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(FavController.class.getName()).log(Level.SEVERE, null, ex);
                    }
               }
           };
        }*/
        int column = 0;
        int row = 1;
        try {
            for(int i = 0; i < produits.size()/2; i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/gui/ProduitFrondItem.fxml"));
                AnchorPane anchorPane = fxmlloader.load();
                ProduitFrondItemController itemController = fxmlloader.getController();
                itemController.setData(produits.get(i),myListener,userId);
                if(column == 2) {
                    column = 0;
                    row++;
                }
                Grid.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException ex) {
            Logger.getLogger(FavController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     private List<Produit> getData(){
        Connection cnx = Maconnexion.getInstance().getCnx();
        String req ="SELECT * FROM Produit";
        try {
            Statement st=cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                produits.add(new Produit(rs.getInt("idP"),rs.getInt("reference"), rs.getString("nomProduit"), rs.getInt("Quantite"), rs.getInt("prix"), rs.getString("image")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return produits; 
     }
    
}
