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
import util.MaConnexion;

/**
 *
 * @author hasse
 */
public class UtilisateurService {

    public void selectUserByEmail(String email) {
        String sql = " Select * from utilisateur where email='" + email + "'";
        try {

            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Client cl = new Client(rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
                System.out.println(cl.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
}
