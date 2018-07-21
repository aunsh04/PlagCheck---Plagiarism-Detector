package strategies;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.apache.log4j.Logger;

import astparser.PythonParser;
import astparser.TreeTraverse;
import model.PlagiarismResult;
import model.SimilarLines;
import model.Submission;
import pythonparsers.Python3Lexer;
import pythonparsers.Python3Parser;
import strategypattern.ComparisonStrategy;
/**
 * AST Comparison strategy for finding code similarity in homework submissions
 * @author Nirupama
 *
 */
public class ASTStrategy implements ComparisonStrategy{
	static Logger logger = Logger.getLogger(ASTStrategy.class);
	/**
	 * This method compares multiple submissions and generates reports for every two submissions compared
	 * @param list of submissions
	 * @return list of PlagiarismResult 
	 */
	@Override
	public List<PlagiarismResult> getReport(List<Submission> submission)
	{   
		List<PlagiarismResult> result=new ArrayList<>();

		List<Submission> subList=new ArrayList<>(submission);

		for(Submission sub : submission)
		{   
			//Remove sub from other submission list so that same Submission is not compared with itself
			// and number of comparisons are greatly reduced hence increasing efficiency.
			subList.remove(sub);
			for(Submission sub1 : subList)
			{   
				//Check for ignoring files which belong to same student
				if(!sub.getStudentId().equals(sub1.getStudentId()))
				{
					PlagiarismResult res=getSimilarity(sub,sub1);
					result.add(res);
			
				}
			}
		}
		return result;
	}
	/**
	 * This method converts a file to a string	
	 * @param file represents a File
	 * @return a string
	 * @throws IOException
	 */
	public String fileToString(File file) throws IOException
	{
		byte[] encoded = Files.readAllBytes(file.toPath());		
		return new String(encoded, Charset.forName("UTF-8"));
	}
	/**
	 * This method takes a file and gives a map of line numbers and the token on that line
	 * @param file represents a file
	 * @return a map of line numbers and the type of tokens present on that line
	 * @throws IOException
	 */
	public Map<Integer, List<Integer>> getTokenMap(File file) throws IOException
	{
		Python3Lexer lexer = new Python3Lexer(new ANTLRInputStream(fileToString(file)));
		CommonTokenStream tokens = new CommonTokenStream(lexer);	
		tokens.fill();		
		List<Token> tokenList = tokens.getTokens();
		Map<Integer, List<Integer>> tokenMap1=new HashMap<>();
		for(Token t: tokenList)
		{
			if(tokenMap1.containsKey(t.getLine()))
			{
				List<Integer> tokenValue=tokenMap1.get(t.getLine());
				tokenValue.add(t.getType());
				tokenMap1.put(t.getLine(),tokenValue);
			}
			else
			{  
				List<Integer> tokenValue=new ArrayList<>();
				tokenValue.add(t.getType());
				tokenMap1.put(t.getLine(),tokenValue);

			}
		}
		return tokenMap1;
	}
	/**
	 * This method compares token line by line in two files
	 * @param tokenMap1 token map of file 1
	 * @param tokenMap2 token map of file 2
	 * @return list of similar lines in both files
	 */
	public List<Set<Integer>> compareTokenMaps(Map<Integer, List<Integer>> tokenMap1,Map<Integer, List<Integer>> tokenMap2)
	{
		Set<Integer> lineFile1=new HashSet<>();
		Set<Integer> lineFile2=new HashSet<>();
		List<Set<Integer>> result=new ArrayList<>();
		for(Map.Entry<Integer,List<Integer>> entry1:tokenMap1.entrySet())
		{
			for(Map.Entry<Integer,List<Integer>> entry2:tokenMap2.entrySet())
			{   
				if(entry1.getValue().equals(entry2.getValue()))
				{
					lineFile1.add(entry1.getKey());
					lineFile2.add(entry2.getKey());
				}
			}
		}
		result.add(lineFile1);
		result.add(lineFile2);
		return result;
	}
	/**
	 * This method compares two submissions at a time
	 * @param submission contains a file, studentId and hwId
	 * @param submission submission contains a file, studentId and hwId
	 * @return PlagiarismResult which contains plagiarism percentage of both files which are compared,along with studentId and hwId of both submissions
	 */
	public PlagiarismResult getSimilarity(Submission sub1, Submission sub2) 
	{   
		String ast1="";
		String ast2="";
		PlagiarismResult result;
		SimilarLines lines=null;
		TreeTraverse treeTraverse=new TreeTraverse();
		PythonParser pythonParser = new PythonParser();
		List<Double> percentages;
		double percentage1=0;
		double percentage2=0;
		if(sub1.getFile()!=null && sub2.getFile()!=null)
		{
			try
			{   
				// asts which are a string of rule nodes
				//for String ast creation using rule nodes refer to astparser package files
				//src/main/java/astparser

				ast1=treeTraverse.getAst(pythonParser.getRuleContext(sub1.getFile())).toString();
				ast2=treeTraverse.getAst(pythonParser.getRuleContext(sub2.getFile())).toString();


				//getting similar line numbers in two files
				Map<Integer, List<Integer>> tokenMap1=getTokenMap(sub1.getFile());
				Map<Integer, List<Integer>> tokenMap2=getTokenMap(sub2.getFile());
				List<Set<Integer>> lineList=compareTokenMaps(tokenMap1,tokenMap2);

				List<Integer> lines1=new ArrayList<>(lineList.get(0));
				List<Integer> lines2=new ArrayList<>(lineList.get(1));
				lines=new SimilarLines(lines1,lines2);

			}
			catch (Exception e) {
				logger.error("ast", e);
			}

			//compare asts if they are not empty
			percentages=calculatePercentage(ast1,ast2);
			percentage1=percentages.get(0);
			percentage2=percentages.get(1);
		}

		result=new PlagiarismResult(sub1.getStudentId(), sub2.getStudentId(), sub1.getHwId(), sub1.getFile(), sub2.getFile(),
				percentage1, percentage2,lines);
		return result;
	}
	/**
	 * This method returnspercentage of plagiarism in two files
	 * @param ast1 String ast tree of first file
	 * @param ast2 String ast tree of second file
	 * @return  plagiarism percentage list of two files
	 */
	public List<Double> calculatePercentage(String ast1,String ast2)
	{

		List<Double> percentage=new ArrayList<>();
		double percentage1=0;
		double percentage2=0;
		if(!ast1.equals("") && !ast2.equals(""))
		{
			List<List<String>> tree1=createTreeWithNodes(ast1);

			List<List<String>> tree2=createTreeWithNodes(ast2);
			String[] astString1=ast1.split(" +");
			String[] astString2=ast2.split(" +");
			int commonSubTrees=0;
			List<List<String>> commonTrees=new ArrayList<>();
			// find subtree of first ast in second ast and increase the count if it is present
			for(List<String> s:tree1)
			{
				for(List<String> s1:tree2)
				{
					if(s1.equals(s))
					{   
						commonTrees.add(s1);
						break;
					}
				}
			}

			for(List<String> commonNodes : commonTrees)
			{
				commonSubTrees+=commonNodes.size();
			}
			// calculate percentage by dividing total number of nodes of an ast by number
			// of common nodes
			percentage1=((double)commonSubTrees*100)/astString1.length;
			percentage2=((double)commonSubTrees*100)/astString2.length;
			percentage.add(percentage1);
			percentage.add(percentage2);
		}
		return percentage;
	}

