package astparser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import pythonparsers.Python3Lexer;
import pythonparsers.Python3Parser;
import strategies.AuthorStrategy;
/**
 * Antlr4 Python Parser
 * @author Nirupama
 * citation: antlr github
 */
public class PythonParser implements AstParser {
	
	static Logger logger = Logger.getLogger(PythonParser.class);
	
	/**
	 * This method parses python code and returns the parse tree.
	 * @param code represents a string
	 * @return ParseTree
	 */
	@Override
	public ParseTree parse(String code) {
		Python3Lexer lexer = new Python3Lexer(new ANTLRInputStream(code));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		Python3Parser parser = new Python3Parser(tokens);
		return parser.file_input().getChild(0);
	}
	/**
	 * This method parses a file and returns the parse tree.
	 * @param file represents file input
	 * @return parse tree.
	 * @throws IOException if reading file fails.
	 */
	@Override
	public Python3Parser.File_inputContext getRuleContext(File file) throws IOException {
		byte[] encoded = Files.readAllBytes(file.toPath());
		String code = new String(encoded, Charset.forName("UTF-8"));
		return getRuleContext(code);
	}
	/**
	 * Parses python code and returns the parse tree.
	 * @param code
	 * @return parse tree.
	 */
	@Override
	public Python3Parser.File_inputContext getRuleContext(String code) {
		Python3Lexer lexer = new Python3Lexer(new ANTLRInputStream(code));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		Python3Parser parser = new Python3Parser(tokens);
		return parser.file_input();
	}
	
}
