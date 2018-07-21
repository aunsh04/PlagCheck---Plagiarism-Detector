package model;
/**
 * POJO for User statistics
 * @author Aunsh
 *
 */
public class UserStats {
	
	private int resultsSize;
	private int resultsCounter;
	private double time;
	
	public UserStats() {
		super();
	}
	
	public UserStats(int resultsSize, int resultsCounter, double time) {
		this.resultsSize = resultsSize;
		this.resultsCounter = resultsCounter;
		this.time = time;
	}

	
    /**
     * Returns the number of results generated from the test run
     * 
     * @return resultsSize number of results generated from the test run
     */
	public int getResultsSize() {
		return resultsSize;
	}

	
    /**
     * Sets the number of results generated from the test run
     * 
     * @param resultsSize the number of results generated from the test run
     */
	public void setResultsSize(int resultsSize) {
		this.resultsSize = resultsSize;
	}

	
    /**
     * Returns the number of times test has been run
     * 
     * @return resultsCounter number of times test has been run
     */
	public int getResultsCounter() {
		return resultsCounter;
	}

    /**
     * Sets the number of times test has been run
     * 
     * @param resultsCounter number of times test has been run
     */
	public void setResultsCounter(int resultsCounter) {
		this.resultsCounter = resultsCounter;
	}

	
    /**
     * Returns the time elapsed in a test run
     * 
     * @return time the time elapsed in a test run
     */
	public double getTime() {
		return time;
	}

    /**
     * Sets the time elapsed in a test run
     * 
     * @param time the time elapsed in a test run
     */
	public void setTime(double time) {
		this.time = time;
	}

	
	
}

