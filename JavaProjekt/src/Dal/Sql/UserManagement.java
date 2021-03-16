/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dal.Sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Fran
 */
public class UserManagement implements IUserManagement{


    @Override
    public boolean checkUsernameAndPassword(String username,String password) {
           try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://localhost:1433;databaseName=Java_Projekt;user=sa;password=SQL";
            Connection con=DriverManager.getConnection(url);
            String sql="Select * from Login_User where Username=? and Password=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,username);
            pst.setString(2, password);
            ResultSet rs=pst.executeQuery();
            if(rs.next())
            {
                con.close();
                return true;
            }
            else
            {
                con.close();
                return false;
            }
            
           
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
         return false;
    }

    @Override
    public boolean register(String username,String password) {
        try{
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://localhost:1433;databaseName=Java_Projekt;user=sa;password=SQL";
            Connection con=DriverManager.getConnection(url);
            String sql="Select * from Login_User where Username=?";
            String insert="INSERT INTO Login_User(Username,Password)"+"VALUES (?,?)";
            PreparedStatement pst=con.prepareStatement(sql);
            PreparedStatement rg=con.prepareStatement(insert);
            pst.setString(1, username);
            rg.setString(1,username);
            rg.setString(2,password);
            
         if(checkForUsername(pst,username)==true)
         {
             con.close();
             return false;
         }
         rg.executeUpdate();
         con.close();
         return true;
        }
        
        
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }
    private boolean checkForUsername(PreparedStatement pst,String checkUsername)
    {
        try
        {
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                if(rs.getString("Username") == null ? checkUsername == null : rs.getString("Username").equals(checkUsername))
                {
                    return true;
                }
                
            }
            
                   
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        
        return false;
    }

    @Override
    public boolean checkAdminLogin(String username, String password) {
         try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://localhost:1433;databaseName=Java_Projekt;user=sa;password=SQL";
            Connection con=DriverManager.getConnection(url);
            String sql="Select * from Login_Administrator where Username=? and Password=?";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,username);
            pst.setString(2, password);
            ResultSet rs=pst.executeQuery();
            if(rs.next())
            {
                con.close();
                return true;
            }
            else
            {
                con.close();
                return false;
            }
            
           
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
         return false;
    
    }

}
