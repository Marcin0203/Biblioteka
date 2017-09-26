/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteka;

import static biblioteka.Biblioteka.polacz;
import java.sql.*;


/**
 *
 * @author Marcin
 */
public class Ksiazki {
    private String autor;
    private String tytul;
    private boolean wypozyczona;
    private int maxID;
    static String baza = "Biblioteka";
       
    public Ksiazki (String autor, String tytul){
        this.autor = autor;
        this.tytul = tytul;
    }
    
    public int getId() {
        return szukajMaxID();  
    }
    
    public void setId(int id) {
        this.maxID = id;
    }
    
    public String getAutor(){
        return autor;      
    }
    
    public void setAutor(String autor){
        this.autor = autor;
    }
    
    public String getTytul(){
        return tytul;
    }
    
    public void setTytul(String tytul){
        this.tytul = tytul;
    }
    
    public boolean getWypozyczona(){
        return wypozyczona;
    }
    
    public void setWypozyczona(boolean wypozyczona){
        this.wypozyczona = wypozyczona;
    }
    
    public int szukajMaxID() {
       
        Connection polaczenie = polacz(baza);
        Statement stat;
        
        try {          
            stat = polaczenie.createStatement();
            String szukajSQL = "SELECT MAX(ID) FROM Ksiazki";
 
            stat.execute(szukajSQL);
            try (ResultSet wynik = stat.getResultSet()) {
                
                        while(wynik.next()){
                            maxID = wynik.getInt(1);
                        }
            stat.close();
            polaczenie.close();      
            }
        } catch (SQLException e) {
            System.out.println("Nie mogę wyszukać Max ID: " + e.getMessage());
        }
        
        return maxID;
    }
    
}
