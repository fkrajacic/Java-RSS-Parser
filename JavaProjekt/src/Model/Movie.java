/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.format.DateTimeFormatter;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author Fran
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Movie {
    

   
    private int id;
    private String title;
    private String description;
    private String redatelj;
    private String glumci;
    private String trajanje;
    private String godina;


    public Movie() {
    }
   
    
    public Movie(String title, String description, String redatelj, String glumci,String trajanje,String godina) {
        this.title = title;
        this.description = description;
        this.redatelj = redatelj;
        this.glumci = glumci;
        this.trajanje=trajanje;
        this.godina=godina;
        
    }
    
    public Movie(int id, String title, String description, String redatelj, String glumci,String trajanje, String godina) {
        this(title, description, redatelj, glumci,trajanje, godina);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRedatelj() {
        return redatelj;
    }

    public void setRedatelj(String redatelj) {
        this.redatelj = redatelj;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGlumci() {
        return glumci;
    }

    public void setGlumci(String glumci) {
        this.glumci = glumci;
    }
    public String getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(String trajanje) {
        this.trajanje = trajanje;
    }
    public String getGodina() {
        return godina;
    }

    public void setGodina(String godina) {
        this.godina = godina;
    }

   

    @Override
    public String toString() {
        return "\n Naziv filma: " +title  + "  Redatelj: " +redatelj+ "   Opis: "+ description + "   Glumci: " + glumci + "   Trajanje: "+trajanje+"   Godina: "+ godina + "\n\n";
    }
    
}
