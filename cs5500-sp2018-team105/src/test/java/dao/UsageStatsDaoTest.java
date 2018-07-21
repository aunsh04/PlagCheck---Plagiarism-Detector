
package dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.UserStats;

/*
 * @author Aunsh
 * Tests for the User Statistics
 */
public class UsageStatsDaoTest {
	
	/*
	 *  tests for valid database connection and retrieval of user statistics of system
	 */
	@Test
	public void setUpConnectionAndGetStats()
	{
		UsageStatsDao dao = new UsageStatsDao();
		dao.updateStats(new UserStats(10,10,8));
		UserStats stats = dao.getUserStats();
		assertEquals(10,stats.getResultsSize());
		assertEquals(8,stats.getTime(),0.01);
	}
	
}
