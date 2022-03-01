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
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Produit;

/**
 * FXML Controller class
 *
 * @author hadir
 */
public class ProduitItemController implements Initializable {

    @FXML
    private ImageView idPhoto;
    @FXML
    private Label idNomProduit;
    @FXML
    private Label idPrix;
        @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(produit);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
}
     private Produit produit;
    private clickListener myListener;
    public void setData(Produit produit,clickListener myListener) throws FileNotFoundException {
        this.produit = produit;
        this.myListener= myListener;
        idNomProduit.setText(produit.getNomProduit());
        // Image : Begin
        String path = produit.getImage();
        FileInputStream input = new FileInputStream(path);
        Image images = new Image(input);
        idPhoto.setImage(images);
        // Image : End
        idPrix.setText(""+produit.getPrix());
    
        // TODO
    }    
    
}
