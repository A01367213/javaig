import javax.swing.JOptionPane;

public class Division{
    
    public static void main(String args[]){
        String strA, strB;
        int a,b;
        float c;
        
        //Obtener los valores de A y B
        strA=JOptionPane.showInputDialog("Variable A: ");
        strB=JOptionPane.showInputDialog("Variable B: ");
        a=Integer.parseInt(strA);
        b=Integer.parseInt(strB);        
        //A/B=C
        c=(float)a/b;
        
        //R
        JOptionPane.showMessageDialog(null, "División de A/B: "+c);
    }

}