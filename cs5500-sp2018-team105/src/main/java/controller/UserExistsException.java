package controller;


/**
 * Custom Exception for Username Existing in Database
 * @author Aunsh
 */
public class UserExistsException extends Exception 
{
	
	/**
	 * Custom Exception for Username Existing in Database
	 */
	private static final long serialVersionUID = 1L;


    /**
     * Exception Specific to Username already existing
     *
     * @param message description of the exception
     */
	public UserExistsException(String message) 
	{
		super(message);
	}

}
