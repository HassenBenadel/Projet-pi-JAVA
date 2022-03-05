/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Magasin;
import util.MaConnexion;

/**
 *
 * @author DELL
 */
public class MagasinService {

    public List<Magasin> afficherMagasin() {
    List<Magasin> magasinlist=new ArrayList();
        String sql = "Select * from magasin ";

        try {

            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getInt(1) + ";;" + rs.getString(2) + ";;" + rs.getString(3) + ";;" + rs.getString(4));
                Magasin magasin=new Magasin(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),rs.getString(4));
                magasinlist.add(magasin);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return magasinlist;
    }
    
    public ObservableList<Magasin> afficherMagasinOb() {
    ObservableList<Magasin> magasinlist=FXCollections.observableArrayList();
        String sql = "Select * from magasin ";

        try {

            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getInt(1) + ";;" + rs.getString(2) + ";;" + rs.getString(3) + ";;" + rs.getString(4));
                Magasin magasin=new Magasin(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
                magasinlist.add(magasin);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return magasinlist;
    }
    

    public void ajouterMagasin(Magasin m) {

        String sql = "insert into magasin (nom, localisation,description) Values (?,?,?)";
        try {
            Connection cnx = MaConnexion.getInstance().getCnx();
            PreparedStatement st = cnx.prepareStatement(sql);
            st.setString(1, m.getNom());
            st.setString(2, m.getLocalisation());
            st.setString(3, m.getDescription());
            int rs = st.executeUpdate();
            if (rs > 0) {
                System.out.println("ajout dans magasin executé avec succes");

            }
            

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
    public void modifierMagasin(Magasin m) {

        String sql = "UPDATE magasin  SET nom = ?, localisation =?, description = ? WHERE reference = ?" ;

        try {
           Connection cnx = MaConnexion.getInstance().getCnx();
            PreparedStatement st = cnx.prepareStatement(sql);
            st.setString(1, m.getNom());
            st.setString(2, m.getLocalisation());
            st.setString(3, m.getDescription());
            st.setInt(4, m.getReference());
            int rs = st.executeUpdate();
            if (rs > 0) {
                System.out.println("modfication faite avec succes");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    
      public void supprimerMagasin(Magasin m) {

        String sql = "DELETE FROM Magasin WHERE  reference=" + m.getReference();

        try {
            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            int rs1 = st.executeUpdate(sql);

            if (rs1 > 0) {
                System.out.println("supression faite avec succes");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