	/**
	 * Method to classify subtrees on the basis of spaces in ast of RuleContext
	 * Current ast structure "parent child1  child2   child3  parent2"
	 * Here spaces are increased for each child of parent and if some parent node comes then space is less than previous space
	 * using above property to create subtrees list
	 * @param ast1 a string ast with rulenodes
	 * @return list of list of subtrees in an ast tree
	 */
	public List<List<String>> createTreeWithNodes(String ast1)
	{
		int spaceCount=0;
		int previousSpaceCount=0;
		List<String> subTrees=new ArrayList<>();
		List<List<String>> result=new ArrayList<>();
		StringBuilder s=new StringBuilder();
		for (char c : ast1.toCharArray()) {
			if (c == ' ') {
				// if character with space comes then add string of rulenode to subtree
				if(s.length()>0)
				{
					subTrees.add(s.toString());
				}
				//increase spaceCount and set string to empty
				spaceCount++;
				s=new StringBuilder();
			}
			else
			{   
				//condition for finding a parent, create new list for new subtree
				if(spaceCount>0 && spaceCount<previousSpaceCount)
				{
					result.add(subTrees);
					subTrees=new ArrayList<>();						
				}
				if(spaceCount>0)
					previousSpaceCount=spaceCount;
				s.append(c);					
				spaceCount=0;					
			}
		}
		return result;
	}
}









