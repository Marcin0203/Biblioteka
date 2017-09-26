/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteka;

import static biblioteka.Biblioteka.baza;
import static biblioteka.Biblioteka.polacz;
import java.sql.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Marcin
 */
public class addBook extends JPanel implements ActionListener {

    JButton addBookButton = new JButton ("Dodaj"),
            backButton = new JButton("Powrót");
    
    JLabel  authorLabel = new JLabel("Autor: "),
            titleLabel = new JLabel("Tytuł:"),
            errorLabel = new JLabel();
    
    JTextField authorText = new JTextField(),
              titleText = new JTextField();
            
    public addBook(){
        
        setLayout(null);
        setBounds(0, 0, 900, 900);
        
        addBookButton.setBounds(150, 600, 300, 100);
        addBookButton.addActionListener(this);
        
        backButton.setBounds(500, 600, 300, 100);
        backButton.addActionListener(this);
        
        authorLabel.setBounds(100, 50, 300, 100);
        authorLabel.setFont(new Font("Arial", 0, 50));
        
        titleLabel.setBounds(100, 250, 300, 100);
        titleLabel.setFont(new Font("Arial", 0, 50));
        
        errorLabel.setBounds(200, 400, 650, 100);
        errorLabel.setFont(new Font("Arial", 0, 50));
        
        authorText.setBounds(300, 50, 500, 100);
        authorText.setFont(new Font("Arial", 0, 50));
        
        titleText.setBounds(300, 250, 500, 100);
        titleText.setFont(new Font("Arial", 0, 50));
        
        add(authorLabel);
        add(authorText);
        add(titleLabel);
        add(titleText);
        add(addBookButton);
        add(backButton);
        add(errorLabel);
        
    }
    
    
    public void setText(){
        authorText.setText("");
        titleText.setText("");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        
        if (o == addBookButton) {
            
            Ksiazki ksiazka = new Ksiazki(authorText.getText(), titleText.getText());
            checkText(ksiazka);
            
        }
        
        if (o == backButton){
            
            this.removeAll();
            this.repaint();
            menuAdd menuAdd = new menuAdd();
            add(menuAdd);
            menuAdd.requestFocus();

        }
    }
    
    protected void checkText(Ksiazki ksiazka){
        
        if (ksiazka.getAutor().equals("") || ksiazka.getTytul().equals("")){
            
            errorLabel.setForeground(Color.red);
            errorLabel.setText("Puste pole!");
            
        }
        else{
       
            if(checkBase(ksiazka)){
                
                            errorLabel.setText("Istnieje taki rekord w bazie.");
                            errorLabel.setForeground(Color.red);
            }
            else{
                
                dodajDaneKsiazki(ksiazka, Biblioteka.baza, Biblioteka.tabelaKsiazki);
                errorLabel.setForeground(Color.green);
                errorLabel.setText("Dodano!");
                setText();
            }
            }
        
    }
    
    public static void dodajDaneKsiazki(Ksiazki ksiazka, String baza, String tabela){
    
        Connection polaczenie = Biblioteka.polacz(baza);
        Statement stat;
        
        try {
            stat = polaczenie.createStatement();
            
            String dodajSQL = "INSERT INTO " + tabela + " (ID,Autor, Tytul, Wypozyczona) "
                    + "VALUES ("
                    + "'"+ (ksiazka.getId()+1) + "',"
                    + "'" + ksiazka.getAutor() + "',"
                    + "'" + ksiazka.getTytul() + "',"
                    + "'FALSE'"
                    + "  );";
            stat.executeUpdate(dodajSQL);
            stat.close();
            polaczenie.close();

        } catch (Exception e) {
            System.out.println("Nie mogę dodać danych " + e.getMessage());
        }        
    }
    
    protected boolean checkBase(Ksiazki ksiazka) {

        Connection polaczenie = polacz(baza);
        Statement stat;
        boolean check = false;
        int ID=0;
        
        
        try {
                      
            stat = polaczenie.createStatement();
            String szukajSQL = "SELECT ID, Autor FROM Ksiazki WHERE Autor = '" 
                + ksiazka.getAutor() + "'";
        
            stat.execute(szukajSQL);
            
            try (ResultSet wynik = stat.getResultSet()) {
                
                        while(wynik.next()){
                            
                            ID = wynik.getInt(1);
                            String szukajSQLnext = "SELECT ID, Autor, Tytul FROM Ksiazki WHERE ID = " 
                                                    + ID;
                            stat.execute(szukajSQLnext);
                            
                             try (ResultSet wyniknext = stat.getResultSet()) {
                                 
                                 while(wynik.next()){
                                     
                                     if(wyniknext.getString(3).equals(ksiazka.getTytul())){
                                         
                                         check = true;
                                     }
                                }
                             }    
                        }
        
        stat.close();
        polaczenie.close();}
        } catch (SQLException e) {
            System.out.println("Błąd w szukaniu: " + e.getMessage());
}
    
        return check;
}
}


