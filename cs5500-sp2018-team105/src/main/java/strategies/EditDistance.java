package strategies;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import model.Pair;
import model.PlagiarismResult;
import model.SimilarLines;
import model.Submission;
import strategypattern.ComparisonStrategy;


/**
 * This class implements the Edit Distance algorithm to calculate the number of edits
 * required to change one file to another
 * This is a string comparison strategy that can help in comparing the code of two
 * code files
 *
 * @author  Aunsh Chaudhari
 */
public class EditDistance implements ComparisonStrategy{
	
	static Logger logger = Logger.getLogger(EditDistance.class);
	
	
	/**
     * Processes the list of submissions passed 
     * and moves ahead to compute plagiarism percent in both files
     * @param list of submissions - each submission is an object with student id, hw id and the file
     * @return list of plagiarism cases - each case is an object with the 
     * plagiarism percentages, files, student ids
     */
	@Override
	public List<PlagiarismResult> getReport(List<Submission> submissionList) {
		List<PlagiarismResult> results = new ArrayList<>();
		try {
		if (submissionList.isEmpty()) {
			throw new NoFilesPresentException("Empty, please check if files present in destination directory");
		}
		for(int i=0;i<submissionList.size()-1;i++) {
			Submission firstSubmission = submissionList.get(i);
			String firstStudentId = firstSubmission.getStudentId();
			for(int j=i+1;j<submissionList.size();j++) {
				Submission secondSubmission = submissionList.get(j);
				String secondStudentId = secondSubmission.getStudentId();
				if (!firstStudentId.equals(secondStudentId)) {
					PlagiarismResult result = getPlagiarismResults(firstSubmission, secondSubmission);
					results.add(result);
				}
			}
		}
	}

	catch(NullPointerException ne) {
		logger.error("Wrong path or destination of files:"+ne );
	}
	catch(NoFilesPresentException npe) {
		logger.error("Exception in processing files:"+npe);
	}	
		return results;
}	
	
	
	/**
     * Processes the two submissions passed
     * and moves ahead to compute plagiarism percent in both files
     * It applies the first Author strategy check - if the authors match, returns the case with 100%
     * @param firstSubmission - each submission is an object with student id, hw id and the file
     * @param secondSubmission - each submission is an object with student id, hw id and the file
     * @return PlagiarismResult object after check on authors in each file
     */
	public PlagiarismResult getPlagiarismResults(Submission firstSubmission, Submission secondSubmission) {
		AuthorStrategy authorStrategy = new AuthorStrategy();
		List<Integer> lines=new ArrayList<>();
		SimilarLines similarLines=new SimilarLines(lines,lines);
		if (!authorStrategy.checkIfSameAuthor(firstSubmission, secondSubmission)) {
			String[] firstFileArray = getStringArray(firstSubmission.getFile());
			String[] secondFileArray = getStringArray(secondSubmission.getFile());
			Pair<Integer,List<Integer>> similarityMetrics = checkSimilarity(firstFileArray, secondFileArray);
			int similarLineCount = (int) similarityMetrics.getStartIndex();
			List<Integer> lineList = similarityMetrics.getEndIndex();
			similarLines = new SimilarLines(lineList,lineList);
			return computeSimilarityStats(firstFileArray.length,secondFileArray.length,
										similarLineCount,firstSubmission.getFile(),secondSubmission.getFile(),
										firstSubmission.getStudentId(),secondSubmission.getStudentId(),firstSubmission.getHwId(),
										similarLines);
		}
		return new PlagiarismResult(firstSubmission.getStudentId(),secondSubmission.getStudentId(),
				firstSubmission.getHwId(),firstSubmission.getFile(),secondSubmission.getFile(),100.0,100.0, similarLines);
	}
	
	
	/**
     * Returns an array of the lines of the code file as strings
     *
     * @param file code file
     * @return stringArray array of lines of the code in the file
     */
	public String[] getStringArray(File file) {
		String [] stringArray = null;
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			List<String> lines = new ArrayList<>();
			String line = bufferedReader.readLine();
			while (line != null) {
				if (!line.trim().isEmpty()) {
					lines.add(line);
				}
				line = bufferedReader.readLine();
			}
			stringArray = lines.toArray(new String[] {});
		} catch (FileNotFoundException e) {
			logger.error("File not found:"+e);
		} catch (IOException e) {
			logger.error("Cannot read file:"+e);
		}
		return stringArray;
	}
	

	/**
     * Checks for similarity between the code of the two files
     * and returns the count of similiar lines of code
     * @param file1 array of strings representing first file
     * @param file2 array of strings representing second file
     * @return similarLinesCount number of lines of code that are similar/possibly plagiarized
     * 			keeping a threshold of 5 characters as the edit distance
     */
	public Pair<Integer,List<Integer>> checkSimilarity(String file1[], String file2[]) {
		int i = 0;
		int similarLinesCount=0;
		double threshold = 5;
		List<Integer> lineNumbers = new ArrayList<>();
		while (i<file1.length && i<file2.length) {
		String line1 = file1[i];
		String line2 = file2[i];
		int lDistance = getEditDistance(line1,line2);
		if (lDistance < threshold) {
			lineNumbers.add(i+1);
			similarLinesCount++;
			}
		i++;
		}
		return new Pair<Integer, List<Integer>>(similarLinesCount,lineNumbers);	
	}
	

	
	/**
     * Computes similarity between the code of the two files
     *
     * @param file1Length length of first file
     * @param file1Length length of first file
     * @param similarLinesCount number of similar lines between two files
     * @param firstFile first file involved in comparison
     * @param secondFile second file involved in comparison
     * @param student1Id ID of first student whose file is involved in comparison
     * @param student2Id ID of second student whose file is involved in comparison
     * @param hwId ID of homework currently being checked for plagiarism
     * @return PlagiarismResult object that has the plagiarism percentages, student ids and files
     * 
     */
	public PlagiarismResult computeSimilarityStats( int file1Length, int file2Length, 
			int similarLinesCount,File firstFile, File secondFile, String student1Id, String student2Id,String hwId,
			SimilarLines similarLines)  {
		double firstSimilarity = ((double)similarLinesCount*100)/file1Length;
		double secondSimilarity = ((double) similarLinesCount*100)/file2Length;
		return new PlagiarismResult(student1Id,student2Id,hwId,firstFile,secondFile,firstSimilarity,secondSimilarity,similarLines);
	}


	/**
	 * https://www.geeksforgeeks.org/dynamic-programming-set-5-edit-distance/
     * Returns the edit distance between two string/lines of code
     *
     * @param string1 line of code of first file
     * @param string2 line of code of second file
     * @return integer that represents edit distance between lines of code
     */
	public int getEditDistance(String string1, String string2) {
		int m = string1.length();
		int n = string2.length();
		int dp[][] = new int[m + 1][n + 1];
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0)
					dp[i][j] = j;
				else if (j == 0)
					dp[i][j] = i;
				else if (string1.charAt(i - 1) == string2.charAt(j - 1))
					dp[i][j] = dp[i - 1][j - 1];
				else
					dp[i][j] = 1 + min(dp[i][j - 1], dp[i - 1][j], dp[i - 1][j - 1]);
			}
		}

		return dp[m][n];
	}

	/**
     * Returns the minimum of three integers passed
     *
     * @param x first integer
     * @param y second integer
     * @param z third integer
     * @return integer that is the maximum of the three integers
     */
	public int min(int x, int y, int z) {
		if (x <= y && x <= z)
			return x;
		if (y <= x && y <= z)
			return y;
		else
			return z;
	}
	
	
	
	
}

