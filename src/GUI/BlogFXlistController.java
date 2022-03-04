/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import interfaces.clickListener;
import java.io.File;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import model.Post;
import service.SPost;
import util.ConnectionDB;

/**
 * FXML Controller class
 *
 * @author El Ghali Omar
 */
public class BlogFXlistController implements Initializable {
    
    @FXML
    private GridPane grid;
    private TextField titre;
    private TextArea desc;
    private TextArea contenu;
    private ImageView imageBrowsed;
    private TextField id;
    @FXML
    private AnchorPane anchor;
    
    final FileChooser fc = new FileChooser();
    List<Post> posts = new ArrayList<>();
    private clickListener myListener;
    String path;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        posts.addAll(getData());
        if(posts.size() > 0) {
            myListener = new clickListener() {
                @Override
                public void onClickListener(Post post) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("PostFXcommentaire.fxml"));
                    try {
                        Parent root = loader.load();
                        PostFXcommentaireController otherController = loader.getController();
                        otherController.setData(post, myListener);
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
            for(int i = 0; i < posts.size()/2; i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/GUI/BlogItem.fxml"));
                AnchorPane anchorPane = fxmlloader.load();
                BlogItemController itemController = fxmlloader.getController();
                itemController.setData(posts.get(i), myListener);
                if(column == 1) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException ex) {
            Logger.getLogger(BlogFXlistController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void goToMyBlogs(ActionEvent event) {
        AnchorPane cp;
        try {
            cp = FXMLLoader.load(getClass().getResource("BlogFXmyList.fxml"));
            anchor.getChildren().removeAll();
            anchor.getChildren().setAll(cp);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private List<Post> getData(){
        Connection cnx = ConnectionDB.getInstance().getCnx();
        String req = "SELECT * FROM post";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                posts.add(new Post(rs.getInt("id"), rs.getInt("userId"), rs.getString("titre"), rs.getString("image"), rs.getString("description"), rs.getString("contenu"), rs.getInt("nombreVues")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return posts;
    }
}
