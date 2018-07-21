package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import connection.DBConnection;


/*
 * LoginDao class acts as an interface between the Servlet and the DB
 * when a user logs in to the application
 * retrieves the username and password from DB
 * @author Anirudh
 */ 

public class LoginDao 
{
	private static final String error = "SQL error";
	static Logger logger = Logger.getLogger(LoginDao.class);
	
	/*
	 * @see authenticate(String username, String password), returns int
	 * returns the number of records found in the database
	 * @param String username
	 */
	public static int authenticate(String username, String password)
	{
		
		Connection con = DBConnection.connect();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		// setting up db connection
		try
		{
			
			// retrieves the login details from Login table
			String sql = "SELECT * FROM Login WHERE username = ? and password = ?";
			
			statement = con.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);
			
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				return 1;
			}
			
			
		}
		
		catch (Exception e) 
		{
			logger.error(error + e);
		}
		
		// closes all connections and PreparedStatements
		finally 
		{
			try 
			{
				if (statement != null)
					statement.close();
			} 
			
			catch (SQLException e) 
			{
				logger.error(error + e);
			}
			
			try
			{
				if (resultSet != null)
					resultSet.close();
				
			}
			
			catch (SQLException e) 
			{
				logger.error(error + e);
			}
		}
		
		return -1;
	}
}
