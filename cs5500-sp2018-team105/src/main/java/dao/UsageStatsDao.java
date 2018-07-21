package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import connection.DBConnection;
import model.UserStats;

/*
 * UsageStatsDao for updating user statistics of system in database
 * @author Aunsh
 */
public class UsageStatsDao {
	static Logger logger = Logger.getLogger(UsageStatsDao.class);
	
	
	/*
	 * updating user statistics of system in database with JDBC
	 * @param resultsSize number of results generated from test run
	 * @param resultsCounter number of times test has been run
	 * @param time time taken by system to run strategy for comparison
	 * @return number of entries updated in database
	 */
	public int updateStats(UserStats stats) {
		Connection con = DBConnection.connect();
		PreparedStatement statement = null;
		int results = 0;
		try {
			String sql = "UPDATE UsageStats SET results=?,counter=?,time=? WHERE usageId = ?";
			statement = con.prepareStatement(sql);
			statement.setInt(1, stats.getResultsSize());
			statement.setInt(2, stats.getResultsCounter());
			statement.setDouble(3, stats.getTime());
			statement.setInt(4, 1);
			results = statement.executeUpdate();
		} catch (SQLException e) {
			logger.error("Not able to update stats" + e);
		} finally {
			try 
			{
				if (statement != null)
					statement.close();
			} 
			
			catch (SQLException e) 
			{
				logger.error("Cannot close statement" + e);
			}
			
			try
			{
				
				con.close();
				
			}
			
			catch (SQLException e) 
			{
				logger.error("Cannot close connection" + e);
			}
		}	
		return results;
	}
	
	/*
	 * returning 
	 * @param resultsSize number of results generated from test run
	 * @param resultsCounter number of times test has been run
	 * @param time time taken by system to run strategy for comparison
	 * @return number of entries updated in database
	 */
	public UserStats getUserStats() {
		UserStats stats = null;
		Connection connection = DBConnection.connect();
		Statement statement = null;
		ResultSet results = null;
		try {
			String sql = "SELECT * FROM UsageStats";
			statement = connection.createStatement();
			results = statement.executeQuery(sql);
			if(results.next()) {
				int resultsSize= results.getInt("results");
				int resultsCounter = results.getInt("counter");
				double time = results.getDouble("time");
				stats = new UserStats(resultsSize,resultsCounter,time);
			}
		}  catch (SQLException e) {
			logger.error("Not able to get stats" + e);
			
		} finally {
			try 
			{
				if (statement != null)
					statement.close();
				
			} 
			
			catch (SQLException e) 
			{
				logger.error("Cannot close statement" + e);
			}
			
			try {
				if(results!=null) 
					results.close();
			}
			catch (SQLException e) 
			{
				logger.error("Cannot close result set" + e);
			}
			try
			{
				
				connection.close();
				
			}
			
			catch (SQLException e) 
			{
				logger.error("Cannot close connection" + e);
			}
		}
		return stats;
	}
	
	
	
	
}
