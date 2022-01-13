/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReadExcel;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author NieveFernandez
 */
public class GeneratePanel extends JFrame{

    public GeneratePanel(){
        //setBounds(100,200, 900, 500);
        setSize(900, 500);
        setLocationRelativeTo(null); 
        setTitle("Generate Meters");  //colocar el panel en el centro de la pantalla
        Lamina milamina = new Lamina();
        add(milamina);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}

class Lamina extends JPanel{
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        //dibuja el texto en la posicion x, y
        g.drawString("Estamos escribiendo ", 10, 10);
    
    }

}