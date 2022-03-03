/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import interfaces.clickListener;
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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import model.Post;
import util.ConnectionDB;

/**
 * FXML Controller class
 *
 * @author El Ghali Omar
 */
public class BlogFXmyListController implements Initializable {
    
    @FXML
    private GridPane grid;
    @FXML
    private AnchorPane anchor;
    @FXML
    private Label lilWarningLabel;
    
    final FileChooser fc = new FileChooser();
    List<Post> posts = new ArrayList<>();
    Post post = new Post();
    private clickListener myListener;
    String path;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        posts.addAll(getData(2));
        System.out.println(posts.size());
        if(posts.size() == 0) {
            lilWarningLabel.setVisible(true);
        } else {
            lilWarningLabel.setVisible(false);
        }
        if(posts.size() > 0) {
            myListener = new clickListener() {
                @Override
                public void onClickListener(Post post) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("PostFXmodifier.fxml"));
                    try {
                        Parent root = loader.load();
                        PostFXmodifierController otherController = loader.getController();
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
                fxmlloader.setLocation(getClass().getResource("/GUI/MyBlogItem.fxml"));
                AnchorPane anchorPane = fxmlloader.load();
                MyBlogItemController itemController = fxmlloader.getController();
                itemController.setData(posts.get(i), myListener);
                if(column == 3) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException ex) {
            Logger.getLogger(BlogFXmyListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    @FXML
    private void backToBlogList(ActionEvent event) {
        AnchorPane cp;
        try {
            cp = FXMLLoader.load(getClass().getResource("BlogFXlist.fxml"));
            anchor.getChildren().removeAll();
            anchor.getChildren().setAll(cp);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void goToAjouterBlogBTN(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PostFXajouter.fxml"));
        try {
            Parent root = loader.load();
            PostFXajouterController acd = loader.getController();
            anchor.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(BlogFXmyListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private List<Post> getData(int userId){
        Post post;
        Connection cnx = ConnectionDB.getInstance().getCnx();
        String req = "SELECT * FROM post WHERE userId = "+userId+"";
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
    
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(post);
    }
}
