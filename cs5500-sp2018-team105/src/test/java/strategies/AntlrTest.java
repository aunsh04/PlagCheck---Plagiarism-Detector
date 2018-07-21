package strategies;

import static org.junit.Assert.*;

import org.junit.Test;

import astparser.ASTParseFactory;
import astparser.AstParser;
import astparser.ParseFactory;
import astparser.TreeTraverse;
import pythonparsers.Python3Parser;

import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import java.io.File;
import java.io.IOException;


import static org.junit.Assert.assertEquals;

/**
 * @author Nirupama
 *
 */
public class AntlrTest {
	private ASTParseFactory factory=new ParseFactory();
	private TreeTraverse treeTraverse=new TreeTraverse();
	
	
	/**
	 * Test case for empty file
	 *
	 * @throws IOException if file not found
	 */
	@Test
	public void testEmptyFile() throws IOException {
		AstParser pythonParser = factory.createParser();
		TreeTraverse astPrinter = new TreeTraverse();
		Python3Parser.File_inputContext tree = pythonParser.getRuleContext
				(new File("src/main/resources/add-strings.py"));
		assertEquals("file_input", astPrinter.getAst(tree).toString());
	}
	
	/**
	 * Testing python parser with normal string code
	 */
	@Test
	public void testParserString() {
		AstParser pythonParser = factory.createParser();
		ParseTree tree = pythonParser.parse("x =7 * 8 * 99");
		assertEquals("x=7*8*99", treeTraverse.getTree(tree));
		tree = pythonParser.parse("a == b");
		assertEquals("a==b", treeTraverse.getTree(tree));
	}

	
	/**
	 * Test case for printing Ast tree for python file with source code
	 *
	 * @throws IOException 
	 */
	@Test
	public void testAstString() throws IOException {
		AstParser pythonParser = factory.createParser();
		Python3Parser.File_inputContext tree = pythonParser
				.getRuleContext(new File("src/main/resources/3sum.py"));
		assertEquals("file_input simple_stmt  import_name   dotted_name classdef  atom  suite   funcdef    parameters     typedargslist      tfpdef      tfpdef    suite     simple_stmt      str     simple_stmt      expr_stmt       testlist_star_expr        atom        atom        atom       testlist_star_expr        power         atom         trailer          atom        atom        integer     while_stmt      comparison       atom       comp_op       arith_expr        power         atom         trailer          atom        integer      suite       if_stmt        or_test         comparison          atom          comp_op          integer         comparison          power           atom           trailer            atom          comp_op          power           atom           trailer            arith_expr             atom             integer        suite         simple_stmt          expr_stmt           testlist_star_expr            atom            atom           testlist_star_expr            arith_expr             atom             integer            arith_expr             power              atom              trailer               atom             integer         while_stmt          comparison           atom           comp_op           atom          suite           if_stmt            comparison             arith_expr              power               atom               trailer                atom              power               atom               trailer                atom              power               atom               trailer                atom             comp_op             integer            suite             simple_stmt              expr_stmt               atom               augassign               integer            comparison             arith_expr              power               atom               trailer                atom              power               atom               trailer                atom              power               atom               trailer                atom             comp_op             integer            suite             simple_stmt              expr_stmt               atom               augassign               integer            suite             simple_stmt              power               atom               trailer               trailer                atom                 testlist_comp                  power                   atom                   trailer                    atom                  power                   atom                   trailer                    atom                  power                   atom                   trailer                    atom             simple_stmt              expr_stmt               testlist_star_expr                atom                atom               testlist_star_expr                arith_expr                 atom                 integer                arith_expr                 atom                 integer             while_stmt              and_test               comparison                atom                comp_op                atom               comparison                power                 atom                 trailer                  atom                comp_op                power                 atom                 trailer                  arith_expr                   atom                   integer              suite               simple_stmt                expr_stmt                 atom                 augassign                 integer             while_stmt              and_test               comparison                atom                comp_op                atom               comparison                power                 atom                 trailer                  atom                comp_op                power                 atom                 trailer                  arith_expr                   atom                   integer              suite               simple_stmt                expr_stmt                 atom                 augassign                 integer       simple_stmt        expr_stmt         atom         augassign         integer     simple_stmt      return_stmt       atom   funcdef    parameters     typedargslist      tfpdef      tfpdef    suite     simple_stmt      str     simple_stmt      expr_stmt       atom       power        atom        trailer        trailer         atom     simple_stmt      expr_stmt       atom       atom        testlist_comp         power          atom          trailer           integer         comp_for          atom          power           atom           trailer           trailer          comp_if           comparison            power             atom             trailer              integer            comp_op            integer     simple_stmt      expr_stmt       atom       power        atom        trailer         atom          testlist_comp           power            atom            trailer             integer           comp_for            atom            power             atom             trailer             trailer     simple_stmt      expr_stmt       atom       test        atom         atom          testlist_comp           integer           integer           integer        comparison         power          atom          trailer           integer         comp_op         integer        atom     for_stmt      exprlist       atom       atom      power       atom       trailer        atom      suite       if_stmt        comparison         atom         comp_op         integer        suite         simple_stmt          expr_stmt           atom           power            atom            trailer             subscript              arith_expr               atom               integer         for_stmt          exprlist           atom           atom          power           atom           trailer            atom          suite           if_stmt            and_test             comparison              arith_expr               integer               atom               atom              comp_op              atom               testlist_comp                atom                atom             comparison              arith_expr               integer               atom               atom              comp_op              atom            suite             if_stmt              comparison               power                atom                trailer                 atom                  testlist_comp                   atom                   atom                   arith_expr                    integer                    atom                    atom               comp_op               atom              suite               simple_stmt                power                 atom                 trailer                 trailer                  power                   atom                   trailer                    atom                     testlist_comp                      atom                      atom                      arith_expr                       integer                       atom                       atom           if_stmt            and_test             comparison              arith_expr               integer               atom               atom              comp_op              atom               testlist_comp                atom                atom             comparison              arith_expr               integer               atom               atom              comp_op              atom            suite             if_stmt              comparison               power                atom                trailer                 atom                  testlist_comp                   atom                   atom                   arith_expr                    integer                    atom                    atom               comp_op               atom              suite               simple_stmt                power                 atom                 trailer                 trailer                  power                   atom                   trailer                    atom                     testlist_comp                      atom                      atom                      arith_expr                       integer                       atom                       atom     simple_stmt      return_stmt       atom if_stmt  comparison   atom   comp_op   str  suite   simple_stmt    expr_stmt     atom     power      atom      trailer      trailer      trailer       atom        testlist_comp         factor          integer         integer         integer         integer         factor          integer         factor          integer   atom   simple_stmt    atom", treeTraverse.getAst(tree).toString());
	}

	/**
	 * Test case for printing python code string
	 *
	 * @throws IOException on file not found
	 */
	@Test
	public void testSimpleString() {
		AstParser pythonParser = factory.createParser();
		Python3Parser.File_inputContext tree = pythonParser.getRuleContext("print ('MSD')");
		assertEquals("file_input power  atom  trailer   str", treeTraverse.getAst(tree).toString());
	}

}
