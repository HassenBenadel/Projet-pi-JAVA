/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import model.Boutique;
import org.controlsfx.control.Notifications;
import services.BoutiqueService;
import services.Create_QR;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class InterfaceAjoutBqController implements Initializable {

    @FXML
    private TextField b5;
    @FXML
    private TextField b6;
    @FXML
    private TextField b7;
    private TableView<Boutique> tableau2;
    @FXML
    private Button ok;
  
    @FXML
    private Button annuler;

    /**
     * Initializes the controller class.
     */
    private double lat=0;
    private double lng=0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 BoutiqueService bs=new BoutiqueService();

    @FXML
    private void ajouterBoutique(ActionEvent event) throws IOException {
          Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Form non valide !");
            alert.setContentText("verifie les champs !");
            
   
        String nomB=b5.getText();
        String localisationB=b6.getText();
        String description=b7.getText();
        localisationB = localisationB.replaceAll(",", " ");
        
        if(nomB.trim().isEmpty()||localisationB.trim().isEmpty()||description.trim().isEmpty()){
            alert.showAndWait();
        }else {
            Boutique b=new Boutique(nomB,localisationB,description);
        Create_QR q = new Create_QR();
        q.gene(b.getNomB(),b.getDescription());
        bs.ajouterBoutique(b);
        notification();
        FXMLLoader loader1 = new FXMLLoader();
        loader1.setLocation(getClass().getResource("./Magasin.fxml"));
        Parent parent = (Parent) loader1.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
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
    
     public void notification(){
      // Image img = new Image("tt.png");
        Notifications notificationBuilder = Notifications.create()
                .title("Succés d'ajout")
                .text("votre boutique a été ajouter avec succés")
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
      public void setCoordnate(double lat,double lng,String place){
        this.lat=lat;
        this.lng=lng;
        b6.setText(place);
    }

    @FXML
    private void map(MouseEvent event) {
         try {
                 FXMLLoader loader1 = new FXMLLoader ();
                 loader1.setLocation(getClass().getResource("./MapB.fxml"));
                
                 Parent  parent = (Parent)loader1.load();
                  Stage stage = new Stage();
                 stage.setScene(new Scene(parent));
                  stage.show();
                   
                  MapBController mc=loader1.getController();
                 mc.init(this);
                
             } catch (IOException ex) {
              System.out.println("erreur");
             }
    }
    
}
