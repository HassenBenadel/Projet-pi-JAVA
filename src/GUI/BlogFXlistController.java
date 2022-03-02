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
    @FXML
    private TextField titre;
    @FXML
    private TextArea desc;
    @FXML
    private TextArea contenu;
    @FXML
    private ImageView imageBrowsed;
    @FXML
    private TextField id;
    @FXML
    private TextField imagePath;
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
        /*if(posts.size() > 0) {
            myListener = new clickListener() {
                @Override
                public void onClickListener(Post post) {
                    try {
                        setChosenPost(post);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(BlogFXlistController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
        }*/
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
    
    private List<Post> getData(){
        Post post;
        /*SPost sp = new SPost();
        List<Post> p = sp.afficher();
        return p;*/
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
    
    private void setChosenPost(Post p) throws FileNotFoundException{
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
    }
    
    /*private void supprimerPost(ActionEvent event) {
        SPost sp = new SPost();
        sp.supprimer(Integer.parseInt(id.getText()));
    }*/

    /*private void modifierPost(ActionEvent event) {
        SPost sp = new SPost();
        path = imagePath.getText();
        String sPath = path;
        System.out.println(path);
        sPath = sPath.replace("\\", "\\\\");
        Post post = new Post(Integer.parseInt(id.getText()), 3, titre.getText(), sPath, desc.getText(), contenu.getText(), 3);
        sp.modifier(post);
    }*/
    
    
}
