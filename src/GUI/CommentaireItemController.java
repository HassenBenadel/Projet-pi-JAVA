/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import interfaces.clickListener;
import interfaces.clickListenerC;
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
import model.Commentaire;
import model.Post;

/**
 * FXML Controller class
 *
 * @author El Ghali Omar
 */
public class CommentaireItemController implements Initializable {

    private Commentaire commentaire;
    private clickListenerC myListener;
    
    @FXML
    private ImageView image;
    @FXML
    private Label nomUtilisateur;
    @FXML
    private TextArea contenuCommentaire;
    @FXML
    private Button modifierCommentaireBTN;
    @FXML
    private Button supprimerCommentaireBTN;
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
    private void modifierCommentaire(ActionEvent event) {
    }

    @FXML
    private void supprimerCommentaire(ActionEvent event) {
    }
    
    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(commentaire);
    }
    
    public void setData(Commentaire commentaire, clickListenerC myListener, int connectedUserId) throws FileNotFoundException {
        this.commentaire = commentaire;
        this.myListener = myListener;
        hiddenId.setText(""+commentaire.getId());
        contenuCommentaire.setText(commentaire.getContenu());
        if(connectedUserId == commentaire.getUserId()) {
            modifierCommentaireBTN.setVisible(true);
            supprimerCommentaireBTN.setVisible(true);
        } else {
            modifierCommentaireBTN.setVisible(false);
            supprimerCommentaireBTN.setVisible(false);
        }
    }
}
