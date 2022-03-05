/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import interfaces.clickListener;
import interfaces.clickListenerC;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import model.Categorie;
import model.Produit;
import util.Maconnexion;

/**
 * FXML Controller class
 *
 * @author hadir
 */
public class ProduitParCatController implements Initializable {

    @FXML
    private AnchorPane produitParCategorie;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
      final FileChooser fc = new FileChooser();
    List<Produit> p = new ArrayList<>();
    Produit produit = new Produit();
    private clickListener myListener;
    String path;
    int userId=3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*Categorie categorie = new Categorie();
        categorie.setIdC(13);
        setData(categorie, myListenerC);*/
    }    

    void setData(Categorie categorie, clickListenerC myListenerC) {
        System.out.println(categorie);
        p.addAll(getData(categorie.getIdC()));
         int column = 0;
        int row = 1;
        try {
            for(int i = 0; i < p.size()/2; i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/gui/ProduitFrondItem.fxml"));
                AnchorPane anchorPane = fxmlloader.load();
                ProduitFrondItemController itemController = fxmlloader.getController();
                itemController.setData(p.get(i),myListener,userId);
                if(column == 2) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException ex) {
            Logger.getLogger(ProduitFxInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     private List<Produit> getData(int idC){
        Produit produit;
        Connection cnx = Maconnexion.getInstance().getCnx();
       String req ="SELECT * FROM Produit where reference="+idC+"";
        try {
            Statement st=cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                p.add(new Produit(rs.getInt("idP"),rs.getInt("reference"), rs.getString("nomProduit"), rs.getInt("Quantite"), rs.getInt("prix"), rs.getString("image")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return p; 
       
    }

    @FXML
    private void back(ActionEvent event) {
          AnchorPane cp;
            try {
                cp = FXMLLoader.load(getClass().getResource("ProduitFrond.fxml"));
                produitParCategorie.getChildren().removeAll();
                produitParCategorie.getChildren().setAll(cp);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }
}
