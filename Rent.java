/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteka;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Marcin
 */
public class Rent extends JPanel implements ActionListener {
    
    JButton     backButton = new JButton("Powrót"),
                rentButton = new JButton ("Wypożycz"),
                searchPerson = new JButton("Wyszukaj osoby"),
                searchBook = new JButton ("Wyszukaj książki"),
                selectInListBookButton = new JButton("Wybierz"),
                selectInListPersonButton = new JButton ("Wybierz");
    
    JLabel      personLabel = new JLabel("Osoba (Wpisz minimum jedną pozycję): "),
                bookLabel = new JLabel ("Książka (Wpisz minimum jedną pozycję):"),
                nameLabel = new JLabel ("Imię: "),
                surnameLabel = new JLabel ("Nazwisko: "),
                peselLabel = new JLabel ("Pesel:"),
                authorLabel = new JLabel ("Autor: "),
                TitleLabel = new JLabel ("Tytuł: "),
                selectInListBookLabel = new JLabel ("Lub wybierz z listy: "),
                selectInListPersonLabel = new JLabel ("Lub wybierz z listy: "),
                selectedPersonLabel = new JLabel ("Wybrana osoba: "),
                selectedBookLabel = new JLabel ("Wybrana książka: "),
                infoLabel = new JLabel ();
    
    JTextField  nameText = new JTextField (),
                surnameText = new JTextField (),
                peselText = new JTextField (),
                authorText = new JTextField (),
                TitleText = new JTextField (),
                selectedPersonText = new JTextField("Brak"),
                selectedBookText = new JTextField("Brak");
    
    public Rent(){
       
        setLayout(null);
        setBounds(0, 0, 900, 900);
        
        personLabel.setBounds(125, 10, 360, 50);
        personLabel.setFont(new Font("Arial", 0, 20));
        
        nameLabel.setBounds(75, 70, 100, 50);
        nameLabel.setFont(new Font("Arial", 0, 20));
        
        nameText.setBounds(175, 70, 300, 50);
        nameText.setFont(new Font("Arial", 0, 20));
        
        surnameLabel.setBounds(75, 130, 100, 50);
        surnameLabel.setFont(new Font("Arial", 0, 20));
        
        surnameText.setBounds(175, 130, 300, 50);
        surnameText.setFont(new Font("Arial", 0, 20));
        
        peselLabel.setBounds(75, 190, 100, 50);
        peselLabel.setFont(new Font("Arial", 0, 20));
        
        peselText.setBounds(175, 190, 300, 50);
        peselText.setFont(new Font("Arial", 0, 20));
        
        searchPerson.setBounds(225, 250, 200, 50);
        searchPerson.addActionListener(this);
        
        selectInListPersonLabel.setBounds(600, 100, 200, 50);
        selectInListPersonLabel.setFont(new Font("Arial", 0, 20));
        
        selectInListPersonButton.setBounds(580, 175, 200, 50);
        selectInListPersonButton.addActionListener(this);
        
        bookLabel.setBounds(125, 330, 360, 50);
        bookLabel.setFont(new Font("Arial", 0, 20));
        
        authorLabel.setBounds(75, 390, 100, 50);
        authorLabel.setFont(new Font("Arial", 0, 20));
        
        authorText.setBounds(175, 390, 300, 50);
        authorText.setFont(new Font("Arial", 0, 20));
        
        TitleLabel.setBounds(75, 450, 100, 50);
        TitleLabel.setFont(new Font("Arial", 0, 20));
        
        TitleText.setBounds(175, 450, 300, 50);
        TitleText.setFont(new Font("Arial", 0, 20));
        
        searchBook.setBounds(225, 510, 200, 50);
        searchBook.addActionListener(this);
        
        selectInListBookLabel.setBounds(600, 390, 200, 50);
        selectInListBookLabel.setFont(new Font("Arial", 0, 20));
        
        selectInListBookButton.setBounds(580, 465, 200, 50);
        selectInListBookButton.addActionListener(this);
        
        selectedPersonLabel.setBounds(75, 570, 200, 50);
        selectedPersonLabel.setFont(new Font("Arial", 0, 20));
        
        selectedPersonText.setBounds(275, 570, 500, 50);
        selectedPersonText.setFont(new Font("Arial", 0, 20));
        selectedPersonText.setEditable(false);
        selectedPersonText.setBorder(BorderFactory.createEmptyBorder());
        
        selectedBookLabel.setBounds(75, 630, 200, 50);
        selectedBookLabel.setFont(new Font("Arial", 0, 20));
        
        selectedBookText.setBounds(275, 630, 500, 50);
        selectedBookText.setFont(new Font("Arial", 0, 20));
        selectedBookText.setEditable(false);
        selectedBookText.setBorder(BorderFactory.createEmptyBorder());
        
        rentButton.setBounds(125, 730, 220, 75);
        rentButton.addActionListener(this);
        
        backButton.setBounds(500, 730, 220, 75);
        backButton.addActionListener(this);
        
        infoLabel.setBounds(200, 670, 500, 50);
        infoLabel.setFont(new Font("Arial", 0, 20));
        
        add(personLabel);
        add(bookLabel);
        add(backButton);
        add(nameLabel);
        add(nameText);
        add(surnameText);
        add(surnameLabel);
        add(peselLabel);
        add(peselText);
        add(searchPerson);
        add(authorLabel);
        add(authorText);
        add(TitleLabel);
        add(TitleText);
        add(searchBook);
        add(selectInListBookLabel);
        add(selectInListBookButton);
        add(selectInListPersonLabel);
        add(selectInListPersonButton);
        add(selectedPersonLabel);
        add(selectedPersonText);
        add(selectedBookLabel);
        add(selectedBookText);
        add(rentButton);
        add(backButton);
        add(infoLabel);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        
       if (o == backButton) {
            
            this.removeAll();
            this.repaint();
            Menu menu = new Menu();
            add(menu);
            menu.requestFocus();
            
        }
       
       if (o == rentButton){
           
           checkSelected();
           
       }
       
       
    }
    
    void checkSelected(){
        
        if(selectedPersonText.getText().equals("Brak") || selectedBookText.getText().equals("Brak")){
            
            infoLabel.setForeground(Color.red);
            infoLabel.setText("Nie wybrano wszystkich danych!");
            
        }
    }
    
}
