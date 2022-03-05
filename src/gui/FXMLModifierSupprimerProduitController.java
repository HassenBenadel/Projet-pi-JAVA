/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import interfaces.clickListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;
import model.Produit;
import service.ProduitService;
import util.Maconnexion;

/**
 * FXML Controller class
 *
 * @author hadir
 */
public class FXMLModifierSupprimerProduitController implements Initializable {
    final FileChooser fc = new FileChooser();
    String path;

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
    private ImageView FxPhoto;
    private Produit produit;
    private clickListener myListener;
    @FXML
    private TextField FxImagePath;
    @FXML
    private AnchorPane modifierProduit;
    @FXML
    private Button annulerModif;
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

    @FXML
    private void modifier(ActionEvent event) {
        int opt=JOptionPane.showConfirmDialog(null, "voulez-vous confirmer la modification ?" , "Ajout", JOptionPane.YES_NO_OPTION);
         if(opt==0) {
       Produit produit = new Produit();
       produit.setIdP(Integer.parseInt(fxId.getText()));
        produit.setQuantite(Integer.parseInt(FxQuantite.getText()));
        produit.setPrix(Integer.parseInt(FxPrix.getText()));
        path = FxImagePath.getText();
        String sPath = path;
        sPath = sPath.replace("\\", "\\\\");
        produit.setImage(sPath);
        ProduitService sp = new ProduitService();
        sp.modifierProduit(produit);
        
        /* Redirect to myList : BEGIN */
        AnchorPane cp;
        try {
            cp = FXMLLoader.load(getClass().getResource("ProduitFxInterface.fxml"));
            modifierProduit.getChildren().removeAll();
            modifierProduit.getChildren().setAll(cp);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        /* END */
         }   
    }

    @FXML
    private void ajouterPhoto(MouseEvent event) throws FileNotFoundException {
        File file = fc.showOpenDialog(null);
        path = file.getAbsolutePath();
        FileInputStream input = new FileInputStream(path);
        Image image = new Image(input);
        FxPhoto.setImage(image);
        FxImagePath.setText(path);
    }

    @FXML
    private void annulerModif(ActionEvent event) {
           /* Redirect to myList : BEGIN */
           int opt=JOptionPane.showConfirmDialog(null, "voulez-vous annuler la modification ?" , "Ajout", JOptionPane.YES_NO_OPTION);
         if(opt==0) {
        AnchorPane cp;
        try {
            cp = FXMLLoader.load(getClass().getResource("ProduitFxInterface.fxml"));
            modifierProduit.getChildren().removeAll();
            modifierProduit.getChildren().setAll(cp);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        /* END */
         }
    }

    @FXML
    private void supprimerProduit(ActionEvent event) {
        int opt=JOptionPane.showConfirmDialog(null, "voulez-vous vraiment supprimer ?" , "Ajout", JOptionPane.YES_NO_OPTION);
         if(opt==0) {
        ProduitService ps = new ProduitService();
        ps.suprimerProduit(Integer.parseInt(fxId.getText()));
           /* Redirect to myList : BEGIN */
        AnchorPane cp;
        try {
            cp = FXMLLoader.load(getClass().getResource("ProduitFxInterface.fxml"));
            modifierProduit.getChildren().removeAll();
            modifierProduit.getChildren().setAll(cp);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        /* END */

    }
    }
    
}
