/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import interfaces.clickListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Panier;
import model.Produit;
import model.lignec;
import service.PanierService;
import service.lignecService;

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
    private Label quantite;
    @FXML
    private Button augmenter;
    @FXML
    private Button diminuer;
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
       PanierService p= new PanierService();
     int idp;
     Integer quant;
     String id_panier;
    lignecService l=new lignecService();
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
        idp=produit.getIdP();
        quant=l.quantiteparid(produit.getIdP(),"11");
    quantite.setText(quant.toString());
        // TODO
    }    

    @FXML
    private void clicked(ActionEvent event) {
        if(event.getSource() == diminuer)
        {
           l.diminuerquantite(idp, "11");
        }else 
                    if(event.getSource() == augmenter)
        {
           l.augmenterquantite(idp, "11");
          
        }
          Float prix=p.calcultotal("11");
        p.update(prix,"11");
        
    }
    
}
