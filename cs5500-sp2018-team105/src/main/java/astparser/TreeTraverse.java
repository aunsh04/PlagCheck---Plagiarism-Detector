package astparser;

import pythonparsers.Python3Parser;
import strategies.AuthorStrategy;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.log4j.Logger;
/**
 * This class generates an AST by parsing it
 * @author Nirupama
 *  citation: antlr4 github
 */
public class TreeTraverse {
	
	static Logger logger = Logger.getLogger(TreeTraverse.class);
	private boolean ignoreFiles = true;
	/**
	 * This method prints parsetree
	 *
	 * @param ctx  rule context
	 * @return String representation of parse tree
	 */
	public StringBuilder getAst(RuleContext ctx) {
		return getAst(ctx, 0, new StringBuilder());
	}
	
	/**
	 * 
	 * @param tree represents a ParseTree
	 * @return a String representation of tree
	 */
	public String getTree(ParseTree tree) {
		return tree.getText();
	}
	/**
	 * This method prints string representation of tree
	 *
	 * @param ctx    rule context
	 * @param indent depth indicator
	 * @param output represents tree as string
	 * @return String represents tree as string
	 */
	public StringBuilder getAst(RuleContext ctx, int indent, StringBuilder resultTree) {
		boolean toBeIgnored = ignoreFiles
				&& ctx.getChildCount() == 1
				&& ctx.getChild(0) instanceof ParserRuleContext;
		if (!toBeIgnored) {
			String ruleName = Python3Parser.ruleNames[ctx.getRuleIndex()];
			for (int i = 0; i < indent; i++) {
				resultTree.append(" ");
			}
			resultTree.append(ruleName);
		}
		for (int i = 0; i < ctx.getChildCount(); i++) {
			ParseTree element = ctx.getChild(i);
			if (element instanceof RuleContext) {
				getAst((RuleContext) element, indent + (toBeIgnored ? 0 : 1), resultTree);
			}
		}
		return resultTree;
	}
	
}

