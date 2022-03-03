/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import util.MaConnexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Client;

/**
 *
 * @author hasse
 */
public class ClientService {

    PasswordService ps = new PasswordService();

    public void afficherClient() {

        String sql = "Select * from utilisateur where typecompte='client'";

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

    /*public int getIdByEmail(String email) {
        String sql = "Select id_user from utilisateur where email ='" + email + "'";
        
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

 /*  public int getIdUserbymail(String email ) {
        
        String sql = "Select id_user from utilisateur where email='" + email+"'";
        
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
    }
     */
    public Client selectClientByEmail(String email) {
        String sql = " Select c.id_client, u.id_user , u.nom , u.prenom ,u.email , u.telephone ,u.image ,u.pays ,u.ville ,u.password , u.typecompte from utilisateur  u , client c "
                + "where email='" + email + "' and c.id_user=u.id_user";

        try {

            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Client cl = new Client(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11));
                return cl;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    /*public void afficherClientById(int id) {
        
        String sql = " SELECT * from utilisateur WHERE id_user = (SELECT id_user  FROM client WHERE id_client =" + id + ")";
        
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
     */
    public int findIdClientByMail(String email) {

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

    public void ajouterClient(Client cl) {

        String sql = "insert into utilisateur (nom, prenom, email, telephone, image, pays, ville, password, typecompte) Values ('"
                + cl.getNom() + "','" + cl.getPrenom() + "','" + cl.getEmail() + "'," + cl.getTelephone() + ",'" + cl.getImage() + "','" + cl.getPays() + "','"
                + cl.getVille() + "','" + ps.passwordEncryption(cl.getPassword()) + "','" + cl.getTypeCompte() + "')";

        try {

            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            st.executeUpdate(sql);
            st.executeUpdate("insert into client (id_user) Values(" + findIdClientByMail(cl.getEmail()) + ")");
            System.out.println("ajout dans utilisateur executé avec succes");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /* public void mailmodify(Client cl) {

        String sql = "Update utilisateur set email ='" + cl.getEmail() + "'Where id_user = ( Select id_user FROM client WHERE id_client =" + cl.getId_client() + ")";
        try {
            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            int rs = st.executeUpdate(sql);
            if (rs > 0) {
                System.out.println("modfication mail avec succes");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/
    public void modifierClient(Client cl) {

        String img = cl.getImage();
        String image = img.replace("\\", "\\\\");

        String sql = "UPDATE utilisateur SET nom='" + cl.getNom() + "', prenom= '" + cl.getPrenom() + "',email='" + cl.getEmail() + "', telephone = " + cl.getTelephone()
                + ", image = '" + image + "', pays = '" + cl.getPays() + "', ville ='" + cl.getVille() + "', password = '" + cl.getPassword()
                + " 'Where id_user = ( Select id_user FROM client WHERE id_client =" + cl.getId_client() + ")";

        System.out.println(sql);

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

    public void supprimerClient(Client cl) {

        String sql = "DELETE FROM utilisateur WHERE id_user=(Select id_user from client where id_client=" + cl.getId_client() + ")";

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
