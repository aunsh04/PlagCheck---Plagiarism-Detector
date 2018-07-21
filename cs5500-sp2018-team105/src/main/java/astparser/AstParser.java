package astparser;
import org.antlr.v4.runtime.tree.ParseTree;
import java.io.File;
import java.io.IOException;
import pythonparsers.Python3Parser;
/**
 * This interface represents AST parser
 * @author Nirupama
 * citation: antlr4 github
 */
public interface AstParser {
	/**
	 * Parses .py files and returns ParseTree
	 * @param code represents code
	 * @return ParseTree
	 */
	ParseTree parse(String code);
	
	/**
	 * Parses .py files
	 *
	 * @param code represents a string
	 * @return input context for string.
	 */
	Python3Parser.File_inputContext getRuleContext(String code);
	/**
	 * Parses a file and returns the rule context for the parse tree.
	 *
	 * @param represents input file File
	 * @return file input context
	 * @throws exception
	 */
	Python3Parser.File_inputContext getRuleContext(File file) throws IOException;
}
