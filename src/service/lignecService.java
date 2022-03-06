/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Panier;
import model.Produit;
import model.lignec;
import util.MyConnexion;

/**
 *
 * @author EYA
 */
public class lignecService {
     Connection cnx = MyConnexion.getInstance().getCnx(); // appliquer la connexion 
  
    public ObservableList<lignec> afficher(String idpanier) {
      ObservableList<lignec> ligne=FXCollections.observableArrayList();
        System.out.println("Ligne ");
        try {

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("Select * from lignec where id_panier= '" + idpanier + "'");
            while (rs.next()) {

                
                String id_panier= rs.getString(1);
                int codeArticle = rs.getInt(2);
                int quantite = rs.getInt(3);
         
lignec l = new lignec (id_panier,codeArticle,quantite); // Retreive database columns by Index       
    ligne.add(l);
                System.out.println(" id panier " + id_panier + "code" + codeArticle + "quantite" + quantite); // Retreive database columns by Index 
            }

        } catch (SQLException e) {
        }
return ligne;
    }

   
    public void insert(String id_panier,int codeArticle,int quantite) {
       

        try {
            String sql = "INSERT INTO lignec VALUES (?, ?, ?)";

            PreparedStatement statement = cnx.prepareStatement(sql);
            
            statement.setString(1,id_panier);
       
statement.setInt(2,codeArticle);

          
 
            statement.setInt(3,quantite);


           

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new ligne  was inserted successfully!");
            }
        } catch (SQLException e) {
        }
    }

 /*   @Override
    public void update(CarteFidelite carte) {

   
       String sql = "update cartefidelite set IdClient='" + carte.getIdClient() + "',Numpoint='" + carte.getNumpoint() + "'WHERE NumCarte='" + carte.getNumCarte()+"'";

        try {
            Connection cnx = MyConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            int rs = st.executeUpdate(sql);
            if (rs > 0) {
                System.out.println("modfication faite avec succes");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/
public List<Integer> idprod(String id_panier)
{ List<Integer> ids= new ArrayList();
try{
    String sql="Select idP FROM lignec WHERE  id_panier= '" + id_panier + "'";
             Statement st = cnx.createStatement();
              ResultSet rs = st.executeQuery(sql); 
  while (rs.next()) {
     ids.add(rs.getInt(1));
                
                          }
        } catch (SQLException e) {
        }
    return ids;
}
 
    public void supprimer(String id_panier) {
        try {
            String sql = "DELETE FROM lignec WHERE  id_panier= '" + id_panier + "'";

            PreparedStatement statement = cnx.prepareStatement(sql);

         //   statement.setString(1,id_panier);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("ligne was deleted successfully!");
            }
        } catch (SQLException e) {
        }
    }
    public Integer quantiteparid(int idp,String id_panier)
    {  int quant=0;
        try{
    String sql="Select quantite FROM lignec WHERE  id_panier= '" + id_panier + "' and idP = " + idp + "";
             Statement st = cnx.createStatement();
              ResultSet rs = st.executeQuery(sql); 
  while (rs.next()) {
     quant=rs.getInt(1);
                
                          }
        } catch (SQLException e) {
        }
    return quant;
}
    public void diminuerquantite(int idp,String id_panier)
    {
        int quant=0;
        try{
    String sql="Select quantite FROM lignec WHERE  id_panier= '" + id_panier + "' and idP = " + idp + "";
             Statement st = cnx.createStatement();
              ResultSet rs = st.executeQuery(sql); 
  while (rs.next()) {
     quant=rs.getInt(1);
                
                          }
        } catch (SQLException e) {
        }  
        quant --;
        String sql = "update lignec set quantite='" + quant + "' WHERE  id_panier= '" + id_panier + "' and idP = " + idp + "";

        try {
            Connection cnx = MyConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            int rs = st.executeUpdate(sql);
            if (rs > 0) {
                System.out.println("modfication faite avec succes");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
        public void augmenterquantite(int idp,String id_panier)
    {
        int quant=0;
        try{
    String sql="Select quantite FROM lignec WHERE  id_panier= '" + id_panier + "' and idP = " + idp + "";
             Statement st = cnx.createStatement();
              ResultSet rs = st.executeQuery(sql); 
  while (rs.next()) {
     quant=rs.getInt(1);
                
                          }
        } catch (SQLException e) {
        }  
        quant ++;
        String sql = "update lignec set quantite='" + quant + "' WHERE  id_panier= '" + id_panier + "' and idP = " + idp + "";

        try {
            Connection cnx = MyConnexion.getInstance().getCnx();
            Statement st = cnx.createStatement();
            int rs = st.executeUpdate(sql);
            if (rs > 0) {
                System.out.println("modfication faite avec succes");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        public int chercherprod(int idp,String id_panier)
        { int trouve=0;
          try{  String sql="Select * FROM lignec  WHERE  id_panier= '" + id_panier + "' and idP = " + idp + "";
               Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
           
          rs.last();
            int rows = rs.getRow();
            if (rows!=1) {
                //System.out.println("modfication faite avec succes");
trouve ++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } 
        return trouve;
        }
         public int idbynom(String nom)
        {
            int id=0;
             try{  String sql="Select p.idP FROM produit p WHERE p.nomProduit= '" + nom + "'";
              Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
              while (rs.next()) {
                  id=rs.getInt(1);
                  System.out.println(id);
              }
     

        } catch (Exception e) {
            e.printStackTrace();
        }
             return id;
        }
        public String nomprod(int idp,String id_panier)
        {
            String nom="";
             try{  String sql="Select p.nomProduit FROM lignec l,produit p WHERE p.idP = " + idp + " and l.idP = " +idp + " and id_panier= '" + id_panier + "'";
              Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
              while (rs.next()) {
                  nom=rs.getString(1);
                  System.out.println(nom);
              }
     

        } catch (Exception e) {
            e.printStackTrace();
        }
             
            return nom;
        }
        public Float prix(int idp,String id_panier)
        {
          float p=0;
              try{  String sql="Select p.prix,l.quantite FROM lignec l,produit p WHERE p.idP = " + idp + " and l.idP = " +idp + " and id_panier= '" + id_panier + "'";
              Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
              while (rs.next()) {
                  float prix=rs.getFloat(1);
                  int quant=rs.getInt(2);
                  p=prix*quant;
              }
     

        } catch (Exception e) {
            e.printStackTrace();
        }
          return p;
        }
        public Integer nbreligne(String id_panier){
            int nbre=0;
         
          try{  String sql="Select * FROM lignec  WHERE  id_panier= '" + id_panier + "'";
               Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
           
         while(rs.next()){    
             int quantite=rs.getInt(3);
nbre+=quantite;//System.out.println("modfication faite avec succes");
          }
         } catch (Exception e) {
            e.printStackTrace();
        }
            return nbre;
        }
         public ObservableList<lignec> afficherpanier(String idpanier) {
      ObservableList<lignec> ligne=FXCollections.observableArrayList();
        System.out.println("Ligne ");
        try {

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("Select * from lignec where id_panier= '" + idpanier + "'");
            while (rs.next()) {

                
                String id_panier= rs.getString(1);
                int codeArticle = rs.getInt(2);
                int quantite = rs.getInt(3);
        String prod=this.nomprod(codeArticle,id_panier);
        Float prix=this.prix(codeArticle, id_panier);
lignec l = new lignec (prod,quantite,prix); // Retreive database columns by Index       
    ligne.add(l);
                System.out.println(" id panier " + id_panier + "code" + codeArticle + "quantite" + quantite); // Retreive database columns by Index 
            }

        } catch (SQLException e) {
        }
return ligne;
    }
    
    }
    
     

