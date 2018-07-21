package service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dao.UsageStatsDao;
import model.UserStats;

/*
 * Test for UsageStatsService for updating user statistics of system
 * @author Aunsh
 */
public class UsageStatsServiceTest {
	
	/*
	 *  tests for valid database connection and retrieval of user statistics of system
	 *  for UserStatsService
	 */
	@Test
	public void setUpConnectionAndGetStats()
	{
		UsageStatsDao dao = new UsageStatsDao();
		dao.updateStats(new UserStats(10,10,8));
		UsageStatsService usageStats = new UsageStatsService(dao);
		usageStats.updateUsageStats(10, 8);
		UserStats stats = dao.getUserStats();
		assertTrue(stats.getResultsCounter() > 0);
	}

}
