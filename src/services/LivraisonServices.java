/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.I_livraison;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Livraison;
import util.MaConnexion;


/**
 *
 * @author Ibtihel
 */
public class LivraisonServices implements I_livraison {
    
     //var 
    Connection cnx = MaConnexion.getInstance().getCnx();
    

    @Override
    public void ajouterLivraison(Livraison Li) {
         //request
        String req = "  INSERT INTO livraison( Date_livraison, Prix_total, Mode_paiement, Id_livreur, Id_commande, Id_client) VALUES ( '"+Li.getDate_livraison()+"' , "+Li.getPrix_total()+" , '"+Li.getMode_paiement()+"' , "+Li.getId_livreur()+", "+Li.getId_commande()+", "+Li.getId_client()+" )";
        
        //insert
            Statement st;
        try {
            st = cnx.createStatement(); 
            st.executeUpdate(req);
            System.out.println("Livraison ajouté avec succée");
            
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
    }

    @Override
    public List<Livraison> afficherlivraison() {
       
         //var
        List <Livraison> livraisons = new ArrayList<>() ;
        
        //request 
        String req = "SELECT * FROM livraison";
       
        
        try {
            //exec
            Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(req);
             
             while (rs.next()){
                 livraisons.add(new Livraison(rs.getInt("Id_livraison"), rs.getDate("Date_livraison"), rs.getFloat("Prix_total"),rs.getString("Mode_paiement") , rs.getInt("Id_livreur"), rs.getInt("Id_commande"), rs.getInt("Id_client")));
                 
             }
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }
    
     return livraisons;
        
        
    }

    @Override
    public void modifierlivraison(Livraison Li) {
        
         String req="UPDATE livraison set Date_livraison='"+Li.getDate_livraison()+" ', Prix_total="+Li.getPrix_total()+", Mode_paiement='"+Li.getMode_paiement()+"' , Id_livreur="+Li.getId_livreur()+", Id_commande="+Li.getId_commande()+", Id_client="+Li.getId_client()+" where Id_livraison="+Li.getId_livraison()+"";
        try {
            Statement st = cnx.createStatement();
            int rowsUpdated = st.executeUpdate(req);
            if (rowsUpdated > 0) {
                System.out.println(" livraison modifié avec succée");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
        
    }

    @Override
    public void supprimerlivraison(int Id_livraison) {
        
         String req = "DELETE FROM livraison where Id_livraison="+Id_livraison+"";
        try {
            Statement st = cnx.createStatement();
            int rowsDeleted = st.executeUpdate(req);
            if (rowsDeleted > 0) {
                System.out.println("livraison supprimée avec succée!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }

    @Override
    public int getNbrLivraison() {
        
         String sql="SELECT COUNT(*) FROM livraison";
        ResultSet rs;
        int countIdLiv=0;
        try {
            PreparedStatement st= cnx.prepareStatement(sql);
			ResultSet res= st.executeQuery(); 
                        while(res.next()) {
                           countIdLiv= res.getInt(1);
                        }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return countIdLiv;
    }

    @Override
    public void TraiterLivraison(Livraison Li) {
       
        
        String sql2="UPDATE livraison SET Date_livraison=? WHERE Id_livraison=?";
        try{
            
             PreparedStatement pstmt = cnx.prepareStatement(sql2);
            
            pstmt.setDate(1,Li.getDate_livraison());
            pstmt.setInt(2,Li.getId_livraison());
            pstmt.executeUpdate();
            pstmt.close();

            
       }catch (Exception ex) {
           Logger.getLogger(LivraisonServices.class.getName()).log(Level.SEVERE, null, ex);
       }

        
    }

   

  
       
    
    
    


}
