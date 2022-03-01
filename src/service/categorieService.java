/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import interfaces.ICategorieService;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import model.Categorie;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import util.Maconnexion;

/**
 *
 * @author hadir
 */
public class categorieService implements ICategorieService {
Connection cnx = Maconnexion.getInstance().getCnx();
    @Override
    public void ajouterCategorie(Categorie C) {
        String req="INSERT INTO `Categorie`(`type`,`imageC`) VALUES ('"+C.getType()+"','"+C.getImageC()+"')";
        try {
            Statement st=cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("categorie ajouter avec succes");
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
    }

    @Override
    public List<Categorie> afficherCategorie() {
        List<Categorie>Categories = new ArrayList<>();
        String req ="SELECT * FROM Categorie";
        try {
            Statement st=cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                Categories.add(new Categorie(rs.getInt("idC"), rs.getString("type"),rs.getString("image")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return Categories;
    }

    @Override
    public void suprimerCategorie(int idC) {
         String req = "DELETE FROM Categorie where idC="+idC+"";
        try {
            Statement st = cnx.createStatement();
            int rowsDeleted = st.executeUpdate(req);
            if (rowsDeleted > 0) {
                System.out.println("Categorie supprimee avec succee!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void modifierCategorie(Categorie C) {
        String req="UPDATE Categorie set type='"+C.getType()+"' where idC="+C.getIdC()+"";
        try {
            Statement st = cnx.createStatement();
            int rowsUpdated = st.executeUpdate(req);
            if (rowsUpdated > 0) {
                System.out.println(" Categorie modifie avec succee");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
