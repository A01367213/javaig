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

	public BancoADjdbc()
	{
		try
		{
			//Class.forName("com.mysql.jdbc.Driver").newInstance();
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

			conexion = DriverManager.getConnection("jdbc:mysql://localhost/banco?user=root");
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
		String ncta, nombre, tipo, saldo;
		StringTokenizer st;

		//insertCliente = "INSERT INTO Cliente VALUES('1777', 'David Suarez','Ahorro',7000)";
		st=new StringTokenizer(datos,"_");
		ncta = st.nextToken;
		nombre = st.nextToken;
		tipo = st.nextToken;
		saldo = st.nextToken;

		insertCliente = "INSERT INTO Cliente VALUES('"+ncta+"','"+nombre+"','"+tipo+"',"+saldo+")";

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

			System.out.println(insertCliente);
		}
		catch(SQLException sqle)
		{
			resultado = "Error al abrir DB..." + sqle;
			System.out.println("ERROR: "+sqle);
		}

		return resultado;
	}

	public String consultar()
	{
		String datos = "";
		String cliente;
		String query;
		String ncta, nombre, tipo, saldo;
		ResultSet trCliente;

		query = "SELECT * FROM CLIENTE";

		try
		{
			//Abrir archivo o base de datos
			//archivoIn = new BufferedReader(new FileReader("Clientes.txt"));
			statement=conexion.createStatement();


			//Procesar archivo o tabla para obtener datos
			trCliente = statement.executeQuery(query);
			while(trCliente.next()){
				ncta   = trCliente.getString("nocta");
				nombre = trCliente.getString("nombre");
				tipo   = trCliente.getString(3);
				saldo  = trCliente.getInt(saldo);

				datos = datos+"_"+ncta+"_"+nombre+"_"+tipo+"_"+saldo+"\n" 

			}



			/*while (archivoIn.ready())
			{
				cliente = archivoIn.readLine();
				datos = datos + cliente + "\n";
			}*/



			//archivoIn.close();
			statement.close();
		}
		
		catch(SQLException fnfe)
		{
			datos = "ERROR al abrir el archivo...";
			System.out.println("ERROR: "+fnfe);
		}

		/*catch(IOException ioe)
		{
			datos = "Error al cerrar el archivo...";
			System.out.println("ERROR: "+ioe);
		}*/
		
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

		query = "SELECT * FROM Cliente WHERE tipo='"+tcta+"'";

		try
		{
			statement = conexion.createStatement();
			//archivoIn = new BufferedReader(new FileReader("Clientes.txt"));
			
			/*while (archivoIn.ready())
			{
				cliente = archivoIn.readLine();

				stClientes = new StringTokenizer(cliente,"_");
				
				ncta = stClientes.nextToken();
				nombre = stClientes.nextToken();
				tipo = stClientes.nextToken();
				saldo = stClientes.nextToken();

				if (tcta.equals(tipo))
				{
					datos = datos + cliente + "\n";
					encontrado = true;
				}
			}
			archivoIn.close();*/

			trCliente = statement.executeQuery(query);
			while(trCliente.next()){
				ncta   = trCliente.getString("nocta");
				nombre = trCliente.getString("nombre");
				tipo   = trCliente.getString(3);
				saldo  = trCliente.getInt(saldo);

				datos = datos+"_"+ncta+"_"+nombre+"_"+tipo+"_"+saldo+"\n" 

				encontrado = true;

			statement.close();
		}

		catch(SQLException fnfe)
		{
			datos = "ERROR al abrir el archivo...";
			System.out.println("ERROR: "+fnfe);
		}

		/*catch(IOException ioe)
		{
			datos = "Error al cerrar el archivo...";
			System.out.println("ERROR: "+ioe);
		}*/

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

		query = "SELECT * FROM Cliente WHERE nocta='"+nocta+"'";

		try
		{
			statement = conexion.createStatement();

			/*archivoIn = new BufferedReader(new FileReader("Clientes.txt"));
			
			while (archivoIn.ready())
			{
				cliente = archivoIn.readLine();

				stClientes = new StringTokenizer(cliente,"_");
				
				ncta = stClientes.nextToken();
				nombre = stClientes.nextToken();
				tipo = stClientes.nextToken();
				saldo = stClientes.nextToken();

				if (nocta.equals(ncta))
				{
					datos = datos + cliente + "\n";
					encontrado = true;
				}
			}
			archivoIn.close();*/

			trCliente = statement.executeQuery(query);
			while(trCliente.next()){
				ncta   = trCliente.getString("nocta");
				nombre = trCliente.getString("nombre");
				tipo   = trCliente.getString(3);
				saldo  = trCliente.getInt(saldo);

				datos = datos+"_"+ncta+"_"+nombre+"_"+tipo+"_"+saldo+"\n" 

				encontrado = true;

			statement.close();

		}

		catch(SQLException fnfe)
		{
			datos = "ERROR al abrir el archivo...";
			System.out.println("ERROR: "+fnfe);
		}

		/*catch(IOException ioe)
		{
			datos = "Error al cerrar el archivo...";
			System.out.println("ERROR: "+ioe);
		}*/

		if (!encontrado)
			datos = "No se localizo el tipo "+nocta;
		
		System.out.println(query);

		return datos;
	}
}