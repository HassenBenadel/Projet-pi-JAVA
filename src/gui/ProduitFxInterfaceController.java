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
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import model.Categorie;
import model.Produit;
import service.ProduitService;
import util.Maconnexion;

/**
 * FXML Controller class
 *
 * @author hadir
 */
public class ProduitFxInterfaceController implements Initializable {
    final FileChooser fc = new FileChooser();
    List<Produit> p = new ArrayList<>();
    Produit produit = new Produit();
    private clickListener myListener;
    String path;
    
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    private TextField FxQuantite;
    private TextField FxPrix;
    private ImageView FxPhoto;
    private TextField FxNomProduit;
    private TextField FxReference;

    private TextField FxId;
    private TextField FxImagePath;
    @FXML
    private Button ajouterProduit;
    @FXML
    private AnchorPane interfaceDaffichage;
    
    private List<Produit> getData(){
        Produit produit;
        Connection cnx = Maconnexion.getInstance().getCnx();
        String req ="SELECT * FROM Produit";
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        p.addAll(getData()); //teba3 laffichage w teba3 kol chy 
      //select to edit
        if(p.size() > 0) {
            myListener = new clickListener() {
                public void onClickListener(Produit produit) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLModifierSupprimerProduit.fxml"));
                    try {
                        Parent root = loader.load();
                        FXMLModifierSupprimerProduitController otherController = loader.getController();
                        otherController.setData(produit, myListener);
                        interfaceDaffichage.getScene().setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(ProduitFxInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

              
            };
        }
        //fin
        //affichage
        int column = 0;
        int row = 1;
        try {
            for(int i = 0; i < p.size()/2; i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/gui/ProduitItem.fxml"));
                AnchorPane anchorPane = fxmlloader.load();
                ProduitItemController itemController = fxmlloader.getController();
                itemController.setData(p.get(i),myListener);
                if(column == 3) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException ex) {
            Logger.getLogger(ProduitFxInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //fin
    } 
    
    private void ajouterPhoto(ActionEvent event) throws FileNotFoundException {
         File file = fc.showOpenDialog(null);
        path = file.getAbsolutePath();
        FileInputStream input = new FileInputStream(path);
        Image image = new Image(input);
        FxPhoto.setImage(image);
        FxImagePath.setText(path);
    }
    /*private void setChosenPost(Produit p) throws FileNotFoundException{
        FxId.setText(""+p.getIdP());
        FxReference.setText(""+p.getReference());
        FxNomProduit.setText(p.getNomProduit());
        FxQuantite.setText(""+p.getQuantite());
        FxPrix.setText(""+p.getPrix());
        FxImagePath.setText(p.getImage());
        // Image : Begin
        String path = p.getImage();
        FileInputStream input = new FileInputStream(path);
        Image images = new Image(input);
        FxPhoto.setImage(images);
        // Image : End
    }*/

    private void modifierProduit(ActionEvent event) {
        ProduitService ps = new ProduitService();
        path = FxImagePath.getText();
        String sPath = path;
        sPath = sPath.replace("\\", "\\\\");
        Produit produit = new Produit(Integer.parseInt(FxId.getText()), Integer.parseInt(FxQuantite.getText()), Integer.parseInt(FxPrix.getText()), sPath);
        ps.modifierProduit(produit);      
    }

    private void supprimerProduit(ActionEvent event) {
        ProduitService ps = new ProduitService();
        ps.suprimerProduit(Integer.parseInt(FxId.getText()));
    }

    @FXML
    private void ajouterProduit(ActionEvent event) {
         AnchorPane cp;
            try {
                cp = FXMLLoader.load(getClass().getResource("FXMLAjouterProduit.fxml"));
                interfaceDaffichage.getChildren().removeAll();
                interfaceDaffichage.getChildren().setAll(cp);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }

    @FXML
    private void retourAuCategories(ActionEvent event) {
         AnchorPane cp;
            try {
                cp = FXMLLoader.load(getClass().getResource("CategorieFXInterface.fxml"));
                interfaceDaffichage.getChildren().removeAll();
                interfaceDaffichage.getChildren().setAll(cp);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }
    
}
