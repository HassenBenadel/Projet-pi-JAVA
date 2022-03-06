/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.Statement;
import model.Client;
import model.Fournisseur;
import model.Historique;
import model.Livreur;
import util.MaConnexion;

/**
 *
 * @author hasse
 */
public class HistoriqueService {

    ClientService cs = new ClientService();
    LivreurService ls = new LivreurService();
    FournisseurService fs = new FournisseurService();
    GetPersonalData gpd = new GetPersonalData();

    public void addDatacl(Client cl, Historique his) {
        String sql = "insert into historique Values (" + cs.getIduserByIdClient(cl) + "," + gpd.getIpAdress() + "," + gpd.getMacAddress() + "," + gpd.getOperatingSystem() + ")";

        try {

            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            st.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addDatafr(Fournisseur fr, Historique his) {
        String sql = "insert into historique Values (" + (fs.getIduserByIdFournisseur(fr)) + "," + gpd.getIpAdress() + "," + gpd.getMacAddress() + "," + gpd.getOperatingSystem() + ")";

        try {

            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            st.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addDatalv(Livreur lv, Historique his) {
        String sql = "insert into historique Values (" + (ls.getIduserByIdLivreur(lv)) + "," + gpd.getIpAdress() + "," + gpd.getMacAddress() + "," + gpd.getOperatingSystem() + ")";

        try {

            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            st.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteDatacl(Client cl, Historique his) {
        String sql = "Delete from historique where id_user=" + cs.getIduserByIdClient(cl) + "limit 1 ";

        try {

            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteDatafr(Fournisseur fr, Historique his) {
        String sql = "Delete from historique where id_user=" + fs.getIduserByIdFournisseur(fr) + "limit 1";

        try {

            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            st.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteDatalv(Livreur lv, Historique his) {
        String sql = "Delete from historique where id_user=" + ls.getIduserByIdLivreur(lv) + "limit 1";

        try {

            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            st.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
