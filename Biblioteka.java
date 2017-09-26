/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteka;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Biblioteka extends JFrame {
    
static String     baza = "Biblioteka",
                  tabelaKsiazki = "Ksiazki",
                  tabelaCzytelnicy = "Czytelnicy",
                  tabelaWypozyczenia = "Wypozyczenia";
    int     maxIDKsiazki=0,
            maxIDCzytelnicy=0,
            maxIDWypozyczenia=0;

    Biblioteka(){
        
        super("Biblioteka v2.0");
        
        this.setPreferredSize(new Dimension(900, 900));
        this.setLocation(500, 100);
        
        Menu menu = new Menu();
        this.setContentPane(menu);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        
    }
    
    public static void main(String[] args) {
        
        Biblioteka biblioteka = new Biblioteka();
                
        stworzTabele(tabelaKsiazki, baza);
        stworzTabele(tabelaCzytelnicy, baza);
        stworzTabele(tabelaWypozyczenia, baza);       
        
    }
    
    public static Connection polacz(String baza) {
        Connection polaczenie = null;
        try {
                      
            polaczenie = DriverManager.getConnection("jdbc:sqlite:"+baza+".db");
            
        } catch (Exception e) {
            System.err.println("Błąd w połączeniu z bazą: \n" + e.getMessage());
            return null;
        }
        return polaczenie;
    }
    
    public static void stworzTabele(String tabela, String baza) {

        Connection polaczenie = polacz(baza);
        Statement stat = null;
        
        try {
            stat = polaczenie.createStatement();
            String tabelaSQL = null;

            switch (tabela) {
                case "Ksiazki":
                    
                    tabelaSQL = "CREATE TABLE IF NOT EXISTS " + tabela
                    + " (ID INT PRIMARY KEY     NOT NULL,"
                    + " Autor         CHAR(50)    NOT NULL, "
                    + " Tytul        CHAR(50)     NOT NULL,"
                    + "Wypozyczona CHAR(5)        NOT NULL)";
                    
                    break;

                case "Czytelnicy":
                    
                    tabelaSQL = "CREATE TABLE IF NOT EXISTS " + tabela
                    + " (ID INT PRIMARY KEY     NOT NULL,"
                    + " Imie         CHAR(50)    NOT NULL, "
                    + " Nazwisko        CHAR(50)     NOT NULL,"
                    + "Pesel CHAR(11)        NOT NULL)";
                    
                    break;
                    
                case "Wypozyczenia":
                    
                    tabelaSQL = "CREATE TABLE IF NOT EXISTS " + tabela
                    + " (ID INT PRIMARY KEY     NOT NULL,"
                    + " ID_ksiazki      INT    NOT NULL, "
                    + " ID_czytelnika   INT     NOT NULL,"
                    + "Data_wypozyczenia CHAR(10)        NOT NULL,"
                    + "Data_oddania CHAR(10))";
                    
                    break;

                default:
                    System.out.println("Nie istnieje tabela");
            }
            stat.executeUpdate(tabelaSQL);
            stat.close();
            polaczenie.close();
        } catch (SQLException e) {
            System.out.println("Błąd: " + e.getMessage());
        }
    }
    
    
          
    
        
        
        
               
        
        
    }
    

