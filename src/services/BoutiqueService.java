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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Boutique;
import model.Magasin;
import util.MaConnexion;

/**
 *
 * @author DELL
 */
public class BoutiqueService {
    
        public ObservableList<Boutique> afficherBoutique() {
ObservableList<Boutique> boutiquelist=FXCollections.observableArrayList();

        String sql = "Select * from Boutique ";

        try {

            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getInt(1) + ";;" + rs.getString(2) + ";;" + rs.getString(3));
                Boutique boutique=new Boutique(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
                boutiquelist.add(boutique);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
return boutiquelist;
    }

    public void ajouterBoutique(Boutique b) {

        String sql = "insert into Boutique(nomB, localisationB,description) Values (?,?,?) ";
        try {
             Connection cnx = MaConnexion.getInstance().getCnx();
            PreparedStatement st = cnx.prepareStatement(sql);
            st.setString(1, b.getNomB());
            st.setString(2, b.getLocalisationB());
            st.setString(3, b.getDescription());
            int rs = st.executeUpdate();
            if (rs > 0) {
                System.out.println("ajout dans magasin executé avec succes");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
    public void modifierBoutique(Boutique b) {
        
        System.out.println("**************"+b+"****************");

        String sql = "UPDATE boutique  SET nomB = ? , localisationB = ?, description =? WHERE idB = ?";

        try {
            Connection cnx = MaConnexion.getInstance().getCnx();
             PreparedStatement st = cnx.prepareStatement(sql);
            st.setString(1, b.getNomB());
            st.setString(2, b.getLocalisationB());
            st.setString(3, b.getDescription());
            st.setInt(4, b.getIdB());
            int rs = st.executeUpdate();
            if (rs > 0) {
                System.out.println("modfication faite avec succes");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    
      public void supprimerBoutique(Boutique b) {

        String sql = "DELETE FROM Boutique WHERE idB= '" + b.getIdB() + "'";

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
