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
public class BlogItemController implements Initializable {

    @FXML
    private Label titre;
    @FXML
    private ImageView image;
    @FXML
    private Label description;
    @FXML
    private Button viewMore;
    
    private Post post;
    private clickListener myListener;
    @FXML
    private Label titre1;
    @FXML
    private Label titre11;
    @FXML
    private Label titre12;
    @FXML
    private TextField hiddenId;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(post);
    }
    
    public void setData(Post post, clickListener myListener) throws FileNotFoundException {
        this.post = post;
        this.myListener = myListener;
        description.setText(post.getDescription());
        titre.setText(post.getTitre());
        hiddenId.setText(""+post.getId());
        // Image : Begin
        String path = post.getImage();
        FileInputStream input = new FileInputStream(path);
        Image images = new Image(input);
        image.setImage(images);
        // Image : End
    }
}
