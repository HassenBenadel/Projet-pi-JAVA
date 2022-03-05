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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import model.Magasin;
import org.controlsfx.control.Notifications;
import services.Create_QR;
import services.MagasinService;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class Interface2Controller implements Initializable {

    @FXML
    private VBox t1;
    private TextField b1;
    @FXML
    private TextField b2;
    @FXML
    private TextField b3;
    @FXML
    private TextField b4;
    @FXML
    private Button okajouterMg;

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
    MagasinService mg=new MagasinService();

    @FXML
    private void ajouterMagasin(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Form non valide !");
            alert.setContentText("verifie les champs !");
            
            
        String nom=b2.getText();
        String localisation=b3.getText();
        String desc=b4.getText();
        localisation = localisation.replaceAll(",", " ");
        System.out.println(localisation);
        
        if(nom.trim().isEmpty()||localisation.trim().isEmpty()||desc.trim().isEmpty()){
            alert.showAndWait();
        }else{
            Magasin m=new Magasin(nom,localisation,desc);
        mg.ajouterMagasin(m);
        notification();
        Create_QR q = new Create_QR();
        q.gene(m.getNom(),m.getDescription());
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
                .text("votre magasin a été ajouter avec succés")
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

    @FXML
    private void map(MouseEvent event) {
        try {
                 FXMLLoader loader1 = new FXMLLoader ();
                 loader1.setLocation(getClass().getResource("./MapE.fxml"));
                
                 Parent  parent = (Parent)loader1.load();
                  Stage stage = new Stage();
                 stage.setScene(new Scene(parent));
                  stage.show();
                   
                  MapEController mc=loader1.getController();
                 mc.init(this);
                
             } catch (IOException ex) {
              System.out.println("erreur");
             }
        
    }
    
    public void setCoordnate(double lat,double lng,String place){
        this.lat=lat;
        this.lng=lng;
        b3.setText(place);
    }
    
}
