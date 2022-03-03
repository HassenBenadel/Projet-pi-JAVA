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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.collections.ObservableList;
import model.Client;
import util.MaConnexion;

/**
 *
 * @author hasse
 */
public class UtilisateurService {

    public String getTypecompteByEmail(String email) {
        String sql = " Select typecompte from utilisateur where email='" + email + "'";
        try {

            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                return (rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public String getNomByEmail(String email) {
        String sql = " Select nom from utilisateur where email='" + email + "'";
        try {

            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                return (rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public String getPreomByEmail(String email) {
        String sql = " Select prenom from utilisateur where email='" + email + "'";
        try {

            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                return (rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public String getPaysByEmail(String email) {
        String sql = " Select pays from utilisateur where email='" + email + "'";
        try {

            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                return (rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public String getVilleByEmail(String email) {
        String sql = " Select ville from utilisateur where email='" + email + "'";
        try {

            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                return (rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public int getTelByEmail(String email) {
        String sql = " Select telephone from utilisateur where email='" + email + "'";
        try {

            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                return (rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }

    public ObservableList<String> getUsersEmail(ObservableList<String> items) {

        String sql = "select email from utilisateur";

        try {

            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String email = rs.getString(1);
                items.add(email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public ObservableList<String> getAdminsEmail(ObservableList<String> items) {

        String sql = "select email from utilisateur where admin = 1";

        try {

            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String email = rs.getString(1);
                items.add(email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public void grantToAdmin(String email) {

        String sql = "UPDATE utilisateur SET admin = 1 WHERE email = '" + email + "'";

        try {

            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            int rs = st.executeUpdate(sql);
            if (rs > 0) {
                System.out.println("Granted to Admin");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(String email) {
        String sql = "Delete from utilisateur where email='" + email + "'";

        try {

            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            int rs = st.executeUpdate(sql);
            if (rs > 0) {
                System.out.println("User Deleted");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void banUtilisateur(String email) {
        LocalDateTime dayplusone = LocalDateTime.now().plusDays(1);
        System.out.println(dayplusone);
        String sql = "UPDATE utilisateur SET ban = 1 AND banexpiration='"+dayplusone+"'";
        try {

            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            int rs = st.executeUpdate(sql);
            if (rs > 0) {
                System.out.println("User Banned");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
