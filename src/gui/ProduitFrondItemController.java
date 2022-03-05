/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import interfaces.clickListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Produit;
import model.favorie;
import service.FavorieService;
import util.Maconnexion;


/**
 * FXML Controller class
 *
 * @author hadir
 */
public class ProduitFrondItemController implements Initializable {

    @FXML
    private TextField idProduit;
    @FXML
    private ImageView idPhoto;
    @FXML
    private Label idNomProduit;
    @FXML
    private Label idPrix;
    @FXML
    private Label idCategorie;
    @FXML
    private ImageView favorieImg;

    int idUserF;
    @FXML
    private AnchorPane itemProduitF;
    private clickListener myListener;

    @FXML
    private void click(MouseEvent event) {
        myListener.onClickListener(produit);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
     private Produit produit;
     
     public void setData(Produit produit,clickListener myListener,int idUser) throws FileNotFoundException {
        this.produit = produit;
        /* get categorie */
        String Categ = null;
        Connection cnx = Maconnexion.getInstance().getCnx();
        String req ="SELECT type FROM Categorie where idC = "+produit.getReference()+"";
        try {
            Statement st=cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                Categ = (rs.getString("type"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        /* END */
        idProduit.setText(""+produit.getIdP());
        idCategorie.setText(Categ);
        idNomProduit.setText(produit.getNomProduit());
        idPrix.setText(""+produit.getPrix());
        // Image : Begin
        String path = produit.getImage();
        FileInputStream input = new FileInputStream(path);
        Image images = new Image(input);
        idPhoto.setImage(images);
        // Image : End
        FavorieService fs = new FavorieService();
        int verif = fs.verifier(idUser,produit.getIdP());
        idUserF=idUser;
        if(verif==1)
        {
            FileInputStream inputF = new FileInputStream("C:\\Users\\hadir\\Documents\\NetBeansProjects\\JavaApplication3A3\\src\\image\\favRouge.png");
            Image imageFav = new Image(inputF);
            favorieImg.setImage(imageFav);
        }
        else
        {
            FileInputStream inputF = new FileInputStream("C:\\Users\\hadir\\Documents\\NetBeansProjects\\JavaApplication3A3\\src\\image\\favNoir.png");  
            Image imageFav = new Image(inputF);
            favorieImg.setImage(imageFav);
        }
    }    

    @FXML
    private void ajouterAuFavorie(MouseEvent event) {
        favorie F =new favorie(Integer.parseInt(idProduit.getText()),idUserF);
        FavorieService fs=new FavorieService();
        fs.ajouterFavorie(F);
        }
    
}
