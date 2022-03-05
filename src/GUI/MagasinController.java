/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Boutique;
import model.Magasin;
import services.BoutiqueService;
import services.MagasinService;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class MagasinController implements Initializable {

    @FXML
    private GridPane gridEvent;
    @FXML
    private ScrollPane scrolEvent;

    @FXML
    private GridPane gridBout;
    @FXML
    private ScrollPane scrolBout;
    
    private int iducon;
 
    private MagasinService ms;
    private BoutiqueService bS;
    @FXML
    private Button AddMag;
    @FXML
    private Button AddBout;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
        ms = new MagasinService();
        scrolEvent.setVisible(true);
        gridEvent.getChildren().clear();
        
        bS= new BoutiqueService();
        scrolBout.setVisible(true);
        gridBout.getChildren().clear();
        
        
        int columnMesMag = 0;
        int rowMesMag = 1;
        
        int columnMesBout = 0;
        int rowMesBout = 1;

        try {

            List<Magasin> MesMag = ms.afficherMagasin();
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaa "+MesMag);
            for (int i = 0; i < MesMag.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                
                fxmlLoader.setLocation(getClass().getResource("./itemMagasin.fxml"));
                
                AnchorPane anchorPane = fxmlLoader.load();

                ItemMagasinController itemeController = fxmlLoader.getController();

                itemeController.setData(MesMag.get(i));

                if (columnMesMag == 2) {
                    columnMesMag = 0;
                    rowMesMag++;
                }

                gridEvent.add(anchorPane, columnMesMag, rowMesMag++); //(child,column,row)
                //set grid width
                gridEvent.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridEvent.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridEvent.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridEvent.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridEvent.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridEvent.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
            
            List<Boutique> MesBout = bS.afficherBoutique();
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaa "+MesBout);
            for (int i = 0; i < MesBout.size(); i++) {
                FXMLLoader fxmlLoaderBout = new FXMLLoader();
                
                fxmlLoaderBout.setLocation(getClass().getResource("./itemBoutique.fxml"));
                
                AnchorPane anchorPane = fxmlLoaderBout.load();

                ItemBoutiqueController itemeController = fxmlLoaderBout.getController();

                itemeController.setData(MesBout.get(i));

                if (columnMesBout == 2) {
                    columnMesBout = 0;
                    rowMesBout++;
                }

                gridBout.add(anchorPane, columnMesBout, rowMesBout++); //(child,column,row)
                //set grid width
                gridBout.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridBout.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridBout.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                gridBout.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridBout.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridBout.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    @FXML
    private void Reserver(MouseEvent event) {
    }

    @FXML
    private void AddMag(ActionEvent event) throws IOException {
        FXMLLoader loader1 = new FXMLLoader();
        loader1.setLocation(getClass().getResource("./interface2.fxml"));
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
    private void AddBout(ActionEvent event) throws IOException {
        FXMLLoader loader1 = new FXMLLoader();
        loader1.setLocation(getClass().getResource("./interfaceAjoutBq.fxml"));
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
