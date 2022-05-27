import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClienteGUI extends JFrame implements ActionListener
{
	// Atributos 
		private JTextField tfNocta, tfNombre, tfTipo, tfSaldo;
		private JButton bCapturar, bConsultar, bConsultarNocta, bConsultarTipo;
		private JTextArea taDatos;
		private JPanel panel1, panel2;

		private BancoADjdbc banco = new BancoADjdbc();

	public ClienteGUI()
	{
		// Poner el título del JFrame
		setTitle("Banco WEB-OOP");

		// Crear los objetos de los atributos
		tfNocta = new JTextField();
		tfNombre = new JTextField();
		tfTipo = new JTextField();
		tfSaldo = new JTextField();

		bCapturar = new JButton("Capturar Datos");
		bConsultar = new JButton("Consultar Clientes");
		bConsultarTipo = new JButton("Consultar Tipo");
		bConsultarNocta = new JButton("Consultar No. Cta."); 

		bCapturar.addActionListener(this);
		bConsultar.addActionListener(this);
		bConsultarTipo.addActionListener(this);
		bConsultarNocta.addActionListener(this);

		taDatos = new JTextArea(8,20);

		panel1 = new JPanel();
		panel2 = new JPanel();

		// Definir los Layouts de los JPanel
		panel1.setLayout(new GridLayout(6,2));
		panel2.setLayout(new FlowLayout());

		// Colocar los atributos en los JPanels correspondientes
		panel1.add(new JLabel("No. de Cuenta: "));
		panel1.add(tfNocta);
		panel1.add(new JLabel("Nombre: "));
		panel1.add(tfNombre);
		panel1.add(new JLabel("Tipo de Cuenta: "));
		panel1.add(tfTipo);
		panel1.add(new JLabel("Saldo: "));
		panel1.add(tfSaldo); 
		panel1.add(bCapturar);
		panel1.add(bConsultar);
		panel1.add(bConsultarNocta);
		panel1.add(bConsultarTipo);

		panel2.add(panel1);
		panel2.add(new JScrollPane(taDatos));

		// Adicionar los JPanels al JFrame y hacerlo visible
		add(panel2);
		setSize(400,350);
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private String obtenerDatos()
	{
		String datos="", ncta, nombre, tipo, saldo;
		
		ncta = tfNocta.getText();
		nombre = tfNombre.getText();
		tipo = tfTipo.getText();
		saldo = tfSaldo.getText();

		if (ncta.equals("") || nombre.isEmpty() || tipo.isEmpty() || saldo.equals(""))
			datos = "VACIO";
		else
		{
			try
			{
				int cantidad = Integer.parseInt(saldo);
				datos = ncta+"_"+nombre+"_"+tipo+"_"+saldo;
			}
			catch(NumberFormatException nfe)
			{
				datos = "NO_NUMERICO";
				System.out.println("ERROR: "+nfe);
			}
		}

		return datos;
	}

	public void actionPerformed(ActionEvent evento)
	{
		String respuesta = "", datos = "";
		if (evento.getSource() == bCapturar)
		{
			datos = obtenerDatos();

			if(datos.equals("VACIO"))
				respuesta = "Algun campo esta vacio...";
			else
				if (datos.equals("NO_NUMERICO"))
					respuesta = "El saldo debe ser numerico...";
				else
				{
					respuesta = banco.capturar(datos);
				}

			taDatos.setText(respuesta);
		}

		if (evento.getSource() == bConsultar)
		{
			datos = banco.consultar();

			taDatos.setText(datos);
		}

		if (evento.getSource() == bConsultarNocta)
		{
			String ncta = tfNocta.getText();

			datos = banco.consultarNocta(ncta);

			taDatos.setText(datos);
		}

		if (evento.getSource() == bConsultarTipo)
		{
			String tcta = tfTipo.getText();

			datos = banco.consultarTipo(tcta);

			taDatos.setText(datos);
		}
	}

	public static void main(String args[])
	{
		ClienteGUI cliente = new ClienteGUI();
	}
}