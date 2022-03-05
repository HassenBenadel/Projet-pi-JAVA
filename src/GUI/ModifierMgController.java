/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import static java.lang.Integer.parseInt;
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
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import model.Magasin;
import org.controlsfx.control.Notifications;
import services.MagasinService;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ModifierMgController implements Initializable {

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
    private TextField M4;
    @FXML
    private Label A31;

    Magasin mag;
    MagasinController f;
    @FXML
    private Button Annuler;

    /**
     * Initializes the controller class.
     */
    private double lat = 0;
    private double lng = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ModifierMgasin(ActionEvent event) throws IOException {
        MagasinService ms = new MagasinService();

        Magasin magasin = new Magasin(mag.getReference(), M2.getText(), M3.getText(), M4.getText());
        ms.modifierMagasin(magasin);
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

    public void setDate(Magasin mag) {
        this.mag = mag;
        M2.setText(mag.getNom());
        M3.setText(mag.getLocalisation());
        M4.setText(mag.getDescription());
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

    void setCoordnate(double lat, double lng, String lieu) {
        this.lat = lat;
        this.lng = lng;
        M3.setText(lieu);
    }

    public void notification() {
        // Image img = new Image("tt.png");
        Notifications notificationBuilder = Notifications.create()
                .title("Succés de modification")
                .text("votre magasin a été modifier avec succés")
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
