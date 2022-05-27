import javax.swing.JOptionPane;

public class Suma{
    
    public static void main(String args[]){
        String strA, strB, strC;
        int a,b,c;
        
        //Obtener los valores de A y B
        strA=JOptionPane.showInputDialog("Variable A: ");
        strB=JOptionPane.showInputDialog("Variable B: ");
        a=Integer.parseInt(strA);
        b=Integer.parseInt(strB);        
        
        //A+B=C
        strC=strB+strA;
        c=a+b;
        
        //R
        JOptionPane.showMessageDialog(null, "Suma de A+B: "+c);
    }

}