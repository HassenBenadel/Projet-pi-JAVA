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
import model.Magasin;
import org.controlsfx.control.Notifications;
import services.MagasinService;

/**
 * FXML Controller class
 *
 * @author seif
 */
public class ItemMagasinController implements Initializable {

    @FXML
    private Label labelle;
    @FXML
    private Label location;
    @FXML
    private Label description;
 private MagasinController Econtroller;
    private Magasin magasin;
    @FXML
    private Button EditMag;
    @FXML
    private Button SupprimerMag;
    
    private MagasinController mc;
    
    private ModifierMgController modier;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setData(Magasin mag) {
        this.magasin = mag;
        labelle.setText(mag.getNom());
        description.setText(mag.getDescription());  
        location.setText(mag.getLocalisation());
    }

    @FXML
    private void Reserver(MouseEvent event) {
    }

    @FXML
    private void EditM(ActionEvent event) throws IOException {        
        
        FXMLLoader loader1 = new FXMLLoader();
        loader1.setLocation(getClass().getResource("./ModifierMg.fxml"));
        
        Parent parent = (Parent) loader1.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
        modier = loader1.getController();
        modier.setDate(magasin);
        Window window = ((Node) (event.getSource())).getScene().getWindow();
        if (window instanceof Stage) {
            ((Stage) window).close();
        }
    }

    @FXML
    private void SuppM(ActionEvent event) throws IOException {
        
        MagasinService ms = new MagasinService();
        
        ms.supprimerMagasin(magasin); 
        notification();
        
        FXMLLoader loader1 = new FXMLLoader();
        loader1.setLocation(getClass().getResource("./Magasin.fxml"));
        
        Parent parent = (Parent) loader1.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
        
        Window window = ((Node) (event.getSource())).getScene().getWindow();
        if (window instanceof Stage) {
            ((Stage) window).close();
        }
    }
    
     public void notification(){
      // Image img = new Image("tt.png");
        Notifications notificationBuilder = Notifications.create()
                .title("Succés de suppression")
                .text("votre magasin a été supprimer avec succés")
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
