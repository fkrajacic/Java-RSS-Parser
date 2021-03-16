/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dal.Sql;

import Model.Movie;
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
public class MoviesDataFactory implements IMoviesData {

    @Override
    public void InsertRssParsedMoviesIntoDatabase(List<Movie> movies) {
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://localhost:1433;databaseName=Java_Projekt;user=sa;password=SQL";
            Connection con=DriverManager.getConnection(url);
            String sql="IF NOT EXISTS(SELECT Title FROM Movie WHERE Title = ?) BEGIN INSERT INTO Movie(Title,Description,Producer,Actors,Duration,Year) VALUES(?,?,?,?,?,?) END";
            PreparedStatement pst=con.prepareStatement(sql);
            
            for(int i=0;i<movies.size();i++)
                {
                    pst.setString(1, movies.get(i).getTitle());
                    pst.setString(2, movies.get(i).getTitle());
                    pst.setString(3, movies.get(i).getDescription());
                    pst.setString(4, movies.get(i).getRedatelj());
                    pst.setString(5, movies.get(i).getGlumci());
                    pst.setString(6, movies.get(i).getTrajanje());
                    pst.setString(7, movies.get(i).getGodina());
                    pst.executeUpdate();
                }
            con.close();
          
           
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }

    @Override
    public List<Movie> GetMoviesFromDatabase() 
    {
        List<Movie> movies = new ArrayList<>();
        
         try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://localhost:1433;databaseName=Java_Projekt;user=sa;password=SQL";
            Connection con=DriverManager.getConnection(url);
            String sql="Select * from Movie";
            Statement ps=con.createStatement();
            ResultSet rs=ps.executeQuery(sql);
            while(rs.next())
            {
                Movie movie=new Movie();
                movie.setTitle(rs.getString("Title"));
                movie.setDescription(rs.getString("Description"));
                movie.setRedatelj(rs.getString("Producer"));
                movie.setGlumci(rs.getString("Actors"));
                movie.setTrajanje(rs.getString("Duration"));
                movie.setGodina(rs.getString("Year"));
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
    public void DeleteSelectedMovieFromTheDatabase(String Title) {
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://localhost:1433;databaseName=Java_Projekt;user=sa;password=SQL";
            Connection con=DriverManager.getConnection(url);
            String sql="DELETE FROM Movie where Title=?";
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
    public void EditSelectedMovie(String Title,String Description,String Producer,String Actors, String Duration, String Year)
    {
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://localhost:1433;databaseName=Java_Projekt;user=sa;password=SQL";
            Connection con=DriverManager.getConnection(url);
            String sql="UPDATE Movie set Description=?, Producer=? , Actors=? , Duration=? , Year=? where Movie.Title=?";
            PreparedStatement ps=con.prepareCall(sql);
            ps.setString(1, Description);
            ps.setString(2, Producer);
            ps.setString(3, Actors);
            ps.setString(4, Duration);
            ps.setString(5, Year);
            ps.setString(6, Title);


            ps.executeUpdate();
            con.close();
        }
         catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e);
            }    
      
    }

    @Override
    public void CreateMovie(String Title, String Description, String Producer, String Actors, String Duration, String Year) {
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://localhost:1433;databaseName=Java_Projekt;user=sa;password=SQL";
            Connection con=DriverManager.getConnection(url);
            String sql="INSERT INTO Movie(Title,Description,Producer,Actors,Duration,Year) VALUES(?,?,?,?,?,?)";
            PreparedStatement ps=con.prepareCall(sql);
            ps.setString(1, Title);
            ps.setString(2, Description);
            ps.setString(3, Producer);
            ps.setString(4, Actors);
            ps.setString(5, Duration);
            ps.setString(6, Year);
           


            ps.executeUpdate();
            con.close();
        }
         catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e);
            }    
        
    }

    @Override
    public void DeleteDatabaseData() {
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://localhost:1433;databaseName=Java_Projekt;user=sa;password=SQL";
            Connection con=DriverManager.getConnection(url);
            String sql="DELETE FROM MOVIE";
            PreparedStatement pst=con.prepareStatement(sql);
            pst.executeUpdate();
            con.close();
          
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

          
}
    

