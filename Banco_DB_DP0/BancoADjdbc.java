import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;

import java.util.StringTokenizer;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class BancoADjdbc
{
	private PrintWriter archivoOut;
	private BufferedReader archivoIn;

	private Connection conexion;
	private Statement statement;

	private ClienteDP clientedp;

	public BancoADjdbc()
	{
		try
		{
			//Class.forName("com.mysql.jdbc.Driver").newInstance();
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

			conexion = DriverManager.getConnection("jdbc:mysql://localhost/banco2?user=root");
			System.out.println("La conexion a la DB ha sido exitosa.");
		}
		catch(ClassNotFoundException cnfe)
		{
			System.out.println("ERROR 1, "+cnfe);
		}
		catch(InstantiationException ie)
		{
			System.out.println("ERROR 2, "+ie);
		}
		catch(IllegalAccessException iae)
		{
			System.out.println("ERROR 3, "+iae);
		}
		catch(SQLException sqle)
		{
			System.out.println("ERROR 4, "+sqle);
		}
	}



	public String capturar(String datos)
	{
		String resultado ="";
		String insertCliente;
		StringTokenizer st;
		String ncta, nombre, tipo, saldo;
		
		
		/*st = new StringTokenizer(datos,"_");
		ncta = st.nextToken();
		nombre = st.nextToken();
		tipo =  st.nextToken();
		saldo = st.nextToken();
		*/
		
		clientedp = new ClienteDP(datos);

		insertCliente = "INSERT INTO Cliente VALUES ("+clientedp.toStringSQL()+")";
		try
		{
			// 1. Abrir BD o archivo para almacenar los datos
			//archivoOut = new PrintWriter(new FileWriter("Clientes.txt",true));
			statement = conexion.createStatement();


			// 2. Guardar o almacenar los datos
			//archivoOut.println(datos);
			statement.executeUpdate(insertCliente);

			// 3. Cerrar BD o archivo
			//archivoOut.close();
			statement.close();

			resultado = "Datos capturados...";

			System.out.println("insertCliente");
		}
		catch(SQLException sqle)
		{
			resultado = "Error al abrir DB O al ejecutar el INSERT..."+sqle;
			System.out.println("ERROR: "+sqle);
		}

		return resultado;
	}

	public String consultar()
	{
		String datos = "";
		String cliente;
		String query; 
		ResultSet trCliente;
		String ncta, nombre, tipo, saldo;

		query = "SELECT * FROM Cliente;";

		try
		{
			//archivoIn = new BufferedReader(new FileReader("Clientes.txt"));
			statement = conexion.createStatement();

			trCliente = statement.executeQuery(query);

			clientedp = new ClienteDP();
			
			while(trCliente.next())
			{
				clientedp.setNocta(trCliente.getString("nocta"));
				clientedp.setNombre(trCliente.getString("nombre"));
				clientedp.setTipo(trCliente.getString("tipo"));
				clientedp.setSaldo(trCliente.getInt("saldo"));

				datos = datos + clientedp.toString() + "\n";

			}

		}
		
		catch(SQLException sqle)
		{
			datos = "ERROR al abrir la DB...";
			System.out.println("ERROR: "+sqle);
		}

		return datos;
	}

	public String consultarTipo(String tcta)
	{
		String datos = "";
		String cliente;
		StringTokenizer stClientes;
		String ncta, nombre, tipo, saldo;
		boolean encontrado = false;
		String query; 
		ResultSet trCliente;

		query = "SELECT * FROM Cliente WHERE tipo='"+ tcta +"';";

		try
		{
			//archivoIn = new BufferedReader(new FileReader("Clientes.txt"));
			statement = conexion.createStatement();

			trCliente = statement.executeQuery(query);
			while(trCliente.next())
			{
				ncta = trCliente.getString("nocta");
				nombre = trCliente.getString("nombre");
				tipo = trCliente.getString("tipo");
				saldo = trCliente.getString("saldo");
				
				datos = datos + ncta + "_" + nombre + "_" + tipo + "_" + saldo + "\n";
				encontrado = true;
			}

			statement.close();
		}

		catch(SQLException sqle)
		{
			datos = "ERROR al conectar con la DB...";
			System.out.println("ERROR: "+sqle);
		}

		if (!encontrado)
			datos = "No se localizo el tipo "+tcta;
		System.out.println(query);

		return datos;
	}

	public String consultarNocta(String nocta)
	{
		String datos = "";
		String cliente;
		StringTokenizer stClientes;
		String ncta, nombre, tipo, saldo;
		boolean encontrado = false;
		String query; 
		ResultSet trCliente;

		query = "SELECT * FROM Cliente WHERE nocta='"+ nocta + "';";

		try
		{
			//archivoIn = new BufferedReader(new FileReader("Clientes.txt"));
			statement = conexion.createStatement();

			trCliente = statement.executeQuery(query);
			while(trCliente.next())
			{
				ncta = trCliente.getString("nocta");
				nombre = trCliente.getString("nombre");
				tipo = trCliente.getString("tipo");
				saldo = trCliente.getString("saldo");
				
				datos = datos + ncta + "_" + nombre + "_" + tipo + "_" + saldo + "\n";
				encontrado = true;
			}

			statement.close();
		}

		catch(SQLException sqle)
		{
			datos = "ERROR al conectar con la DB...";
			System.out.println("ERROR: "+sqle);
		}

		if (!encontrado)
			datos = "No se localizo el num de cuenta "+nocta;
		System.out.println(query);

		return datos;
	}
}