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
import java.util.ResourceBundle;
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

/**
 * FXML Controller class
 *
 * @author El Ghali Omar
 */
public class PostFXmodifierController implements Initializable {

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
    private TextField idPostModifier;
    
    private Post post;
    private clickListener myListener;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        titre.setText(post.getTitre());
        // Image : Begin
        String path = post.getImage();
        FileInputStream input = new FileInputStream(path);
        Image images = new Image(input);
        imageBrowsed.setImage(images);
        // Image : End
    }
    
    /*private void setChosenPost(Post p) throws FileNotFoundException{
        id.setText(""+p.getId());
        titre.setText(p.getTitre());
        desc.setText(p.getDescription());
        contenu.setText(p.getContenu());
        // Image : Begin
        String path = p.getImage();
        FileInputStream input = new FileInputStream(path);
        Image images = new Image(input);
        imageBrowsed.setImage(images);
        // Image : End
    }*/

    
}
