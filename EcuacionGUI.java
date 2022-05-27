import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author destr
 */
public class EcuacionGUI extends JFrame {
    
    //Atributos
    private JTextField tfCoeficienteA, tfCoeficienteB, tfCoeficienteC;
    private JButton bCalcular, bSalir;
    
    //Constructor
    public EcuacionGUI(){
        //JOptionPane.showMessageDialog(null,"Constructor 1");
        
        //Poner título al JFrame
        setTitle("Ecuación Cuadrática");
        
        //Crear objetos de los atributos
        tfCoeficienteA = new JTextField();
        tfCoeficienteB = new JTextField();
        tfCoeficienteC = new JTextField();
        bCalcular = new JButton("Calcular Raices");
        bSalir = new JButton("Salir");
        
        //Crear layout para los objetos
        setLayout(new GridLayout(4,2));
        
        //Colocar objetos en JFame
        add(new JLabel("Coeficiente A= "));
        add(tfCoeficienteA);
        add(new JLabel("Coeficiente B= "));
        add(tfCoeficienteB);
        add(new JLabel("Coeficiente C= "));
        add(tfCoeficienteC);
        add(bCalcular);
        add(bSalir);
        
        //Hacer visible el JFrame
        setSize(300,300);
        setVisible(true);
    }       
            
    public static void main(String args[]) {
        // TODO code application logic here
        EcuacionGUI ecuacion = new EcuacionGUI();
    }
}
