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
public class Czytelnicy {
    
    private String  name,
                    surname,
                    pesel;
    private int maxID;
    static String baza = "Biblioteka";
    
    public Czytelnicy(String name, String surname, String pesel){
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
    }
    
    public int getId() {
        return szukajMaxID();  
    }
    
    public void setId(int id) {
        this.maxID = id;
    }
    
    public String getName() {
        return name;  
    }
    
    public void setName(String name) {
        this.name = name;  
    }
    
    public String getSurname() {
        return surname;  
    }
    
    public void setSurname(String surname) {
        this.surname = surname; 
    }
    
    public String getPesel() {
        return pesel;  
    }
    
    public void getSurname(String pesel) {
        this.pesel = pesel; 
    }
    
    public int szukajMaxID() {
       
        Connection polaczenie = polacz(baza);
        Statement stat = null;
        
        try {          
            stat = polaczenie.createStatement();
            String szukajSQL = "SELECT MAX(ID) FROM Czytelnicy";
 
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
