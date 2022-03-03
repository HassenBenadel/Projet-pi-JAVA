/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
    
}
