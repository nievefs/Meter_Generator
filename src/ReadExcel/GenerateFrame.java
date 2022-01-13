/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReadExcel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.*;

/**
 *
 * @author NieveFernandez
 */
public class GenerateFrame extends JFrame{
    public JPanel panel;

    public GenerateFrame(){
        //setBounds(100,200, 900, 500);
        setSize(900, 500);
        setLocationRelativeTo(null); 
        setTitle("Generate Meters");  //colocar el panel en el centro de la pantalla
       
        createPanel();
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setVisible(true);
    }
    
    private void createPanel(){
        panel = new JPanel();
        //panel.setLayout(null);
        //panel.setBackground(Color.GRAY); 
        this.getContentPane().add(panel);
        startComponents();
    }
    
    private void startComponents(){
        JLabel labelLot = new JLabel("Lot: ", SwingConstants.CENTER);
        labelLot.setFont(new Font("arial", 1, 15));
        
        JTextField textLot = new JTextField(5);
        
        JButton openButton = new JButton("Select File");
        //openButton.setSize(100, 20);
        openButton.setFont(new Font("arial", 1, 15));
        
        JTextArea textArea = new JTextArea(25, 80);
        textArea.setText("holaaaa");
        //log.setMargin(new Insets(5, 5, 5, 5));
        //log.setEditable(true);
        //JScrollPane logScrollPane = new JScrollPane(log);
        
        System.out.println(textArea.getText());
        
        panel.add(labelLot);
        panel.add(textLot);
        panel.add(openButton);
        panel.add(textArea);
     
    }
    
   
    
    
}


    
    
    /*
    class Panel extends JPanel{
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            //dibuja el texto en la posicion x, y
            g.drawString("Estamos escribiendo ", 10, 10);

        }
    }*/