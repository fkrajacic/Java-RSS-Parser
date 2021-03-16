/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dal.Sql;

import Model.FavoriteMovie;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Fran
 */
public class FavoriteMoviesDataFactory implements IFavoriteMovies{

    @Override
    public List<FavoriteMovie> GetMoviesFromDatabase() {
         List<FavoriteMovie> movies = new ArrayList<>();
        
         try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://localhost:1433;databaseName=Java_Projekt;user=sa;password=SQL";
            Connection con=DriverManager.getConnection(url);
            String sql="Select * from Favorite_Movie";
            Statement ps=con.createStatement();
            ResultSet rs=ps.executeQuery(sql);
            while(rs.next())
            {
                FavoriteMovie movie=new FavoriteMovie();
                movie.setTitle(rs.getString("Title"));
                movies.add(movie);
            } 
            con.close();
        }
         catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e);
            }    
      return movies;
    }

    @Override
    public void DeleteSelectedMovieFromTheDatabase(String Title){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://localhost:1433;databaseName=Java_Projekt;user=sa;password=SQL";
            Connection con=DriverManager.getConnection(url);
            String sql="DELETE FROM Favorite_Movie where Title=?";
            PreparedStatement ps=con.prepareCall(sql);
            ps.setString(1, Title);
            ps.executeUpdate();
            con.close();
        }
         catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e);
            }    
      
    }

    @Override
    public void SaveFavoriteMovies(List<FavoriteMovie> favoriteMovies) {
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://localhost:1433;databaseName=Java_Projekt;user=sa;password=SQL";
            Connection con=DriverManager.getConnection(url);
            String sql="INSERT INTO Favorite_Movie(Title) VALUES(?)";
            PreparedStatement pst=con.prepareStatement(sql);
            
            for(int i=0;i<favoriteMovies.size();i++)
                {
                    pst.setString(1, favoriteMovies.get(i).getTitle());
                    pst.executeUpdate();
                }
            con.close();
          
           
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
}
