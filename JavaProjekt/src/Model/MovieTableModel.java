/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Fran
 */
public class MovieTableModel extends AbstractTableModel{
        
    private static final String[] COLUMN_NAMES = {"Title", "Description", "Producer", "Actors", "Duration", "Year"};
    private List<Movie> movies;

    public MovieTableModel(List<Movie> movies) {
        this.movies = movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return movies.size();
    }
    
    public void removeRow(int rowID) {
        movies.remove(rowID);
        fireTableRowsDeleted(rowID, rowID);
    }
    
    public String getTitleFromRowId(int rowID) {
        return movies.get(rowID).getTitle();
    }
    
     public String getDescriptionFromRowId(int rowID) {
        return movies.get(rowID).getDescription();
    }
      public String getProducerFromRowId(int rowID) {
        return movies.get(rowID).getRedatelj();
    }
      
     public String getActorsFromRowId(int rowID) {
        return movies.get(rowID).getGlumci();
    }
      public String getDurationFromRowId(int rowID) {
        return movies.get(rowID).getTrajanje();
    }
       public String getYearFromRowId(int rowID) {
        return movies.get(rowID).getGodina();
    }

    @Override
    public int getColumnCount() { // ako nešto ne radi ovo je razlog
        return Movie.class.getDeclaredFields().length - 2; // we do not need static DELIMITER field!!!
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return movies.get(rowIndex).getTitle();
            case 1:
                return movies.get(rowIndex).getDescription();
            case 2:
                return movies.get(rowIndex).getRedatelj();
            case 3:
                return movies.get(rowIndex).getGlumci();
            case 4:
                return movies.get(rowIndex).getTrajanje();
            case 5:
                return movies.get(rowIndex).getGodina();
            default:
                throw new RuntimeException("No such column");
        }
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }
    
    
    
}
