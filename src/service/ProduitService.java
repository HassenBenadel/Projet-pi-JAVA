/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import interfaces.IProduitService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Produit;
import util.Maconnexion;

/**
 *
 * @author hadir
 */
public class ProduitService implements IProduitService {

    Connection cnx = Maconnexion.getInstance().getCnx();
    @Override
    public void ajouterProduit(Produit p) {
        String req="INSERT INTO `produit`(`reference`, `nomProduit`, `Quantite`, `prix`,`image`) VALUES ("+p.getReference()+",'"+p.getNomProduit()+"',"+p.getQuantite()+","+p.getPrix()+",'"+p.getImage()+"')";
        try {
            Statement st= cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Produit ajouter avec succes");
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
    }
   

    @Override
    public List<Produit> afficherProduit() {
        List<Produit>produits = new ArrayList<>();
        String req ="SELECT * FROM Produit";
        try {
            Statement st=cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                produits.add(new Produit(rs.getInt("idP"),rs.getInt("reference"), rs.getString("nomProduit"), rs.getInt("Quantite"), rs.getInt("prix"), rs.getString("image")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return produits; 
    }

    @Override
    public void suprimerProduit(int idP) {
        String req = "DELETE FROM Produit where idP="+idP+"";
        try {
            Statement st = cnx.createStatement();
            int rowsDeleted = st.executeUpdate(req);
            if (rowsDeleted > 0) {
                System.out.println("Produit supprimee avec succee!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }

    @Override
    public void modifierProduit(Produit p) {
        String req="UPDATE Produit set Quantite="+p.getQuantite()+",prix="+p.getPrix()+",image='"+p.getImage()+"' where idP="+p.getIdP()+"";
        try {
            Statement st = cnx.createStatement();
            int rowsUpdated = st.executeUpdate(req);
            if (rowsUpdated > 0) {
                System.out.println("Produit modifie avec succee");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
    
    
}
