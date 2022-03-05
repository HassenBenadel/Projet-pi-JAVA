/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import model.Boutique;
import model.Magasin;
import org.controlsfx.control.Notifications;
import services.BoutiqueService;

/**
 * FXML Controller class
 *
 * @author seif
 */
public class ItemBoutiqueController implements Initializable {

    @FXML
    private Label labelle;
    @FXML
    private Label location;
    @FXML
    private Label description;
    
    
    private Boutique boutique;
    @FXML
    private Button EditBout;
    @FXML
    private Button SupprimerBout;
    
    
    private ModifierBoutiqueController modier;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setData(Boutique bout) {
        this.boutique = bout;
        labelle.setText(bout.getNomB());
        description.setText(bout.getDescription());  
        location.setText(bout.getLocalisationB());
    }

    @FXML
    private void Reserver(MouseEvent event) {
    }

    @FXML
    private void EditB(ActionEvent event) throws IOException {
        FXMLLoader loader1 = new FXMLLoader();
        loader1.setLocation(getClass().getResource("./ModifierBoutique.fxml"));
        
        Parent parent = (Parent) loader1.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
        modier = loader1.getController();
        modier.setDate(boutique);
        Window window = ((Node) (event.getSource())).getScene().getWindow();
        if (window instanceof Stage) {
            ((Stage) window).close();
        }
    }

    @FXML
    private void SuppB(ActionEvent event) throws IOException {
        BoutiqueService bs= new BoutiqueService();
        bs.supprimerBoutique(boutique);
        
        FXMLLoader loader1 = new FXMLLoader();
        loader1.setLocation(getClass().getResource("./Magasin.fxml"));
        Parent parent = (Parent) loader1.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
        notification();
        
        Window window = ((Node) (event.getSource())).getScene().getWindow();
        if (window instanceof Stage) {
            ((Stage) window).close();
        }
    }
    
    public void notification(){
      // Image img = new Image("tt.png");
        Notifications notificationBuilder = Notifications.create()
                .title("Succés de suppression")
                .text("votre boutique a été supprimer avec succés")
              //  .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                 System.out.println("clicked on notification");
            }
        });
        notificationBuilder.darkStyle();
        notificationBuilder.showInformation();
       
    
    }
    
}
