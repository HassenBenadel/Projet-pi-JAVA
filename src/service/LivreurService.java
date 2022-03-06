/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Fournisseur;
import model.Livreur;
import util.MaConnexion;

/**
 *
 * @author hasse
 */
public class LivreurService {

    PasswordService ps = new PasswordService();

 
    public Livreur selectLivreurByEmail(String email) {

        String sql = " Select l.id_livreur, u.id_user , u.nom , u.prenom ,u.email , u.telephone ,u.image ,u.pays ,u.ville ,u.password , u.typecompte , l.secteuractivite "
                + "from utilisateur as u , livreur as l"
                + " where u.email='" + email + "' and l.id_user=u.id_user";

        try {

            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Livreur lv = new Livreur(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12));
                return lv;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
     public int getIduserByIdLivreur(Livreur lv) {

        String sql = "Select id_user   from client where id_livreur = " + lv.getId_livreur();
        try {

            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0 ;

    }

    /*
    public void afficherLivreurById(int id) {

        String sql = " SELECT * from utilisateur WHERE id_user = (SELECT id_user  FROM livreur WHERE id_livreur =" + id + ")";

        try {

            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                System.out.println(rs.getInt(1) + " -- " + rs.getString(2) + " -- " + rs.getString(3) + " -- " + rs.getString(4) + " -- " + rs.getInt(5) + " -- "
                        + rs.getString(6) + " -- " + rs.getString(7) + " -- " + rs.getString(8) + " -- " + rs.getString(9) + " -- "
                        + rs.getString(10) + " -- " + rs.getInt(11));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }*/
    public int findIdLivreurByMail(String email) {

        String sql = " SELECT * from utilisateur WHERE email = '" + email + "'";

        try {

            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getInt(1));
                return (rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }

    public void ajouterLivreur(Livreur lv) {

        String sql = "insert into utilisateur (nom, prenom, email, telephone, image, pays, ville, password, typecompte) Values ('"
                + lv.getNom() + "','" + lv.getPrenom() + "','" + lv.getEmail() + "'," + lv.getTelephone() + ",'" + lv.getImage() + "','" + lv.getPays() + "','"
                + lv.getVille() + "','" + ps.passwordEncryption(lv.getPassword()) + "','" + lv.getTypeCompte() + "')";

        try {
            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            st.executeUpdate(sql);
            st.executeUpdate("insert into livreur (id_user,SecteurActivite) Values(" + findIdLivreurByMail(lv.getEmail()) + ",'" + lv.getSecteur_activite() + "')");
            System.out.println("ajout dans utilisateur executé avec succes");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void modifierLivreur(Livreur lv) {
        String img = lv.getImage();
        String image = img.replace("\\", "\\\\");

        String sql = "UPDATE utilisateur SET nom = '" + lv.getNom() + "', prenom = '" + lv.getPrenom() + "', email = '" + lv.getEmail() + "', telephone = " + lv.getTelephone()
                + ", image = '" + image + "', pays = '" + lv.getPays()+"', ville= '" + lv.getVille() + "', password = '" + lv.getPassword()
                + " 'Where id_user = ( Select id_user FROM livreur WHERE id_livreur =" + lv.getId_livreur() + ")";

        String sql_livreur = "UPDATE livreur SET SecteurActivite = '" + lv.getSecteur_activite() + "'";
        try {
            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            int rs = st.executeUpdate(sql);
            int rs2 = st.executeUpdate(sql_livreur);
            if (rs2 > 0 && rs > 0) {
                System.out.println("modfication faite avec succes");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void supprimerLivreur(Livreur lv) {

        String sql = "DELETE FROM utilisateur WHERE id_user=(Select id_user from livreur where id_livreur=" + lv.getId_livreur() + ")";

        try {
            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            int rs1 = st.executeUpdate(sql);
            //  int rs2 = st.executeUpdate(sqlL);

            if (rs1 > 0) {
                System.out.println("supression faite avec succes");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
