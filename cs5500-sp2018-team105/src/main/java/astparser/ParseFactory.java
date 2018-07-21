package astparser;

/**
 * This class has createParser method which returns python parser
 * @author Nirupama
 *
 */
public class ParseFactory implements ASTParseFactory {
	/**
	 * This method returns python parser
	 */
	@Override
    public AstParser createParser() {
        return new PythonParser();
    }

}
