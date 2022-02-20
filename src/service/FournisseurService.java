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
import model.Client;
import model.Fournisseur;
import util.MaConnexion;

/**
 *
 * @author hasse
 */
public class FournisseurService {

    public void afficherFournisseur() {

        String sql = "Select * from utilisateur where typecompte='fournisseur'";

        try {

            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getInt(1) + " -- " + rs.getString(2) + " -- " + rs.getString(3) + " -- " + rs.getString(4) + " -- " + rs.getInt(5) + " -- "
                        + rs.getString(6) + " -- " + rs.getString(7) + " -- " + rs.getString(8) + " -- " + rs.getString(9) + " -- "
                        + rs.getString(10) + " -- " + rs.getString(11));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void afficherFournisseurtById(int id) {

        String sql = " SELECT * from utilisateur WHERE id_user = (SELECT id_user  FROM fournisseur WHERE id_fournisseur =" + id + ")";

        try {

            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                System.out.println(rs.getInt(1) + " -- " + rs.getString(2) + " -- " + rs.getString(3) + " -- " + rs.getString(4) + " -- " + rs.getInt(5) + " -- "
                        + rs.getString(6) + " -- " + rs.getString(7) + " -- " + rs.getString(8) + " -- " + rs.getString(9) + " -- "
                        + rs.getString(10) + " -- " + rs.getString(11));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

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

        String sql = "insert into utilisateur (nom, prenom, email, telephone, image, pays, ville, password, typecompte, adressMac) Values ('"
                + fr.getNom() + "','" + fr.getPrenom() + "','" + fr.getEmail() + "'," + fr.getTelephone() + ",'" + fr.getImage() + "','" + fr.getPays() + "','"
                + fr.getVille() + "','" + fr.getPassword() + "','" + fr.getTypeCompte() + "','" + fr.getAdressMac() + "')";

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

        String sql = "UPDATE utilisateur SET nom = '" + fr.getNom() + "', prenom = '" + fr.getPrenom() + "', email = '" + fr.getEmail() + "', telephone = " + fr.getTelephone()
                + ", image = '" + fr.getImage() + "', pays = '" + fr.getPays() + "', password = '" + fr.getPassword()
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
