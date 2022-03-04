/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import interfaces.clickListenerC;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import model.Categorie;
import model.Produit;
import util.Maconnexion;

/**
 * FXML Controller class
 *
 * @author hadir
 */
public class CategorieItemController implements Initializable {
    
    private Categorie categorie;
    private clickListenerC   myListener;
    @FXML
    private TextField idCategorie;
    @FXML
    private ImageView idPhoto;
    @FXML
    private Label type;
    private Categorie Categorie;
    @FXML
    private AnchorPane anchor;
    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(categorie);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
     public void setData(Categorie categorie,clickListenerC myListener) throws FileNotFoundException {
        this.categorie = categorie;
        this.myListener= myListener;
        idCategorie.setText(""+categorie.getIdC());
        type.setText(categorie.getType());
        // Image : Begin
        String path = categorie.getImageC();
        FileInputStream input = new FileInputStream(path);
        Image images = new Image(input);
        idPhoto.setImage(images);
        // Image : End
    }    
    
}
