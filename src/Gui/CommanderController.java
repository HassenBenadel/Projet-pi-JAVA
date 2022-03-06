/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import interfaces.IcartefideliteService;
import interfaces.IcommandeService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Commande;
import model.lignec;
import service.CarteFideliteService;
import service.CodeReductionService;
import service.CommandeService;
import service.PanierService;
import service.lignecService;
import util.MyConnexion;
import model.Panier;

/**
 * FXML Controller class
 *
 * @author EYA
 */
public class CommanderController implements Initializable {
    int id_client=20;
   
    @FXML
    private ImageView voirpanier;
    @FXML
    private ImageView cartecoordonnees;
    @FXML
    private TextField tfuser;
    private TextField tfcarte;
    @FXML
    private TextField tfcode;
   
    @FXML
    private Button btninsert;

    private Button btnupdate;

    /**
     * Initializes the controller class.
     */
         Connection cnx = MyConnexion.getInstance().getCnx(); // appliquer la connexion 
        IcommandeService cmd= new CommandeService(); 
           IcartefideliteService crt= new CarteFideliteService(); 
    private TextField tfid;
    @FXML
    private Hyperlink creercarte;
    @FXML
    private AnchorPane commander;
    @FXML
    private ComboBox<Integer> numcarte;
    @FXML
    private RadioButton enligne;
    @FXML
    private RadioButton cash;
    String methode;
    @FXML
    private Label total;
       @FXML
    private Label gain;
      lignecService ligne=new lignecService();
       PanierService pa= new PanierService();
       
        String idpanier=pa.ChercherpanierbyClient(id_client).getId_panier();
    @FXML
    private Button parametre;
    @FXML
    private ListView<String> listnom;
    @FXML
    private Label nbre;
    @FXML
    private Label quantit;
    @FXML
    private Label prix;
    @FXML
    private Hyperlink coderedu;
     String code="";
    @FXML
    private Label reduction;
    Float prixreduc;
        float prixx;    
    @Override
       public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println(idpanier+"idpanier"+id_client);
        prixx=pa.calcultotal(idpanier); 
        ObservableList<String> noms= pa.listnoms(idpanier);
        listnom.setItems(noms);
        nbre.setText(ligne.nbreligne(idpanier).toString());
       
       total.setText(Float.toString(prixx));
       pa.update(prixx,idpanier);
       Integer g=0;
       g=pa.gain(idpanier);
       gain.setText(g.toString());
        //afficher();
         List<Integer> list = new ArrayList<Integer>();
    list.add(crt.afficherbyid(id_client).getNumCarte());
    ToggleGroup group = new ToggleGroup();
        enligne.setToggleGroup(group);
        enligne.setSelected(true);
        cash.setToggleGroup(group);
    ObservableList obList = FXCollections.observableList(list);
         numcarte.setItems(obList);
    }    
     
  public void ajouter(){
     
           if(enligne.isSelected())
     {
         methode="en ligne";
     } else if(cash.isSelected())
     {
         methode="cash";
     }
             
        try{ if(controleDeSaisi()){
            String code="";
            code=tfcode.getText();
            int carte=0;
            carte=numcarte.getValue();
            cmd.insert(methode,code,carte,Integer.parseInt(tfuser.getText()));
             Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("Commande Validé");
alert.setHeaderText("Votre commande a été ajouté");
alert.setContentText("Votre commande est validé, passez à la livraison ! ");

alert.showAndWait();
CodeReductionService cd=new CodeReductionService();
cd.supprimer(tfcode.getText());
        }}catch(Exception e)
  {
      e.printStackTrace();
  }
 // afficher();
  }

    @FXML
    private void clickedbutton(ActionEvent event) {
           if(event.getSource() == creercarte)
        {
         
          Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Confirmation : ");
alert.setHeaderText("Avec une carte de fidélité,vous pouvez créer des codes de réduction illimitées en convertissant vos points gagnés !");
alert.setContentText("Vous etes sure que vous voulez créer votre carte de fidélité disponible pour vos achats chez nous?");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
   crt.insert(id_client);
               AnchorPane onecarte;
        try {
            onecarte = FXMLLoader.load(getClass().getResource("cartefideliteclient2.fxml"));
            commander.getChildren().removeAll();
            commander.getChildren().setAll(onecarte);
        } catch (IOException ex) {
            ex.printStackTrace();
        }  
} else {
    // ... user chose CANCEL or closed the dialog
}
        }else if(event.getSource() == voirpanier)
        {
                          AnchorPane affpanier;
        try {
            affpanier = FXMLLoader.load(getClass().getResource("panieruser.fxml"));
            commander.getChildren().removeAll();
            commander.getChildren().setAll(affpanier);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }else if(event.getSource() == cartecoordonnees)
        {
                                    AnchorPane onecarte;
        try {
            onecarte = FXMLLoader.load(getClass().getResource("cartefideliteclient2.fxml"));
            commander.getChildren().removeAll();
            commander.getChildren().setAll(onecarte);
        } catch (IOException ex) {
            ex.printStackTrace();
        }  
        }else if (event.getSource() == parametre)
        {
                                               AnchorPane para;
        try {
            para = FXMLLoader.load(getClass().getResource("interface.fxml"));
            commander.getChildren().removeAll();
            commander.getChildren().setAll(para);
        } catch (IOException ex) {
            ex.printStackTrace();
        }  
        }
    
    }


    @FXML
    private void onclick(ActionEvent event) {
       
        if(event.getSource() == btninsert)
        {
           ajouter(); 
          
        }
    }


 private boolean controleDeSaisi() {  

        if (tfcode.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
            return false;
        } else {

            if (!Pattern.matches("[0-9]*", tfcode.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez le Nom ! ");
                tfcode.requestFocus();
                tfcode.selectEnd();
                return false;
            }

           
        }
        return true;
    }
       public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

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



    @FXML
    private void codereduction(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("");
dialog.setTitle("Code de réduction");
dialog.setHeaderText("Avez-vous un code de réduction? ");
dialog.setContentText("Ajouter votre code de réduction:");

// Traditional way to get the response value.
Optional<String> result = dialog.showAndWait();
if (result.isPresent()){
    System.out.println("code: " + result.get());
}

// The Java 8 way to get the response value (with lambda expression).
result.ifPresent(code -> System.out.println("Your name: " + code));
code=result.get();
tfcode.setText(code);
CodeReductionService cd=new CodeReductionService();
int pourcentage=cd.pourcentagecode(code);
    float reduc;
    //float prc=(pourcentage / 100);
        reduc = ( pourcentage * prixx)/100 ;
            prixreduc = prixx - reduc;
            System.out.println(prixx);
           // System.out.println(prc);
            System.out.println(pourcentage);
reduction.setText(prixreduc.toString());
    }

 




    
}
