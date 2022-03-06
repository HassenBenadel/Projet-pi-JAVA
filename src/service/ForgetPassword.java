/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author hasse
 */
import java.sql.Connection;
import util.MaConnexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import model.Client;
import model.Utilisateur;

public class ForgetPassword {

    PasswordService ps = new PasswordService();

    public int generateCode() {
        int upperbound = 10001;

        Random rand = new Random();

        int code = rand.nextInt(upperbound);
        return code;
    }

    public void insertCodeInDB(String email, int code) {
        String sql = "UPDATE  utilisateur SET code=" + code + " where email='" + email + "'";

        try {
            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            int rs = st.executeUpdate(sql);
            if (rs > 0) {
                System.out.println("code ajouté");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean VerifyCode(int suggested, String email) {
        boolean bool = false;

        int written = -1;

        String sql = "UPDATE  utilisateur SET code=0 WHERE email='" + email + "'";
        String getCodeSql = "select code from utilisateur where email='" + email + "'";

        try {
            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(getCodeSql);
            if (rs.next()) {
                written = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(suggested + "..." + written);
        if (suggested == written) {

            try {
                Connection cnx = MaConnexion.getInstance().getCnx();
                Statement st = cnx.createStatement();
                int rs = st.executeUpdate(sql);
                if (rs > 0) {
                    System.out.println("code modifié");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return true;

        } else {
            System.out.println("Wrong Code");
            return false;
        }
    }

    public void modifypassword(String email, String password) {

        String sql = "Update utilisateur SET password='" + ps.passwordEncryption(password) + "'where email='" + email + "'";

        try {
            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            int rs = st.executeUpdate(sql);
            if (rs > 0) {
                System.out.println("password modifié  avec succes");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
