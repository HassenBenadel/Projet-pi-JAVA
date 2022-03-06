/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.net.UnknownHostException;
import interfaces.IcartefideliteService;
import interfaces.IcommandeService;
import util.MyConnexion;
import interfaces.clickListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterAttributes;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Commande;
import model.Produit;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import service.CarteFideliteService;
import service.CommandeService;
import service.PanierService;
import service.lignecService;



/**
 * FXML Controller class
 *
 * @author EYA
 */
public class InterfaceController implements Initializable {

  List<Produit> p = new ArrayList<>();
      final FileChooser fc = new FileChooser();
    String path;
    private GridPane grid;
      private clickListener myListener;
    @FXML
   private TableView<Commande> tvcommande;
    @FXML
    private Button btnupdate;
    @FXML
    private TextField tfid;
   Connection cnx = MyConnexion.getInstance().getCnx(); // appliquer la connexion 
    IcommandeService cmd= new CommandeService(); 
           IcartefideliteService crt= new CarteFideliteService(); 
    /**
     * Initializes the controller class.
     */
      
      lignecService ligne=new lignecService();
       PanierService pa= new PanierService();
    @FXML
    private RadioButton enligne;
    @FXML
    private RadioButton cash;
      String methode;
    @FXML
    private ImageView carte;
    @FXML
    private ImageView panier;
    @FXML
    private Button gestioncarte;
    @FXML
    private AnchorPane param;
    @FXML
    private Button gestioncmd;
    private ListView<Commande> listcommande;
  @FXML
    private TableColumn<Commande, Integer> colid;
    @FXML
    private TableColumn<Commande, Integer> colnum1;
    @FXML
    private TableColumn<Commande, String> colcode1;
    @FXML
    private TableColumn<Commande, String> colmeth1;
    @FXML
    private TableColumn<Commande, Float> colpanier1;
    @FXML
    private TableColumn<Commande, Date> coldate1;
    @FXML
    private TableColumn<Commande, Float> colprix1;
    @FXML
    private TextField cod;
    @FXML
    private TextField num;
    @FXML
    private TextField meth;
    @FXML
    private TextField dat;
    @FXML
    private TextField id;
    @FXML
    private TextField prx;
    @FXML
    private TextField tot;
    @FXML
    private Button pdf;
    @FXML
    private Button btnupdate1;
    

  @Override
    public void initialize(URL url, ResourceBundle rb) {
   afficher();
   
        ToggleGroup group = new ToggleGroup();
        enligne.setToggleGroup(group);
        enligne.setSelected(true);
        cash.setToggleGroup(group);
    }

