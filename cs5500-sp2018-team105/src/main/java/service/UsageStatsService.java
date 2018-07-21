package service;

import org.apache.log4j.Logger;

import dao.UsageStatsDao;
import model.UserStats;

/*
 * UsageStatsService for updating user statistics of system
 * @author Aunsh
 */
public class UsageStatsService {

	static Logger logger = Logger.getLogger(UsageStatsService.class);
	static int resultsCounter=0;
	private UsageStatsDao usageDao;
	
	public UsageStatsService(UsageStatsDao usageDao) {
		this.usageDao = usageDao;
	}
	
	/*
	 * updating user statistics of system
	 * @param resultsSize the number of results generated from test run
	 * @param time the time taken for test to run (in seconds)
	 * @return resultsCounter the number of times test has been run
	 */
	public void updateUsageStats(int resultsSize,double time) {
		resultsCounter++;
		UserStats stats = new UserStats(resultsSize, resultsCounter,time);
		usageDao.updateStats(stats);
	}
	
	
}
