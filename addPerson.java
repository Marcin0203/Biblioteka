/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteka;

import static biblioteka.Biblioteka.baza;
import static biblioteka.Biblioteka.polacz;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Marcin
 */
public class addPerson extends JPanel implements ActionListener {
    
    JButton addPersonButton = new JButton("Dodaj"),
            backButton = new JButton("Powrót");
    
    JLabel  nameLabel = new JLabel("Imie: "),
            surnameLabel = new JLabel("Nazwisko: "),
            peselLabel = new JLabel("Pesel: "),
            errorLabel = new JLabel();
    
    JTextField nameText = new JTextField(),
              surnameText = new JTextField(),
              peselText = new JTextField();
    
            
            
    
    public addPerson(){
        
        setLayout(null);
        setBounds(0, 0, 900, 900);
        
        addPersonButton.setBounds(150, 700, 300, 100);
        addPersonButton.addActionListener(this);
        
        backButton.setBounds(500, 700, 300, 100);
        backButton.addActionListener(this);
        
        errorLabel.setBounds(170, 570, 650, 100);
        errorLabel.setFont(new Font("Arial", 0, 50));
        
        nameLabel.setBounds(50, 50, 300, 100);
        nameLabel.setFont(new Font("Arial", 0, 50));
        
        surnameLabel.setBounds(50, 250, 300, 100);
        surnameLabel.setFont(new Font("Arial", 0, 50));
        
        peselLabel.setBounds(50, 450, 300, 100);
        peselLabel.setFont(new Font("Arial", 0, 50));
        
        peselText.setBounds(300, 450, 500, 100);
        peselText.setFont(new Font("Arial", 0, 50));
        
        nameText.setBounds(300, 50, 500, 100);
        nameText.setFont(new Font("Arial", 0, 50));
        
        surnameText.setBounds(300, 250, 500, 100);
        surnameText.setFont(new Font("Arial", 0, 50));
        
        add(nameLabel);
        add(surnameLabel);
        add(nameText);
        add(surnameText);
        add(addPersonButton);
        add(peselLabel);
        add(peselText);
        add(backButton);
        add(errorLabel);
        
    }
    
    public void setText(){
        surnameText.setText("");
        nameText.setText("");
        peselText.setText("");
    }
    
    public static void DodajDaneCzytelnicy(Czytelnicy czytelnik, String baza, String tabela){
        
        Connection polaczenie = polacz(baza);
        Statement stat = null;
        
        try {
            stat = polaczenie.createStatement();
            
            String dodajSQL = "INSERT INTO " + tabela + " (ID, Imie, Nazwisko, Pesel) "
                    + "VALUES ("
                    + "'"+ (czytelnik.getId()+1) + "',"
                    + "'" + czytelnik.getName() + "',"
                    + "'" + czytelnik.getSurname() + "',"
                    + "'" + czytelnik.getPesel() +"'"
                    + "  );";
            stat.executeUpdate(dodajSQL);
            stat.close();
            polaczenie.close();

        } catch (Exception e) {
            System.out.println("Nie mogę dodać danych " + e.getMessage());
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        
       if (o == addPersonButton) {
            
            Czytelnicy czytelnik = new Czytelnicy(nameText.getText(), surnameText.getText(), peselText.getText());
            checkText(czytelnik);      
        }
       
       if (o == backButton){
           
           this.removeAll();
            this.repaint();
            menuAdd menuAdd = new menuAdd();
            add(menuAdd);
            menuAdd.requestFocus();
            
       }
    }
    
    protected void checkText(Czytelnicy czytelnik){
        
        if (czytelnik.getName().equals("") || czytelnik.getPesel().equals("") || czytelnik.getSurname().equals("")){
            
            errorLabel.setForeground(Color.red);
            errorLabel.setText("Puste pole!");
            
        }
        else{
       
            if(checkBase(czytelnik)){
                
                            errorLabel.setText("Istnieje taki rekord w bazie.");
                            errorLabel.setForeground(Color.red);
            }
            else{
                
                DodajDaneCzytelnicy(czytelnik, Biblioteka.baza, Biblioteka.tabelaKsiazki);
                errorLabel.setForeground(Color.green);
                errorLabel.setText("Dodano!");
                setText();
            }
            }
        
    }

protected boolean checkBase(Czytelnicy czytelnik) {

        Connection polaczenie = polacz(baza);
        Statement stat;
        boolean check = false;
        int ID=0;
        
        
        try {
                      
            stat = polaczenie.createStatement();
            String szukajSQL = "SELECT Pesel FROM Czytelnicy WHERE Pesel = '" 
                + czytelnik.getPesel() + "'";
        
            stat.execute(szukajSQL);
            
            try (ResultSet wynik = stat.getResultSet()) {
                
                        while(wynik.next()){

                                         check = true;
                        } 
        stat.close();
        polaczenie.close();}
        } catch (SQLException e) {
            System.out.println("Błąd w szukaniu: " + e.getMessage());
}
    
        return check;
}
}
