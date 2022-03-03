/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import interfaces.clickListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Post;
import service.SPost;
import util.ConnectionDB;

/**
 * FXML Controller class
 *
 * @author El Ghali Omar
 */
public class PostFXmodifierController implements Initializable {
    
    private Post post;
    private clickListener myListener;

    @FXML
    private TextField titre;
    @FXML
    private TextArea description;
    @FXML
    private TextArea contenu;
    @FXML
    private Button modifierPost;
    @FXML
    private ImageView imageBrowsed;
    @FXML
    private Label choisirImageLabel;
    @FXML
    private TextField hiddenId;
    @FXML
    private TextField hiddenPath;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void validerModification(ActionEvent event) {
    }

    @FXML
    private void browseImage(MouseEvent event) {
    }

    @FXML
    private void annulerModificationPost(ActionEvent event) {
    }
    
    @FXML
    private void supprimerPost(ActionEvent event) {
    }
    
    public void setData(Post post, clickListener myListener) throws FileNotFoundException {
        this.post = post;
        this.myListener = myListener;
        hiddenId.setText(""+post.getId());
        setNewData();
    }
    
    private void setNewData() throws FileNotFoundException {
        Post post = new Post();
        SPost sp = new SPost();
        post = sp.afficherParId(Integer.parseInt(hiddenId.getText()));
        titre.setText(post.getTitre());
        description.setText(post.getDescription());
        contenu.setText(post.getContenu());
        hiddenPath.setText(post.getImage());
        // Image : Begin
        String path = post.getImage();
        FileInputStream input = new FileInputStream(path);
        Image images = new Image(input);
        imageBrowsed.setImage(images);
        // Image : End
    }
}
