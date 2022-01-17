/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReadExcel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import java.io.IOException;

/**
 *
 * @author NieveFernandez
 */
public class GenerateFrame extends JFrame{
    public JPanel panel;
    public String lot;
    public JLabel labelLot;
    public JTextField textLot;
    public JTextArea textArea;
    public JButton openButton; 
   
    public GenerateFrame(){
        //setBounds(100,200, 900, 500);
        setSize(1500, 600);
        setLocationRelativeTo(null);  //colocar el panel en el centro de la pantalla 
        setTitle("Generate Meters"); 
       
        createPanel();
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void createPanel(){
        panel = new JPanel();
        //panel.setLayout(null);
        //panel.setBackground(Color.GRAY); 
        this.setLayout(new BorderLayout());
        
        this.getContentPane().add(panel);
            
        startComponents();
    }
    
    private void startComponents(){
        
        labelLot = new JLabel("Lot: ", SwingConstants.CENTER);
        labelLot.setFont(new Font("arial", 1, 15));
        
        textLot = new JTextField(5);
        
        openButton = new JButton("Select File");
        openButton.setFont(new Font("arial", 1, 15));
        
        textArea = new JTextArea(120, 60);
        textArea.setMargin(new Insets(15,15,5,5));
        textArea.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(textArea);
             
        panel.add(labelLot);
        panel.add(textLot);
        panel.add(openButton);
               
        this.add(panel, BorderLayout.PAGE_START);
        this.add(logScrollPane, BorderLayout.CENTER);
        
        
        ActionListener action = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
               FileChooser(); 
            }
        };
        openButton.addActionListener(action);
    }
    
    public void FileChooser(){
        
        ReadExcel read = new ReadExcel();
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);

        if (fileChooser.showOpenDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {

            try {
                File fileXls = fileChooser.getSelectedFile();

                String querys = read.createQuerys(fileXls, textLot.getText());
                textArea.setText(querys);

            } catch (Exception ex){
                System.out.println("****ERROR DE FORMATO DEL FICHERO****" + ex.getMessage());
            }

        } else {
            System.out.println("****NO SE HA SELECCIONADO NINGUN FICHERO****");
        }
    }
}

