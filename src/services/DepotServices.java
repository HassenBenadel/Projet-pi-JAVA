/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.I_depot;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Depot;
import util.MaConnexion;

/**
 *
 * @author Ibtihel
 */
public class DepotServices implements I_depot {
     //var 
    Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public void ajouterDepot(Depot D) {
         //request
        String req = "  INSERT INTO depot(  Adresse, Quantite, Disponibilite, Id_livraison, Id_produit) VALUES (  '"+D.getAdresse()+"' , "+D.getQuantite()+", '"+D.getDisponibilite()+"', "+D.getId_livraison()+", "+D.getId_produit()+" )";
        
        //insert
            Statement st;
        try {
            st = cnx.createStatement(); 
            st.executeUpdate(req);
            System.out.println("Depot ajouté avec succée");
            
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        
    }

    

    @Override
    public List<Depot> afficherDepot() {
        
         //var
        List <Depot> depots = new ArrayList<>() ;
        
        //request 
        String req = "SELECT * FROM depot";
       
        
        try {
            //exec
            Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(req);
             
             while (rs.next()){
                 depots.add(new Depot(rs.getInt("Id_depot"), rs.getString("Adresse"), rs.getInt("Quantite"),rs.getString("Disponibilite") , rs.getInt("Id_livraison"), rs.getInt("Id_produit")));
                 
             }
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }
    
     return depots;   
   
        
    }

    @Override
    public void modifierDepot(Depot D) {
        
        
        String req="UPDATE depot set Adresse='"+D.getAdresse()+" ', Quantite="+D.getQuantite()+", Disponibilite='"+D.getDisponibilite()+"' , Id_livraison="+D.getId_livraison()+", Id_produit="+D.getId_produit()+" where Id_depot="+D.getId_depot()+"";
        try {
            Statement st = cnx.createStatement();
            int rowsUpdated = st.executeUpdate(req);
            if (rowsUpdated > 0) {
                System.out.println(" depot modifié avec succée");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void supprimerDepot(int Id_depot) {
        
         
        String req = "DELETE FROM depot where Id_depot="+Id_depot+"";
        try {
            Statement st = cnx.createStatement();
            int rowsDeleted = st.executeUpdate(req);
            if (rowsDeleted > 0) {
                System.out.println(" depot supprimée avec succée!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String getAdresseDepotbyId(int Id_depot) {
        
         try {
            PreparedStatement st = cnx.prepareStatement("select * from depot where Id_depot=?");
            st.setInt(1, Id_depot);
            ResultSet rs = st.executeQuery();
            rs.beforeFirst();
            if (rs.next()) {
                return rs.getString("Adresse");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepotServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    @Override
    public void AffecterLivraisonDepot(int Id_livraison,int Id_depot) {
        
        Statement st;
        try {
            st = cnx.createStatement();
            String req = "insert into depot_livrasion(Id_livraison, Id_depot) values(  " + Id_livraison + ", " + Id_depot + ")";
            st.executeUpdate(req);
            String req2 = "UPDATE `depot` SET `Disponibilite` = 'indispo' WHERE `depot`.`Id_depot` = "+Id_depot;
            st.executeUpdate(req2);
        } catch (SQLException ex) {
            Logger.getLogger(DepotServices.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        
    }
}
