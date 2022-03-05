/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Categorie;
import util.Maconnexion;

/**
 * FXML Controller class
 *
 * @author hadir
 */
public class StatisqtiqueController implements Initializable {

    @FXML
    private BarChart<?, ?> catChart;
    @FXML
    private AnchorPane statestique;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        /* GetData */
        ArrayList<String> typee = new ArrayList<String>();
        ArrayList<Integer> summ = new ArrayList<Integer>();
        Connection cnx = Maconnexion.getInstance().getCnx();
        String req ="SELECT categorie.type, COUNT(*) AS suum FROM categorie, favorie, produit WHERE produit.idP=favorie.idP AND produit.reference=categorie.idC GROUP BY produit.idP ORDER BY suum DESC";
        try {
            Statement st=cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                typee.add(rs.getString("type"));
                summ.add(rs.getInt("suum"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        /* END */
        
        /*CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart barChart = new BarChart(xAxis, yAxis);*/
        XYChart.Series serieData =new XYChart.Series<>();
        for(int i = 0; i < typee.size(); i++) {
            serieData.getData().add(new XYChart.Data(typee.get(i), summ.get(i)));
        }
        catChart.getData().addAll(serieData);
    } 

    @FXML
    private void echap(ActionEvent event) {
         AnchorPane cp;
            try {
                cp = FXMLLoader.load(getClass().getResource("CategorieFXInterface.fxml"));
                statestique.getChildren().removeAll();
                statestique.getChildren().setAll(cp);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }
    
    
    
}
