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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import model.Boutique;
import org.controlsfx.control.Notifications;
import services.BoutiqueService;

/**
 * FXML Controller class
 *
 * @author seif
 */
public class ModifierBoutiqueController implements Initializable {

    @FXML
    private Button OkModif;
    @FXML
    private Label A2;
    @FXML
    private TextField M2;
    @FXML
    private Label A3;
    @FXML
    private TextField M3;
    @FXML
    private Label A31;
    @FXML
    private TextField M4;
    @FXML
    private Button Annuler;
    
    private Boutique bout;

    /**
     * Initializes the controller class.
     */
      private double lat=0;
    private double lng=0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ModifierBoutique(ActionEvent event) throws IOException {
        BoutiqueService bs= new BoutiqueService();
        Boutique boutique = new Boutique (bout.getIdB(),M2.getText() , M3.getText(), M4.getText() );
        System.out.println("------------------ "+boutique);
        bs.modifierBoutique(boutique);
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

    @FXML
    private void Annuler(ActionEvent event) throws IOException {
        
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
    
    public void setDate(Boutique bout){
        this.bout=bout;
        M2.setText(bout.getNomB());
        M3.setText(bout.getLocalisationB());
        M4.setText(bout.getDescription());
    }

    @FXML
    private void map(MouseEvent event) {
          try {
                 FXMLLoader loader1 = new FXMLLoader ();
                 loader1.setLocation(getClass().getResource("./mapModifierBoutique.fxml"));
                
                 Parent  parent = (Parent)loader1.load();
                  Stage stage = new Stage();
                 stage.setScene(new Scene(parent));
                  stage.show();
                   
                  mapModifierBoutiqueController mc=loader1.getController();
                 mc.init(this);
                
             } catch (IOException ex) {
              System.out.println("erreur");
             }
    }

    void setCoordnate(double lat, double lng, String place) {
        this.lat=lat;
        this.lng=lng;
        M3.setText(place);
    }
    
     public void notification(){
      // Image img = new Image("tt.png");
        Notifications notificationBuilder = Notifications.create()
                .title("Succés de modification")
                .text("votre boutique a été modifier avec succés")
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
