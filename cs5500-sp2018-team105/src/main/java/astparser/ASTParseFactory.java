package astparser;

/**
 * Factory Interface for creating AST parser
 * @author Nirupama
 *
 */
public interface ASTParseFactory {
  /**
   * factory method for creating a parser
   * @return ASTParser
   */
  public AstParser createParser();
}
