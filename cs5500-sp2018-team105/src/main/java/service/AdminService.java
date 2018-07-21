package service;

import java.util.List;

import dao.AdminDao;
import model.Instructor;

/*
 * @author Anirudh
 * AdminService for setting up an instructor
 * invokes the dao method for inserting instructor info into the database
 */
public class AdminService 
{
	/*
	 * @see invalid(String, String String, String, String)
	 * @return boolean
	 * @param String 
	 * @param String
	 * @param String
	 * @param String
	 * @param String
	 * checks if all the provided details are valid or not
	 */
	public boolean invalidForm(String fname, String lname, String uname, String pwd, String email)
	{
		return (fname.isEmpty() || lname.isEmpty() || uname.isEmpty() || pwd.isEmpty() || email.isEmpty()); 
	}
	
	/*
	 * @see invalid(String, String String, String)
	 * @return boolean
	 * @param String 
	 * @param String
	 * @param String
	 * @param String
	 * checks if all the provided details except username are valid or not
	 */
	public boolean invalidForm(String fname, String lname, String pwd, String email)
	{
		return (fname.isEmpty() || lname.isEmpty() || pwd.isEmpty() || email.isEmpty()); 
	}
	
	/*
	 * @see setUpInstructor(Instructor, int)
	 * @param Instructor
	 * @param int
	 * returns an integer - the number of records updated in the database
	 */
	public int setUpInstructor(Instructor inst, int flag)
	{
		return AdminDao.setUpInstructor(inst, flag);
	}
	
	
	/*
	 * @see getUsers(int)
	 * @param int
	 * returns a list of all the users
	 */
	public List<String> getUsers(int flag)
	{
		return AdminDao.getUsers(flag);
	}
}
