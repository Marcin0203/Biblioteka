/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteka;

import static biblioteka.addBook.dodajDaneKsiazki;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Marcin
 */
public class menuAdd extends JPanel implements ActionListener {
    
    JButton newBookButton = new JButton("Dodaj książkę"),
            newPersonButton = new JButton("Dodaj osobę"),
            backButton = new JButton("Powrót");
    
    menuAdd(){
        
        setLayout(null);
        setBounds(0, 0, 900, 900);
        
        newBookButton.setBounds(300, 50, 300, 100);
        newBookButton.addActionListener(this);
        
        newPersonButton.setBounds(300, 160, 300, 100);
        newPersonButton.addActionListener(this);
        
        backButton.setBounds(300, 270, 300, 100);
        backButton.addActionListener(this);
        
        add(newBookButton);
        add(newPersonButton);
        add(backButton);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        
        if (o == newBookButton) {
            
            this.removeAll();
            this.repaint();
            addBook newBook = new addBook();
            add(newBook);
            newBook.requestFocus();
            
        }
        
        if (o == newPersonButton) {
            
            this.removeAll();
            this.repaint();
            addPerson newPerson = new addPerson();
            add(newPerson);
            newPerson.requestFocus();
            
        }
        
        
        if (o == backButton){

           this.removeAll();
           this.repaint();
           Menu menu = new Menu();
           add(menu);
           menu.requestFocus();
            
        }
    }
}
