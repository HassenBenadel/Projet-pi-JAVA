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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
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
import service.SPost;
import util.ConnectionDB;

/**
 * FXML Controller class
 *
 * @author El Ghali Omar
 */
public class BlogFXlistController implements Initializable {
    
    final FileChooser fc = new FileChooser();
    List<Post> posts = new ArrayList<>();
    private clickListener myListener;
    ObservableList<String> sort;
    String path;
    
    @FXML
    private GridPane grid;
    private TextField titre;
    private TextArea desc;
    private TextArea contenu;
    private ImageView imageBrowsed;
    private TextField id;
    @FXML
    private AnchorPane anchor;
    @FXML
    private ComboBox sortCB;
    @FXML
    private Label sortWarning;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> sortS = new ArrayList<>();
        sortS.add("Plus Rates");
        sortS.add("Plus Commentes");
        sort = FXCollections.observableArrayList(sortS);
        sortCB.setItems(sort);
        
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
            }; // dhib w layla / soul7oufet wal arneb
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
                posts.add(new Post(rs.getInt("id"), rs.getInt("userId"), rs.getString("titre"), rs.getString("image"), rs.getString("description"), rs.getString("contenu")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return posts;
    }
    
    private List<Post> getDataRate(){
        Connection cnx = ConnectionDB.getInstance().getCnx();
        String req = "SELECT post.*, SUM(postrating.rating) AS suum FROM post,postrating WHERE post.id=postrating.postId GROUP BY post.id ORDER BY suum DESC";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                posts.add(new Post(rs.getInt("id"), rs.getInt("userId"), rs.getString("titre"), rs.getString("image"), rs.getString("description"), rs.getString("contenu")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return posts;
    }
    
    private List<Post> getDataComm(){
        Connection cnx = ConnectionDB.getInstance().getCnx();
        String req = "SELECT post.*, COUNT(*) AS suum FROM post,commentaire WHERE post.id=commentaire.idPost GROUP BY post.id ORDER BY suum DESC";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                posts.add(new Post(rs.getInt("id"), rs.getInt("userId"), rs.getString("titre"), rs.getString("image"), rs.getString("description"), rs.getString("contenu")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return posts;
    }
    
    private void affMethRate() {
        posts.clear();
        posts.addAll(getDataRate());
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
    
    private void affMethComm() {
        posts.clear();
        posts.addAll(getDataComm());
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
    private void selectSort(ActionEvent event) {
        String sort = sortCB.getSelectionModel().getSelectedItem().toString();
        System.out.println(sort);
        if(sort == "Plus Rates") {
            affMethRate();
        } else if(sort == "Plus Commentes") {
            affMethComm();
        }
    }
}
