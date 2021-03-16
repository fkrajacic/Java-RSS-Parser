/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dal.Sql;

import Model.FavoriteMovie;
import java.util.List;

/**
 *
 * @author Fran
 */
public interface IFavoriteMovies {
    public List<FavoriteMovie> GetMoviesFromDatabase();
    public void DeleteSelectedMovieFromTheDatabase(String Title);
    public void SaveFavoriteMovies(List<FavoriteMovie> favoriteMovies);
}
