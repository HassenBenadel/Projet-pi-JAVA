/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import interfaces.IPost;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Post;
import util.ConnectionDB;

/**
 *
 * @author El Ghali Omar
 */
public class SPost implements IPost{
    Connection cnx = ConnectionDB.getInstance().getCnx();
    
    @Override
    public void ajouter(Post p) {
        String req = "INSERT INTO post(userId , titre, image, description, contenu, nombreVues) VALUES("+p.getUserId()+",'"+p.getTitre()+"','"+p.getImage()+"','"+p.getDescription()+"','"+p.getContenu()+"',"+p.getNombreVues()+")";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Post est ajoute avec success");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Post> afficher() {
        List<Post> p = new ArrayList<>();
        String req = "SELECT * FROM post";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                p.add(new Post(rs.getInt("id"), rs.getInt("userId"), rs.getString("titre"), rs.getString("image"), rs.getString("description"), rs.getString("contenu"), rs.getInt("nombreVues")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }

    @Override
    public void supprimer(int id) {
        String req = "DELETE FROM post WHERE id = "+id+"";
        try {
            Statement st = cnx.createStatement();
            int rowsDeleted = st.executeUpdate(req);
            if (rowsDeleted > 0) {
                System.out.println("Post supprime avec succee!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void modifier(Post p) {
        String req = "UPDATE post SET userId = "+p.getUserId()+" , titre = '"+p.getTitre()+"', image = '"+p.getImage()+"', description = '"+p.getDescription()+"', contenu = '"+p.getContenu()+"', nombreVues = "+p.getNombreVues()+" WHERE id = "+p.getId()+"";
        try {
            Statement st = cnx.createStatement();
            int rowsUpdated = st.executeUpdate(req);
            if (rowsUpdated > 0) {
                System.out.println("Post modifie avec succee!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Post afficherParTitre(String titre) {
        Post p = new Post();
        String req = "SELECT * FROM post WHERE titre LIKE '%"+titre+"%'";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                p = (new Post(rs.getInt("id"), rs.getInt("userId"), rs.getString("titre"), rs.getString("image"), rs.getString("description"), rs.getString("contenu"), rs.getInt("nombreVues")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }
    
    @Override
    public Post afficherParId(int id) {
        Post post = new Post();
        String req = "SELECT * FROM post WHERE id = "+id+"";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                post = (new Post(rs.getInt("id"), rs.getInt("userId"), rs.getString("titre"), rs.getString("image"), rs.getString("description"), rs.getString("contenu"), rs.getInt("nombreVues")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       return post;
    }
}
