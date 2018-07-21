package model;

/*
 * Model for Instructor, replicates the fields present in the Professor and TA tables in DB
 * @author Anirudh
 */

public class Instructor 
{
	
	public Instructor() {
		
	}
	public Instructor(String fname, String lname, String uname, String pwd, String email) 
	{
		this.firstName = fname;
		this.lastName = lname;
		this.username = uname;
		this.password = pwd;
		this.email = email;
	}

	String firstName;
	String lastName;
	String username;
	String password;
	String email;
	
    /**
     * Returns the first name of the instructor
     * 
     * @return first name of the instructor
     */
	public String getFirstName() 
	{
		return firstName;
	}
	
    /**
     * Sets the first name of the instructor
     * 
     * @param first name of the instructor
     */
	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}
	
    /**
     * Returns the last name of the instructor
     * 
     * @return last name of the instructor
     */
	public String getLastName() 
	{
		return lastName;
	}
	
    /**
     * Sets the last name of the instructor
     * 
     * @param last name of the instructor
     */
	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}
	
    /**
     * Returns the username of the instructor
     * 
     * @return username of the instructor
     */
	public String getUsername() 
	{
		return username;
	}
	
    /**
     * Sets the username of the instructor
     * 
     * @param username username of the instructor
     */
	public void setUsername(String username) 
	{
		this.username = username;
	}
	
	
    /**
     * Returns the password of the instructor
     * 
     * @return password of the instructor
     */
	public String getPassword() 
	{
		return password;
	}
	
	
    /**
     * Sets the password of the instructor
     * 
     * @param password password of the instructor
     */
	public void setPassword(String password) 
	{
		this.password = password;
	}
	
    /**
     * Returns the email of the instructor
     * 
     * @return email of the instructor
     */
	public String getEmail()
	{
		return email;
	}
	
    /**
     * Sets the email of the instructor
     * 
     * @param email email of the instructor
     */
	public void setEmail(String email) 
	{
		this.email = email;
	}
	
}
