package strategies;


/**
 * Custom Exception for Files not present at path/destination
 * @author Aunsh
 */
public class NoFilesPresentException extends Exception {

	private static final long serialVersionUID = 1L;

	
	
    /**
     * Exception Specific to file not present at path/destination
     *
     * @param message description of the exception
     */
	public NoFilesPresentException(String message) {
		super(message);
	}

}
