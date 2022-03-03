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
import util.MaConnexion;

/**
 *
 * @author hasse
 */
public class FournisseurService {

    PasswordService ps = new PasswordService();

    public void afficherFournisseur() {

        String sql = "Select * from utilisateur where typecompte='fournisseur'";

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

    }

    /*public int getIdFournisseur(String email) {

        String sql = "Select id_fournisseur from fournisseur where id_user=(select id_user from utilisateur where email='" + email + "')";

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
        return 0;
    }*/
    public Fournisseur selectFournisseurByEmail(String email) {

        String sql = " Select f.id_fournisseur, u.id_user , u.nom , u.prenom ,u.email , u.telephone ,u.image ,u.pays ,u.ville ,u.password , u.typecompte from utilisateur  u , fournisseur f "
                + "where email='" + email + "' and f.id_user=u.id_user";

        try {

            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getInt(1)+" "+rs.getInt(2));

                Fournisseur fr = new Fournisseur(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11));
                return fr;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    /*

    public void afficherFournisseurtById(int id) {

        String sql = " SELECT * from utilisateur WHERE id_user = (SELECT id_user  FROM fournisseur WHERE id_fournisseur =" + id + ")";

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
    public int findIdFournisseurByMail(String email) {

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

    public void ajouterFournisseur(Fournisseur fr) {

        String sql = "insert into utilisateur (nom, prenom, email, telephone, image, pays, ville, password, typecompte) Values ('"
                + fr.getNom() + "','" + fr.getPrenom() + "','" + fr.getEmail() + "'," + fr.getTelephone() + ",'" + fr.getImage() + "','" + fr.getPays() + "','"
                + fr.getVille() + "','" + ps.passwordEncryption(fr.getPassword()) + "','" + fr.getTypeCompte() + "')";

        try {
            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            st.executeUpdate(sql);
            st.executeUpdate("insert into fournisseur (id_user) Values(" + findIdFournisseurByMail(fr.getEmail()) + ")");
            System.out.println("ajout dans utilisateur executé avec succes");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void modifierFournisseur(Fournisseur fr) {
        String img = fr.getImage();
        String image = img.replace("\\", "\\\\");

        String sql = "UPDATE utilisateur SET nom = '" + fr.getNom() + "', prenom = '" + fr.getPrenom() + "', email = '" + fr.getEmail() + "', telephone = " + fr.getTelephone()
                + ", image = '" + image + "', pays = '" + fr.getPays() + "', password = '" + fr.getPassword()
                + " 'Where id_user = ( Select id_user FROM fournisseur WHERE id_fournisseur =" + fr.getId_fournisseur() + ")";

        try {
            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            int rs = st.executeUpdate(sql);
            if (rs > 0) {
                System.out.println("modfication faite avec succes");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void supprimerFournisseur(Fournisseur fr) {

        String sql = "DELETE FROM utilisateur WHERE id_user=(Select id_user from fournisseur where id_fournisseur=" + fr.getId_fournisseur() + ")";

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
