/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import interfaces.IFavorieService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.favorie;
import util.Maconnexion;

/**
 *
 * @author hadir
 */
public class FavorieService implements IFavorieService{
    Connection cnx = Maconnexion.getInstance().getCnx();
    @Override
    public void ajouterFavorie(favorie F) {
        String prereq="SELECT * FROM favorie where idP="+F.getIdP()+" and idUser="+F.getIdUser()+"";
        String aj="INSERT INTO `favorie`(`idP`, `idUser`) VALUES ("+F.getIdP()+","+F.getIdUser()+")";
        String sup="DELETE FROM favorie where idP="+F.getIdP()+" and idUser="+F.getIdUser()+"";
        try {
            Statement st= cnx.createStatement();
            ResultSet rs = st.executeQuery(prereq);
            if (rs.next()){
                st.executeUpdate(sup);
                        System.out.println("favorie supprimer avec succes");

            }else
                st.executeUpdate(aj);
            System.out.println("favorie ajouter avec succes");
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
    }

    @Override
    public List<favorie> afficherFavorie(int user) {
        List<favorie>favories = new ArrayList<>();
      //String req= "SELECT produit.idP,(SELECT type FROM categorie WHERE produit.reference=categorie.idC) as categorie,produit.nomProduit,produit.Quantite,produit.prix FROM produit INNER JOIN favorie ON produit.idP=favorie.idP WHERE favorie.idUser="+user+"";
            String req= "SELECT * from favorie where idUser="+user;

      try {
            Statement st=cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                //favories.add(new favorie(rs.getInt("idP"), rs.getString("categorie"),rs.getString("nomProduit"),rs.getInt("Quantite"),rs.getInt("prix")));
                System.err.println(rs.toString());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return favories; 
    }    
}
