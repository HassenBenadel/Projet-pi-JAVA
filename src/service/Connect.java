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
import util.MaConnexion;

/**
 *
 * @author hasse
 */
public class Connect {
    PasswordService ps = new PasswordService();

    public boolean verify(String email, String password) {
        boolean bool = false;

        String sql = "Select email , password from utilisateur where email='" + email + "' and password='" + ps.passwordEncryption(password) + "'";

        try {

            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                bool = true;
                return bool;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bool;
    }
}