       public void modifier()
  {
      
     if(enligne.isSelected())
     {
         methode="en ligne";
     } else if(cash.isSelected())
     {
         methode="cash";
     }
          Commande c=new Commande(Integer.parseInt(id.getText()),methode);
      cmd.update(c);
     afficher();
  }  
      public void supprimer()
      {
              cmd.supprimer(Integer.parseInt(id.getText()));
        afficher();
      }
      public void afficher()
  {
    ObservableList<Commande> list= cmd.afficherbyid("20");
    colid.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("id_commande"));
    colnum1.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("NumCarte"));
    colcode1.setCellValueFactory(new PropertyValueFactory<Commande,String>("code"));
    colmeth1.setCellValueFactory(new PropertyValueFactory<Commande,String>("methpaiement"));
    colpanier1.setCellValueFactory(new PropertyValueFactory<Commande,Float>("totalpanier"));
    coldate1.setCellValueFactory(new PropertyValueFactory<Commande,Date>("DateCommande"));
    colprix1.setCellValueFactory(new PropertyValueFactory<Commande,Float>("totalprix"));
    //coluser.setCellValueFactory(new PropertyValueFactory<Commande,String>("iduser"));
    tvcommande.setItems(list);
    //  ObservableList<Commande> commandes = cmd.afficherbyid("20");
   //listcommande.setItems(commandes);
      
  }
    

    @FXML
    private void onclick(ActionEvent event) {
    
if(event.getSource() == btnupdate)
        {
            modifier();
            Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("Gestion Commande");
alert.setHeaderText(null);
alert.setContentText("Votre commande a été modifié");

alert.showAndWait();
        }else         if(event.getSource() == panier)
        {
                          AnchorPane affpanier;
        try {
            affpanier = FXMLLoader.load(getClass().getResource("panieruser.fxml"));
            param.getChildren().removeAll();
            param.getChildren().setAll(affpanier);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }else if(event.getSource() == carte)
        {
                                    AnchorPane onecarte;
        try {
            onecarte = FXMLLoader.load(getClass().getResource("cartefideliteclient2.fxml"));
            param.getChildren().removeAll();
            param.getChildren().setAll(onecarte);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        }else if(event.getSource() == gestioncarte)
        {
                                    AnchorPane cartee;
        try {
            cartee = FXMLLoader.load(getClass().getResource("cartefideliteclient2.fxml"));
            param.getChildren().removeAll();
            param.getChildren().setAll(cartee);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        }else if(event.getSource() == btnupdate1)
        {
         Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Gestion commande");
alert.setHeaderText("Suppression du commande de l'historique: ");
alert.setContentText("Etes-vous sur?");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
    supprimer();
} else {
    // ... user chose CANCEL or closed the dialog
} 
        }
    }


    @FXML
    private void toWord(ActionEvent event) throws FileNotFoundException, IOException {
         XWPFDocument document = new XWPFDocument();
       FileOutputStream out = new FileOutputStream(new File("demo.docx"));
       XWPFParagraph paragraph = document.createParagraph();
       XWPFRun run = paragraph.createRun();
        String value1 = id.getText();
           
            
            String value2 = cod.getText();
            String value3 = num.getText();
            String value4 = meth.getText();
            String value5 = dat.getText();
            String value6 = prx.getText();
            String value7 = tot.getText();
            String s1 = "";
            s1= s1.concat("     Id:"     ).concat(value1).concat("    Code:     ").concat(value2).concat("      Numero de carte:    ").
                    concat(value3).concat("      Methode :         ").concat(value4).concat("      Date de commande:        ").concat(value5).concat("     Total panier:      ").concat(value6).concat("     total prix:        ").concat(value7);
       run.setText(s1);
       document.write(out);
       out.close();
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("DOCUMENT ENREGISTRE");
                
                alert.showAndWait();
    }


    @FXML
    private void getSelected(MouseEvent event) {
        int index = tvcommande.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    id.setText(colid.getCellData(index).toString());
    cod.setText(colcode1.getCellData(index));
    num.setText(colnum1.getCellData(index).toString());
    meth.setText(colmeth1.getCellData(index));
    dat.setText(coldate1.getCellData(index).toString());
    prx.setText(colprix1.getCellData(index).toString());
    tot.setText(colpanier1.getCellData(index).toString());
    }


  private Stage primaryStage1;
    @FXML
    private void topdf(ActionEvent event) {
       /*    Printer printer = Printer.getDefaultPrinter();
    PageLayout pageLayout
        = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);
    PrinterAttributes attr = printer.getPrinterAttributes();
    PrinterJob job = PrinterJob.createPrinterJob();
    double scaleX
        = pageLayout.getPrintableWidth() ;
    double scaleY
        = pageLayout.getPrintableHeight();
    Scale scale = new Scale(scaleX, scaleY);
    this.tvcommande.getTransforms().add(scale);

    if (job != null && job.showPrintDialog(this.tvcommande.getScene().getWindow())) {
      boolean success = job.printPage(pageLayout, this.tvcommande);
      if (success) {
        job.endJob();

      }
    }
    this.tvcommande.getTransforms().remove(scale);*/
 try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\EYA\\Desktop\\hello.pdf"));
            document.open();
            float[] pointColumnWidths = {150F, 150F, 150F};
            PdfPTable table = new PdfPTable(pointColumnWidths);

            PdfPCell c1 = new PdfPCell(new Phrase("heading1"));
            table.addCell(c1);

            PdfPCell c2 = new PdfPCell(new Phrase("heading1"));
            table.addCell(c2);

            PdfPCell c3 = new PdfPCell(new Phrase("heading1"));
            table.addCell(c3);
            
String imgSrc = "C:\\Users\\EYA\\Desktop\\logo.png";
            Image img = Image.getInstance(imgSrc);
            document.add(img);
            table.addCell("1.0");
            table.addCell("2.0");
            table.addCell("3.0");
            table.addCell("4.0");
            table.addCell("5.0");
            table.addCell("6.0");
             String value1 = id.getText();
           
            
            String value2 = cod.getText();
            String value3 = num.getText();
            String value4 = meth.getText();
            String value5 = dat.getText();
            String value6 = prx.getText();
            String value7 = tot.getText();
            String s1 = "";
            s1= s1.concat("     Id:"     ).concat(value1).concat("    Code:     ").concat(value2).concat("      Numero de carte:    ").
                    concat(value3).concat("      Methode :         ").concat(value4).concat("      Date de commande:        ").concat(value5).concat("     Total panier:      ").concat(value6).concat("     total prix:        ").concat(value7);
            document.add(new Paragraph(s1));

            document.add(table);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("DOCUMENT ENREGISTRE");
                
                alert.showAndWait();
    
    }



 



}
    

