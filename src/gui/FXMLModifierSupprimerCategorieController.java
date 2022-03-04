/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import interfaces.clickListener;
import interfaces.clickListenerC;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import model.Categorie;
import service.categorieService;
import util.Maconnexion;

/**
 * FXML Controller class
 *
 * @author hadir
 */
public class FXMLModifierSupprimerCategorieController implements Initializable {
    final FileChooser fc = new FileChooser();
    String path;
    
    
    @FXML
    private TextField idC;
    @FXML
    private TextField typeM;
    @FXML
    private ImageView idPhoto;
    @FXML
    private Label FxImagePath;
    private Categorie categorie;
    private clickListenerC myListener;
    @FXML
    private AnchorPane modificationCategorie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
     public void setData(Categorie categorie, clickListenerC myListener) throws FileNotFoundException {
        this.categorie = categorie;
        this.myListener = myListener;
        idC.setText(""+categorie.getIdC());
        setNewData();
    }
     private void setNewData() throws FileNotFoundException{
         Connection cnx = Maconnexion.getInstance().getCnx();
         categorie=new Categorie();
         String req ="SELECT * FROM Categorie where idC="+idC.getText()+"";
        try {
            Statement st=cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                categorie= (new Categorie(rs.getInt("idC"),rs.getString("type"),rs.getString("imageC")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        typeM.setText(categorie.getType());
        FxImagePath.setText(categorie.getImageC());
        // Image : Begin
        String path = categorie.getImageC();
        FileInputStream input = new FileInputStream(path);
        Image images = new Image(input);
        idPhoto.setImage(images);
        // Image : End
    }
 @FXML
    private void ajouterPhoto(MouseEvent event) throws FileNotFoundException {
        File file = fc.showOpenDialog(null);
        path = file.getAbsolutePath();
        FileInputStream input = new FileInputStream(path);
        Image image = new Image(input);
        idPhoto.setImage(image);
        FxImagePath.setText(path);
    }

    @FXML
    private void modifierCategorie(ActionEvent event) {
        Categorie categorie = new Categorie();
        categorie.setIdC(Integer.parseInt(idC.getText()));
        categorie.setType(typeM.getText());
        path = FxImagePath.getText();
        String sPath = path;
        sPath = sPath.replace("\\", "\\\\");
        categorie.setImageC(sPath);
        categorieService Cs = new categorieService ();
        Cs.modifierCategorie(categorie);
        
        /* Redirect to myList : BEGIN */
        AnchorPane cp;
        try {
            cp = FXMLLoader.load(getClass().getResource("CategorieFXInterface.fxml"));
            modificationCategorie.getChildren().removeAll();
            modificationCategorie.getChildren().setAll(cp);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        /* END */
    }

    @FXML
    private void supprimerCategorie(ActionEvent event) {
         categorieService ps = new categorieService();
        ps.suprimerCategorie(Integer.parseInt(idC.getText()));
           /* Redirect to myList : BEGIN */
        AnchorPane cp;
        try {
            cp = FXMLLoader.load(getClass().getResource("CategorieFXInterface.fxml"));
            modificationCategorie.getChildren().removeAll();
            modificationCategorie.getChildren().setAll(cp);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void annulerModif(ActionEvent event) {
         AnchorPane cp;
        try {
            cp = FXMLLoader.load(getClass().getResource("CategorieFXInterface.fxml"));
            modificationCategorie.getChildren().removeAll();
            modificationCategorie.getChildren().setAll(cp);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
