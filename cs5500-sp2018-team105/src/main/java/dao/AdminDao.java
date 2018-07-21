package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import connection.DBConnection;
import model.Instructor;

/*
 * AdminDao class acts as an interface between the Servlet and the DB
 * when the admin sets up the accounts of Professor or TA's
 * @author Anirudh
 */

public class AdminDao 
{
	static Logger logger = Logger.getLogger(AdminDao.class);
	
	/*
	 * @see setUpInstructor(Instructor, int)
	 * @param Instructor
	 * @param int
	 * returns an integer - the number of records updated in the database
	 */
	public static int setUpInstructor(Instructor inst, int flag)
	{

		Connection con = DBConnection.connect();
		PreparedStatement statement = null;
		PreparedStatement loginStatement = null;

		int result = 0;

		// setting up db connection
		try 
		{
			
			// inserts the Instructor's details in the DB
			String prof = "INSERT INTO Professor (firstName, lastName, username, password, email) VALUES (?,?,?,?,?)";
			String tA = "INSERT INTO TeachingAssistant (firstName, lastName, username, password, email) VALUES (?,?,?,?,?)";
			String login = "INSERT INTO Login (username, password) VALUES (?,?)";

			loginStatement = con.prepareStatement(login);

			loginStatement.setString(1, inst.getUsername());
			loginStatement.setString(2, inst.getPassword());

			if (flag == 0)
			{
				statement = con.prepareStatement(prof);
				result = insert(statement, inst);
				loginStatement.executeUpdate();
			}

			else
			{
				statement = con.prepareStatement(tA);
				result = insert(statement, inst);
				loginStatement.executeUpdate();
			}


		}
		
		catch(SQLException e)
		{
			logger.error("SQL exception, check query:" + e);
		}

		catch (Exception e) 
		{
			logger.error("Exception, cannot insert instructor:" + e);
		}

		finally 
		{
			try 
			{
				if (loginStatement!=null)
					loginStatement.close();

				if (statement != null)
					statement.close();

				con.close();

			} 

			catch (SQLException e) 
			{
				logger.error("SQL error:" + e);
			}
		}

		return result;

	}

	
	/*
	 * @see insert(Statement, Instructor)
	 * @param Statement
	 * @param Instructor
	 * returns an integer - the number of records updated in the database
	 */
	public static int insert(PreparedStatement statement, Instructor inst)
	{

		int result = 0;
		
		try
		{
			statement.setString(1, inst.getFirstName());
			statement.setString(2, inst.getLastName());
			statement.setString(3, inst.getUsername());
			statement.setString(4, inst.getPassword());
			statement.setString(5, inst.getEmail());

			result = statement.executeUpdate();

		}

		catch (SQLException e) 
		{
			logger.error("SQL exception, check query:" + e);
		}

		return result;
	}
	
	/*
	 * @see getUsers(int)
	 * @param int
	 * returns a list of all the users
	 */
	public static List<String> getUsers(int flag)
	{
		
		Connection con = DBConnection.connect();
		Statement getUsers = null;
		List<String> userList = new ArrayList<>();
		ResultSet userResults = null;
		
		try 
		{
			
			if (flag == 0)
			{
				
				String prof = "SELECT username FROM Professor";
				getUsers = con.createStatement();
				
				userResults = getUsers.executeQuery(prof);
				
				while(userResults.next())
				{
					userList.add(userResults.getString("username"));
				}
					
			}
			
			else 
			{
				String TA = "SELECT username FROM TeachingAssistant";
				getUsers = con.createStatement();
				
				userResults = getUsers.executeQuery(TA);
				
				while(userResults.next())
					userList.add(userResults.getString("username"));
			}
			
		}
		
		catch (Exception e) 
		{
			logger.error("Error getting all users " + e);
		}
		finally 
		{
			try 
			{
				if (getUsers != null)
					getUsers.close();
			} 
			
			catch (SQLException e) 
			{
				logger.error("SQL error:"+ e);
			}
			
			try
			{
				if (userResults != null)
					userResults.close();
				
			}
			
			catch (SQLException e) 
			{
				logger.error("SQL error:" + e);
			}
		}
		
		return userList;
	}

}