package logicaAccesoADatos;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import logicaDeNegocios.Usuario;

public class conexion {

	private static final String SQL_SERIALIZE_OBJECT = "INSERT INTO serialized_java_objects(object_name, serialized_object) VALUES (?, ?)";
	private static final String SQL_DESERIALIZE_OBJECT = "SELECT serialized_object FROM serialized_java_objects WHERE object_name = ?";
	

	public static Connection conexionDB() throws ClassNotFoundException, SQLException{
		Connection connection = null;

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://us-cdbr-iron-east-03.cleardb.net/ad_ed0a33a710978e7";
		String username = "b6cf09759bc632";
		String password = "984a8b90";
		Class.forName(driver);
		connection = DriverManager.getConnection(url, username, password);
		return connection;
		
	}
	
	public static void serializeJavaObjectToDB(Connection connection,String nombre,Object objetoGuardar,String id) throws SQLException {

		// just setting the class name
		PreparedStatement ejecucionQuery = connection.prepareStatement(SQL_SERIALIZE_OBJECT);

		// just setting the class name
		ejecucionQuery.setString(1, id);
		ejecucionQuery.setObject(2, objetoGuardar);
		ejecucionQuery.executeUpdate();
		ResultSet resultadoQuery = ejecucionQuery.getGeneratedKeys();
		int serialized_id = -1;
		if (resultadoQuery.next()) {
			serialized_id = resultadoQuery.getInt(1);
		}
		resultadoQuery.close();
		ejecucionQuery.close();
		connection.close();

	}

	/**
	 * To de-serialize a java object from database
	 *
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object deSerializeJavaObjectFromDB(Connection connection,String serialized_id){
		try{
		PreparedStatement ejecucionQuery = connection.prepareStatement(SQL_DESERIALIZE_OBJECT);
		ejecucionQuery.setString(1, serialized_id);
		ResultSet resultadoQuery = ejecucionQuery.executeQuery();
		resultadoQuery.next();
			
		byte[] buf = resultadoQuery.getBytes(1);
		ObjectInputStream objectIn = null;
		if (buf != null)
			objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
		Object deSerializedObject = objectIn.readObject();

		resultadoQuery.close();
		ejecucionQuery.close();
		connection.close();
		
		return deSerializedObject;
		
		}catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
		
	}


	/**
	 * Serialization and de-serialization of java object from mysql
	 *
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public static void main(String args[]) throws ClassNotFoundException,
			SQLException, IOException {
				
		Usuario user = new Usuario(1,2014086158,"Matamoros","Serrano","Kevin","k.andres0620@gmail.com","aaaAAA123");
		
		serializeJavaObjectToDB(conexionDB(),"peresultadoQueryona1",user,"k.andres0620@gmail.com");

		
		// de-serializing java object from mysql database
		Usuario objFromDatabase = (Usuario) deSerializeJavaObjectFromDB(conexionDB(),"k.andres0620@gmail.com");
		System.out.println(objFromDatabase.getNombre());
		
		conexionDB().close();
	}
}
