import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ExponencialGUI extends JFrame implements ActionListener{
	private JTextField tfBase, tfExp;
	private JButton bResultado, bSalir;

	public ExponencialGUI(){
		tfBase = new JTextField();
		tfExp = new JTextField();
		bResultado = new JButton("Resultado");
		bSalir = new JButton("Salir");

		bResultado.addActionListener(this);
		bSalir.addActionListener(this);

		setLayout(new GridLayout(3,2));

		add(new JLabel("Base: "));
		add(tfBase);
		add(new JLabel("Exponente: "));
		add(tfExp);
		add(bResultado);
		add(bSalir);

		setSize(300,300);
		setVisible(true);

	}

	private void potencia(int base, int exp){
		double r;

		r = Math.pow(base,exp);
		JOptionPane.showMessageDialog(null, "Resultado: "+r);
	}

	public void actionPerformed(ActionEvent evento){
		int exp, base;
		String strBase, strExp;

		if (evento.getSource() == bResultado){
			strBase = tfBase.getText();
			strExp = tfExp.getText();
			base = Integer.parseInt(strBase);
			exp = Integer.parseInt(strExp);

			potencia(base,exp);
		}

		if (evento.getSource() == bSalir){
			System.exit(0);
		}
	}

	public static void main(String args[]) {
		ExponencialGUI exponencial = new ExponencialGUI();
	}
}