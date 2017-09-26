/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteka;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Marcin
 */
public class Menu extends JPanel implements ActionListener {    
    
    JButton newButton = new JButton("Dodaj do bazy"),
            rentButton = new JButton("Wypożycz"),
            giveButton = new JButton("Oddaj"),
            exitButton = new JButton("Wyjście");
    
    Menu(){
        
        setLayout(null);
        setBounds(0, 0, 900, 900);
        
        newButton.setBounds(300, 50, 300, 100);
        newButton.addActionListener(this);
        
        rentButton.setBounds(300, 160, 300, 100);
        rentButton.addActionListener(this);
        
        giveButton.setBounds(300, 270, 300, 100);
        giveButton.addActionListener(this);
        
        exitButton.setBounds(300, 380, 300, 100);
        exitButton.addActionListener(this);
        
        add(newButton);
        add(rentButton);
        add(giveButton);
        add(exitButton);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        
        if (o == newButton) {
            
            removeAll();
            repaint();
            menuAdd menuAdd = new menuAdd();
            add(menuAdd);
            menuAdd.requestFocus();
            
        }
        
        if (o == rentButton){
            
            removeAll();
            repaint();
            Rent rent = new Rent();
            add(rent);
            rent.requestFocus();
        }
        
        if (o == exitButton) {
            
           System.exit(0);
            
        }
    }
    
}
