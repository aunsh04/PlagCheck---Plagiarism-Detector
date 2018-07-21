package dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dao.LoginDao;
import service.LoginService;

/*
 * @author Anirudh
 * Tests for Login
 */
public class LoginDaoTest
{
	
	LoginService service = new LoginService();
	
	/*
	 *  tests for valid credentials of a Professor/TA
	 */
	@Test
	public void validInstructorCredentials() 
	{
		assertEquals(1, LoginDao.authenticate("anirudh", "anirudh"));
		assertEquals(1, service.validateUser("anirudh", "anirudh"));
	}

	/*
	 *  tests for invalid credentials of a Professor/TA
	 */
	@Test
	public void invalidInstructorCredentials() throws Exception
	{
		assertEquals(-1, LoginDao.authenticate("anirudh", "batman"));
		assertEquals(-1, service.validateUser("anirudh", "batman"));
	}
	
	/*
	 *  tests for valid credentials of admin
	 */
	@Test
	public void validAdminLogin()
	{
		assertEquals(1, LoginDao.authenticate("admin", "admin"));
		assertEquals(0, service.validateUser("admin", "admin"));
	}
	
	/*
	 *  tests for invalid credentials of admin
	 */
	@Test
	public void invalidAdminLogin()
	{
		assertEquals(-1, LoginDao.authenticate("admin", "ironman"));
		assertEquals(-1, LoginDao.authenticate("peter", "spiderman"));
		
		assertEquals(-1, service.validateUser("admin", "ironman"));
	}
	
	/*
	 *  test for blank username/password
	 */
	@Test
	public void testNoCredentials()
	{
		assertEquals(-1, LoginDao.authenticate("", ""));
		
		assertEquals(-2, service.validateUser("", ""));
	}
	
}
