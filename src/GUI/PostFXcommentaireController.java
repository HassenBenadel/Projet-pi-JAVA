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
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import model.Commentaire;
import model.Post;
import service.SCommentaire;
import service.SPost;
import util.ConnectionDB;

/**
 * FXML Controller class
 *
 * @author El Ghali Omar
 */
public class PostFXcommentaireController implements Initializable {
    
    final FileChooser fc = new FileChooser();
    List<Commentaire> commentaires = new ArrayList<>();
    private Commentaire commentaire;
    private Post post;
    private clickListener myListener;
    private clickListenerC myListenerC;
    String path;
    
    @FXML
    private AnchorPane anchor;
    @FXML
    private ImageView image;
    @FXML
    private Label titre;
    @FXML
    private Label description;
    @FXML
    private TextArea contenu;
    @FXML
    private Label nomUtilisateur;
    @FXML
    private TextArea contenuCommentaire;
    @FXML
    private Button ajouterCommentaireBTN;
    @FXML
    private TextField hiddenId;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void setData(Post post) throws FileNotFoundException {
        this.post = post;
        hiddenId.setText(""+post.getId());
        setNewData();
    }
    
    public void setData(int postId) throws FileNotFoundException {
        hiddenId.setText(""+postId);
        setNewData();
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
        // Image : Begin
        String path = post.getImage();
        FileInputStream input = new FileInputStream(path);
        Image images = new Image(input);
        image.setImage(images);
        // Image : End
        
        commentaires.addAll(getData());
        if(commentaires.size() > 0) {
            myListenerC = new clickListenerC() {
                @Override
                public void onClickListener(Commentaire commentaire) {
                    Post post = new Post();
                    post.setId(Integer.parseInt(hiddenId.getText()));
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("PostFXcommentaire.fxml"));
                    try {
                        Parent root = loader.load();
                        PostFXcommentaireController otherController = loader.getController();
                        otherController.setData(post);
                        anchor.getScene().setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(BlogFXmyListController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for(int i = 0; i < commentaires.size()/2; i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/GUI/CommentaireItem.fxml"));
                AnchorPane anchorPane = fxmlloader.load();
                CommentaireItemController itemController = fxmlloader.getController();
                itemController.setData(commentaires.get(i), myListenerC, 3);
                if(column == 1) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException ex) {
            Logger.getLogger(PostFXcommentaireController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private List<Commentaire> getData(){
        Connection cnx = ConnectionDB.getInstance().getCnx();
        String req = "SELECT * FROM commentaire WHERE idPost = "+Integer.parseInt(hiddenId.getText())+"";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                commentaires.add(new Commentaire(rs.getInt("id"), rs.getInt("idPost"), rs.getInt("userId"), rs.getString("commentateur"), rs.getString("contenu")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return commentaires;
    }

    @FXML
    private void ajouterCommentaire(ActionEvent event) throws FileNotFoundException {
        Commentaire commentaire = new Commentaire();
        commentaire.setUserId(3);
        commentaire.setCommentateur("samir");
        commentaire.setIdPost(Integer.parseInt(hiddenId.getText()));
        commentaire.setContenu(contenuCommentaire.getText());
        SCommentaire sc = new SCommentaire();
        sc.ajouter(commentaire);
        
        Post post = new Post();
        post.setId(Integer.parseInt(hiddenId.getText()));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PostFXcommentaire.fxml"));
        try {
            Parent root = loader.load();
            PostFXcommentaireController otherController = loader.getController();
            otherController.setData(post);
            anchor.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(BlogFXmyListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void backToBlogList(ActionEvent event) {
        /* Redirect to myList : BEGIN */
        AnchorPane cp;
        try {
            cp = FXMLLoader.load(getClass().getResource("BlogFXlist.fxml"));
            anchor.getChildren().removeAll();
            anchor.getChildren().setAll(cp);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        /* END */
    }
    
}
