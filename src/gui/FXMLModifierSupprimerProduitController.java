/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import interfaces.clickListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Produit;
import util.Maconnexion;

/**
 * FXML Controller class
 *
 * @author hadir
 */
public class FXMLModifierSupprimerProduitController implements Initializable {

    @FXML
    private TextField fxId;
    @FXML
    private TextField FxPrix;
    @FXML
    private TextField FxQuantite;
    @FXML
    private TextField FxNomProduit;
    @FXML
    private TextField FxReference;
    @FXML
    private Button FxModifier;
    @FXML
    private Button FxAddPhoto;
    @FXML
    private ImageView FxPhoto;
    private Produit produit;
    private clickListener myListener;
    @FXML
    private TextField FxImagePath;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    public void setData(Produit produit, clickListener myListener) throws FileNotFoundException {
        this.produit = produit;
        this.myListener = myListener;
        fxId.setText(""+produit.getIdP());
        setNewData();
    }
    private void setNewData() throws FileNotFoundException{
         Connection cnx = Maconnexion.getInstance().getCnx();
         produit=new Produit();
         String req ="SELECT * FROM Produit where idP="+fxId.getText()+"";
        try {
            Statement st=cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                produit= (new Produit(rs.getInt("idP"),rs.getInt("reference"), rs.getString("nomProduit"), rs.getInt("Quantite"), rs.getInt("prix"), rs.getString("image")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        FxReference.setText(""+produit.getReference());
        FxNomProduit.setText(produit.getNomProduit());
        FxQuantite.setText(""+produit.getQuantite());
        FxPrix.setText(""+produit.getPrix());
        FxImagePath.setText(produit.getImage());
        // Image : Begin
        String path = produit.getImage();
        FileInputStream input = new FileInputStream(path);
        Image images = new Image(input);
        FxPhoto.setImage(images);
        // Image : End
    }
    
}
