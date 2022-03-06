/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.collections.ObservableList;
import util.MaConnexion;

/**
 *
 * @author hasse
 */
public class UtilisateurService {

    public boolean verifyMailExistance(String email) {
        String sql = "select email from utilisateur where email='" + email + "'";
        try {

            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

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

    public boolean compareDate(String email) {
        Date datedb = getDatebyEmail(email);
        if (datedb == null) {
            return true;
        } else {
            Date date = Date.valueOf(datedb.toString());
            Date now = Date.valueOf(LocalDate.now());
            boolean test = now.after(date);
            return test;

        }

    }

    public Date getDatebyEmail(String email) {
        String sql = " Select banexpiration from utilisateur where email='" + email + "'";

        try {

            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                return (rs.getDate(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

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

    public void banUtilisateur(String email, int nbrofdays) {
        LocalDateTime bandays = LocalDateTime.now().plusDays(nbrofdays);
        String sql = "UPDATE utilisateur SET ban = 1 , banexpiration='" + bandays + "' where email='"+email+"'";
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
