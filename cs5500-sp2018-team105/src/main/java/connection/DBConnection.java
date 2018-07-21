package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;


/**
 * Class for connection to database
 * @author Anirudh
 */
public class DBConnection 
{
	
	static Logger logger = Logger.getLogger(DBConnection.class);

	private static final String URL = "jdbc:mysql://cs5200-spring2018-upadhyayula.c2pummxb805k.us-east-2.rds.amazonaws.com/cs5200_spring2018_upadhyayula";
	private static String key = "anirudhuns";
	private static String value = "anirudh1794";
	
	static Connection conn = null;
	
	/**
	 * connects to database using database url, username and password
	 * 
	 * @return Connection with help of credentials
	 */
	public static Connection connect()
	{
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, key, value);
		}
		catch (SQLException e) 
		{
			logger.error("SQL Error" + e);
		}
		catch (Exception e) 
		{
			logger.error("Exception" + e);
		}
		
		return conn;
	}
}
