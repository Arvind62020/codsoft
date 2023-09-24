package inJDBCutil;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {
	
	
	
	public static Connection getJdbcConnection() throws SQLException, IOException {
		FileInputStream FIS = new FileInputStream("Application.properties");
		
		Properties P =new Properties();
		P.load(FIS);
	 
	String url=P.getProperty("url");
	String user= P.getProperty("user");
	String password = P.getProperty("password");

	//just to confirm if the properties came here we will just do the below code

	System.out.println(url);
	System.out.println(user);
	System.out.println(password);

	       //STEP2: establish the connection 
	Connection connection = DriverManager.getConnection(url, user, password);


	return connection;


	}
	public static void closeResource(Connection connection, PreparedStatement preparedstatement,ResultSet resultset) throws SQLException {
		if(connection!=null) {
			connection.close();
		}if(preparedstatement!=null) {
			preparedstatement.close();
		}
		if(resultset!=null) {
			resultset.close();
		}
	}
	}



