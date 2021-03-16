/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dal.Sql;

import Model.Movie;
import java.util.List;

/**
 *
 * @author Fran
 */
public interface IMoviesData {
    public void InsertRssParsedMoviesIntoDatabase(List<Movie> movies);
    public List<Movie> GetMoviesFromDatabase();
    public void DeleteSelectedMovieFromTheDatabase(String Title);
    public void EditSelectedMovie(String Title,String Description,String Producer,String Actors, String Duration, String Year);
    public void CreateMovie(String Title,String Description,String Producer,String Actors, String Duration, String Year); 
    public void DeleteDatabaseData();


}
