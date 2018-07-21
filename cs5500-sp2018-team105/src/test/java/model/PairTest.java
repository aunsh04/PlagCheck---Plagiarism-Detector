package model;
import static org.junit.Assert.assertEquals;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


/**
 * This is a test class for the Pair POJO
 * @author  Aunsh Chaudhari
 */
public class PairTest {
	

    /**
     * Checks number of times test has been run in user statistics
     */
	@Test
	public void testStart() {
		Pair pair = new Pair();
		pair.setStartIndex(10);
		assertEquals(10,pair.getStartIndex());
	}
	
	
    /**
     * Checks number of times test has been run in user statistics
     */
	@Test
	public void testEnd() {
		Pair pair = new Pair();
		List<Integer> list = new ArrayList<>();
		list.add(5);
		pair.setEndIndex(list);
		assertEquals("[5]",pair.getEndIndex().toString());
	}
   
	
    /**
     * Checks constructor of UserStats class
     * 
     */
	@Test
	public void testConstructor() {
		List<Integer> list = new ArrayList<>();
		list.add(5);
		Pair pair = new Pair(10,list);
		assertEquals(10,pair.getStartIndex());
	}
	
}

