import javax.swing.JOptionPane;

public class Ecuacion{
    
    public static void main(String args[]){
        String strA, strB, strC;
        int a, b, c, radical;
        double x1, x2;
        
        // 1.- Obtener los coeficientes de la ecuación
        strA=JOptionPane.showInputDialog("Coeficiente A: ");
        strB=JOptionPane.showInputDialog("Coeficiente B: ");
        strC=JOptionPane.showInputDialog("Coeficiente C: ");
        
        a=Integer.parseInt(strA);
        b=Integer.parseInt(strB);
        c=Integer.parseInt(strC);
        
        // 2.- Checar los coeficientes, calcular raices y desplegar resultados
        if(a==0){
            if(b==0){
                JOptionPane.showMessageDialog(null,"No existe ecuación");
            }
            else{
                x1=(float)-c/b;
                JOptionPane.showMessageDialog(null,"Ecuación Lineal. X = "+x1);
            }
        }
        else{
            radical=(b*b)-(4*a*c);
            if(radical<0){
                JOptionPane.showMessageDialog(null,"Raices Imaginarias...");
            }
            else{
                x1=(-b+Math.sqrt(radical))/(2*a);
                x2=(-b-Math.sqrt(radical))/(2*a);
                JOptionPane.showMessageDialog(null,"Raices Reales. \nx1 = "+x1+" \nx2 = " + x2);
            }
        }
        
    }
}