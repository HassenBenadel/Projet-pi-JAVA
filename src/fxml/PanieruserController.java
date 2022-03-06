/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import interfaces.clickListener;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import model.Produit;
import model.lignec;
import service.PanierService;
import service.lignecService;
import util.MyConnexion;

/**
 * FXML Controller class
 *
 * @author EYA
 */
public class PanieruserController implements Initializable {

    @FXML
    private ImageView voirpanier;
    @FXML
    private ImageView cartecoordonnees;
    private TableView<lignec> panieruser;
  //  private TableColumn<lignec, String> colpanier;
    private TableColumn<lignec, String> colprod;
    private TableColumn<lignec, Integer> colquantite;
    @FXML
    private Label total;
    @FXML
    private Button calculbut;
    @FXML
    private AnchorPane affpanier;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
  List<Produit> p = new ArrayList<>();
 
      final FileChooser fc = new FileChooser();
    String path;
       private clickListener myListener;
    @FXML
    private Button parametre;
    private TableColumn<lignec, Float> colprix;
    @FXML
    private Label quantit;
    @FXML
    private Label prix;
    @FXML
    private ListView<String> listnom;
    @FXML
    private Label nbre;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      nbre.setText(ligne.nbreligne("11").toString());
        Float prix=pa.calcultotal("11");
       total.setText(prix.toString());
       afficherparuser();
 
        // TODO
    }     lignecService ligne=new lignecService();
       PanierService pa= new PanierService();
       
    public void afficherparuser()
{
        ObservableList<lignec> list= ligne.afficherpanier("11");
        ObservableList<String> noms= pa.listnoms("11");
        listnom.setItems(noms);
    //colpanier.setCellValueFactory(new PropertyValueFactory<lignec,String>("id_panier"));

     /*colprod.setCellValueFactory(new PropertyValueFactory<lignec,String>("prod"));
     colquantite.setCellValueFactory(new PropertyValueFactory<lignec,Integer>("quantite"));
     colprix.setCellValueFactory(new PropertyValueFactory<lignec,Float>("prix"));
panieruser.setItems(list);*/
      
List<Integer> idprod = ligne.idprod("11");
       Iterator<Integer> iter = idprod.iterator();
while(iter.hasNext())
{
    Integer yp = iter.next();
    System.out.println(yp);
    p.addAll(getData(yp));

            

        int column = 0;
        int row = 1;
        try {
            for(int i = 0; i < p.size()/3; i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/fxml/ProduitItem.fxml"));
                AnchorPane anchorPane = fxmlloader.load();
                ProduitItemController itemController = fxmlloader.getController();
                itemController.setData(p.get(i),myListener);
                if(column == 2) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException ex) {
            Logger.getLogger(InterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
       
}
    private List<Produit> getData(int id){
        Produit produit;
        Connection cnx = MyConnexion.getInstance().getCnx();
        String req ="SELECT * FROM Produit where idP= " + id + "";
        try {
            Statement st=cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                p.add(new Produit(rs.getInt("idP"),rs.getInt("reference"), rs.getString("nomProduit"), rs.getInt("Quantite"), rs.getInt("prix"), rs.getString("image")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return p; 
       
    }
   
    private void doubleclickligne(MouseEvent event) {
            lignec l=panieruser.getSelectionModel().getSelectedItem();
        ligne.supprimer(l.getId_panier());
          Float prix=pa.calcultotal("11");
        pa.update(prix,"11");
         total.setText(prix.toString());
         afficherparuser();
         
    }

    @FXML
    private void clickcommande(ActionEvent event) {
                AnchorPane create;
        try {
            create = FXMLLoader.load(getClass().getResource("commander.fxml"));
            affpanier.getChildren().removeAll();
            affpanier.getChildren().setAll(create);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    private void clickedbutton(KeyEvent event) {
       if(event.getSource() == parametre)
       { AnchorPane param;
        try {
          param = FXMLLoader.load(getClass().getResource("interface.fxml"));
            affpanier.getChildren().removeAll();
            affpanier.getChildren().setAll(param);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
       
    }


    @FXML
    private void listclick(MouseEvent event) {
          int index = listnom.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    String nom = listnom.getSelectionModel().getSelectedItem();
    quantit.setText(ligne.quantiteparid(ligne.idbynom(nom),"11").toString());
    prix.setText(ligne.prix(ligne.idbynom(nom),"11").toString());

    }


 







}
