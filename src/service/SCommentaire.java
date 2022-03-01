/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import interfaces.ICommentaire;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Commentaire;
import util.ConnectionDB;

/**
 *
 * @author El Ghali Omar
 */
public class SCommentaire implements ICommentaire{
    Connection cnx = ConnectionDB.getInstance().getCnx();
    
    @Override
    public void ajouter(Commentaire c) {
        String req = "INSERT INTO commentaire(userId , commentateur, contenu) VALUES("+c.getUserId()+",'"+c.getCommentateur()+"','"+c.getContenu()+"')";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Commentaire ajoutee avec success");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Commentaire> afficher() {
        List<Commentaire> c = new ArrayList<>();
        String req = "SELECT * FROM commentaire";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                c.add(new Commentaire(rs.getInt("id"), rs.getInt("userId"), rs.getString("commentateur"), rs.getString("contenu")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return c;
    }

    @Override
    public void supprimer(int id) {
        String req = "DELETE FROM commentaire WHERE id = "+id+"";
        try {
            Statement st = cnx.createStatement();
            int rowsDeleted = st.executeUpdate(req);
            if (rowsDeleted > 0) {
                System.out.println("Commentaire supprime avec succee!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void modifier(Commentaire c) {
        String req = "UPDATE commentaire SET userId = "+c.getUserId()+" , commentateur = '"+c.getCommentateur()+"', contenu = '"+c.getContenu()+"' WHERE id = "+c.getId()+"";
        try {
            Statement st = cnx.createStatement();
            int rowsUpdated = st.executeUpdate(req);
            if (rowsUpdated > 0) {
                System.out.println("Commentaire modifie avec succee!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
