/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.CarteFidelite;
import model.Panier;
import util.MyConnexion;

/**
 *
 * @author EYA
 */
public class PanierService {
     Connection cnx = MyConnexion.getInstance().getCnx(); // appliquer la connexion 
  

    public  ObservableList<Panier> afficher(String Idclient) {
     ObservableList<Panier> panier=FXCollections.observableArrayList();
        System.out.println("Panier ");
        try {

            Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery("Select * from Panier WHERE idclient = '" + Idclient + "'");
            while (rs.next()) {

                
                String idclient = rs.getString(1);
                String id_panier = rs.getString(2);
                Float totalpanier = rs.getFloat(3);
         
Panier p = new Panier (idclient,id_panier,totalpanier); // Retreive database columns by Index       
    panier.add(p);
                System.out.println(" Id client :" + idclient + " id panier " + id_panier + "total " + totalpanier); // Retreive database columns by Index 
            }

        } catch (SQLException e) {
        }
return panier;
    }

   
    public void insert(String idclient,String id_panier) {
       

        try {
            String sql = "INSERT INTO Panier VALUES (?, ?, ?)";

            PreparedStatement statement = cnx.prepareStatement(sql);
            
            statement.setString(1, idclient);
       
statement.setString(2,id_panier);

          float prix=this.calcultotal(id_panier);
 
            statement.setFloat(3,prix);

            System.out.println(prix);
           

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new panier was inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void update(float prix,String id_panier) {

   
       String sql = "update panier set totalpanier=" + prix + "WHERE id_panier= '" + id_panier +"'";

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

 
    public void supprimer(String id_panier) {
        try {
            String sql = "DELETE FROM panier WHERE id_panier=?";

            PreparedStatement statement = cnx.prepareStatement(sql);

            statement.setString(1,id_panier);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("panier was deleted successfully!");
            }
        } catch (SQLException e) {
        }
    }
    public void viderpanier(String id_panier)
    { float prix=0;
     lignecService l=new lignecService();
     l.supprimer(id_panier);
       String sql = "update panier set totalpanier=" + prix + "WHERE id_panier= '" + id_panier +"'";

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
    public int gain(String id_panier)
{
 int g=0;
 float prix=this.calcultotal(id_panier);
 g=(int)(prix *20/100);
 return g;
}
    
    public float calcultotal(String idpanier)
    { lignecService ligne=new lignecService();
        float total =0,t=0;
   int n=0;
    n=ligne.nbreligne(idpanier);

        try{
            String sql="Select * FROM lignec l where l.id_panier= '" + idpanier +"'";
               Statement st = cnx.createStatement();
              ResultSet rs = st.executeQuery(sql); 
              
  while (rs.next()) {

                String id_panier = rs.getString(1);
                int codeArticle=rs.getInt(2);
                int quantite = rs.getInt(3);
             total+=ligne.prix(codeArticle,id_panier);
             
             System.out.println(total);
  
 
          }
        }catch(Exception e)
        {
            //e.printStackTrace();
        }
      return total;  
    }
    public ObservableList<String> listnoms(String id)
{ ObservableList<String> ids= FXCollections.observableArrayList();
lignecService ligne=new lignecService();
int idp;
List<Integer> idps= new ArrayList();
TreeSet<String> noms= new TreeSet();
try{
    String sql="Select l.idP FROM lignec l,produit p WHERE l.id_panier = '" + id + "' and id_panier= '" + id + "'";
         Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
              while (rs.next()) {
                  idps.add(rs.getInt(1));
               
              }
}catch(Exception e)
{
    e.printStackTrace();
}       Iterator<Integer> iter = idps.iterator();
while(iter.hasNext())
{
    idp = iter.next();
    System.out.println(idp);
noms.add(ligne.nomprod(idp,id));
            
    
                   System.out.println(ligne.nomprod(idp,id));
} 
Iterator<String> iter2 = noms.iterator();
while(iter2.hasNext())
{
   String nom1 = iter2.next();
    System.out.println(nom1);
ids.add(nom1);
            
}
    return ids;
}

       public Panier ChercherpanierbyClient(String Num)
    {
        Panier p=new Panier();
        
    try{
        String sql="Select * FROM Panier WHERE idclient = '" + Num + "'";
           Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
             while (rs.next()) {
                float  totalpanier = rs.getFloat(3);
                String id_panier = rs.getString(2);
                String idclient = rs.getString(1);


Panier pa= new Panier(idclient,id_panier,totalpanier);
return pa;
             }  
    }catch(SQLException e)
    {
        e.printStackTrace();
    }
    return p;
}
 
}

