package service;

import org.apache.log4j.Logger;

import dao.LoginDao;

/*
 * LoginService for retrieving login details from the database
 * @author Anirudh
 */
public class LoginService 
{
	static Logger logger = Logger.getLogger(LoginService.class);
	
	public LoginService() 
	{
		// empty constructor
	}
	
	/*
	 * @see validateUser(String username, String password), returns int
	 * returns the number of records found in the database and sets the session attributes
	 * @param String username
	 */
	public int validateUser(String uname, String pwd) 
	{
		
		try 
		{
			
			int status = LoginDao.authenticate(uname, pwd);

			// conditions for displaying the appropriate error message (if any) on the login screen
			
			if (uname.equals("") || pwd.equals("")) {
				return -2;
			}
			
			
			else if (uname.equals("admin")&&pwd.equals("admin")) {
				return 0;
				
			}	
			
			else if (status == 1) {
				return 1;
			}
			
			else 
			{
				return -1;
				
			}
		}
		catch (Exception e) 
		{
			logger.error("Error while logging in" + e);
		}
		
		return 0;
	}
}
