package studentManagement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import inJDBCutil.JDBCUtil;



public class SelectQuery {
	// TODO Auto-generated method stub
	public ArrayList<String> featch(int id) {
		ArrayList<String> ans =new ArrayList<>();
	
	Connection connection = null;
	PreparedStatement preparedstatement = null;
	ResultSet result=null;
	
	try {
		connection =JDBCUtil.getJdbcConnection();
		if(connection != null)
		{
			String mysqlQuery = "select * from student where id=? ";
			preparedstatement=connection.prepareStatement(mysqlQuery);
			if(preparedstatement!=null) {
				System.out.println("Enter the id Which you want to get from Database of mySql");
				
				preparedstatement.setInt(1, id);
				result =preparedstatement.executeQuery();
				if(result!=null) {
					if(result.next()) {
						System.out.print("NAME\t\tROLL_NUM\tRESULT\tID");
						System.out.println("\n"+result.getString(1)+"\t\t"+result.getInt(2)+"\t"+result.getString(3)+"\t");
						
						ans.add(result.getString(1));
						ans.add(result.getString(2));
						ans.add(result.getString(3));
						ans.add(result.getString(4));
					}
				}
				else {
					ans.add("not");
				}
				
			}
		}
	} catch (SQLException | IOException e) {
		
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally {
		try {
			JDBCUtil.closeResource(connection, preparedstatement,result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	return ans;
	}
}


