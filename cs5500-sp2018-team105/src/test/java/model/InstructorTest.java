package model;
import static org.junit.Assert.assertEquals;


import org.junit.Test;


/**
 * This is a test class for the Instructor POJO
 * @author  Aunsh Chaudhari
 */
public class InstructorTest {
	

	
    /**
     * Checks first name of instructor through setter and getter
     * 
     */
	@Test
	public void testFirstName() {
		Instructor i = new Instructor();
		i.setFirstName("anirudh");
		assertEquals("anirudh",i.getFirstName());
    	
	}
	
    /**
     * Checks last name of instructor through setter and getter
     * 
     */
	@Test
	public void testLastName() {
		Instructor i = new Instructor();
		i.setLastName("shah");
		assertEquals("shah",i.getLastName());
    	
	}
	
    /**
     * Checks username of instructor through setter and getter
     * 
     */
	@Test
	public void testUserName() {
		Instructor i = new Instructor();
		i.setUsername("bob");
		assertEquals("bob",i.getUsername());
    	
	}
	
    /**
     * Checks password of instructor through setter and getter
     * 
     */
	@Test
	public void testPassword() {
		Instructor i = new Instructor();
		i.setPassword("cricket");
		assertEquals("cricket",i.getPassword());
    	
	}
	
    /**
     * Checks email of instructor through setter and getter
     * 
     */
	@Test
	public void testEmail() {
		Instructor i = new Instructor();
		i.setEmail("a1994@gmail.com");
		assertEquals("a1994@gmail.com",i.getEmail());
    	
	}
	
    /**
     * Checks constructor of instructor
     * 
     */
	@Test
	public void testConstructor() {
		Instructor i = new Instructor("anirudh","shah","bob","cricket","a1994@gmail.com");
		assertEquals("a1994@gmail.com",i.getEmail());
    	
	}
}

