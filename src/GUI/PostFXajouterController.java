/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import model.Post;
import service.SPost;

/**
 * FXML Controller class
 *
 * @author El Ghali Omar
 */
public class PostFXajouterController implements Initializable {
    
    @FXML
    private TextField titre;
    @FXML
    private TextArea description;
    @FXML
    private TextArea contenu;
    @FXML
    private ImageView imageBrowsed;
    @FXML
    private Button validerPost;
    @FXML
    private Label choisirImageLabel;
    @FXML
    private AnchorPane anchor;
    
    final FileChooser fc = new FileChooser();
    String path;
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void browseImage(MouseEvent mouseEvent) throws FileNotFoundException {
        File file = fc.showOpenDialog(null);
        path = file.getAbsolutePath();
        FileInputStream input = new FileInputStream(path);
        Image image = new Image(input);
        imageBrowsed.setImage(image);
    }
    
    @FXML
    private void validerAjout(ActionEvent event) {
        String sTitre = titre.getText();
        String sDesc = description.getText();
        String sContenu = contenu.getText();
        String sPath = path;
        sPath = sPath.replace("\\", "\\\\");
        Post p = new Post(2, sTitre, sPath, sDesc, sContenu, 0);
        SPost sp = new SPost();
        sp.ajouter(p);
        
        /* Redirect to myList : BEGIN */
        AnchorPane cp;
        try {
            cp = FXMLLoader.load(getClass().getResource("BlogFXmyList.fxml"));
            anchor.getChildren().removeAll();
            anchor.getChildren().setAll(cp);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        /* END */
    }

    @FXML
    private void annulerAjoutPost(ActionEvent event) {
        AnchorPane cp;
        try {
            cp = FXMLLoader.load(getClass().getResource("BlogFXmyList.fxml"));
            anchor.getChildren().removeAll();
            anchor.getChildren().setAll(cp);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
