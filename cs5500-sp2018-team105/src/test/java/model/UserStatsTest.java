package model;
import static org.junit.Assert.assertEquals;

import org.junit.Test;


/**
 * This is a test class for the UserStats POJO
 * @author  Aunsh Chaudhari
 */
public class UserStatsTest {
	

    /**
     * Checks number of times test has been run in user statistics
     */
	@Test
	public void testCounter() {
		UserStats stats = new UserStats();
		stats.setResultsCounter(10);
		assertEquals(10,stats.getResultsCounter());	
	}
	
	
    /**
     * Checks number of results generated in user statistics
     * 
     */
	@Test
	public void testResultsSize() {
		UserStats stats = new UserStats();
		stats.setResultsSize(70);
		assertEquals(70,stats.getResultsSize());	
	}
    /**
     * Checks time elapsed of user statistics
     * 
     */
	@Test
	public void testTime() {
		UserStats stats = new UserStats();
		stats.setTime(8.5);
		assertEquals(8.5,stats.getTime(),0.01);	
	}
	
	
    /**
     * Checks constructor of UserStats class
     * 
     */
	@Test
	public void testConstructor() {
		UserStats stats = new UserStats(70,10,8.5);
		assertEquals(8.5,stats.getTime(),0.01);
		assertEquals(70,stats.getResultsSize());
		assertEquals(10,stats.getResultsCounter());	
	}
	
}

