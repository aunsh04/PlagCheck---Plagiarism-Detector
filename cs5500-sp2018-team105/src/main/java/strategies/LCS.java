package strategies;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import model.PlagiarismResult;
import model.SimilarLines;
import model.Submission;
import strategypattern.ComparisonStrategy;

/**
 * This class implements the Longest Common Sequence algorithm for list of submissions
 * passed to it and computes the similarity percent for each file/submission
 *
 * @author  Aunsh Chaudhari
 */
public class LCS implements ComparisonStrategy{
	
	static Logger logger = Logger.getLogger(AuthorStrategy.class);
	
	
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
			String firstFileCode = getString(firstSubmission.getFile()).replaceAll("([\\r\\n])", "");
			String secondFileCode = getString(secondSubmission.getFile()).replaceAll("([\\r\\n])", "");
			int lcs = getLCS(firstFileCode,secondFileCode);
			double firstPercentage = computePercent(lcs,firstFileCode.length());
			double secondPercentage = computePercent(lcs,secondFileCode.length());
			return new PlagiarismResult(firstSubmission.getStudentId(),secondSubmission.getStudentId(),firstSubmission.getHwId(),
					firstSubmission.getFile(),secondSubmission.getFile(),firstPercentage,secondPercentage,similarLines);

		}
		return new PlagiarismResult(firstSubmission.getStudentId(),secondSubmission.getStudentId(),
				firstSubmission.getHwId(),firstSubmission.getFile(),secondSubmission.getFile(),100.0,100.0,similarLines);
	}
	
	
	
	
	/**
     * Returns the plagiarism percent of the file
     *
     * @param length of longest common subsequence of strings between two files
     * @param length length of file
     * @return the plagiarism percent of the file
     */
	public Double computePercent(int lcs, int length) {
		return ((double) lcs) / length * 100.0;
	}
	
	/**
     * Returns the code file as a string
     *
     * @param file code file
     * @return all the lines of the code in the file as a string
     */
	public String getString(File file) {
		StringBuilder code = new StringBuilder();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
					code.append(line);
			}
		} catch (FileNotFoundException e) {
			logger.error("File not found:"+e);
		} catch (IOException e) {
			logger.error("Cannot read file:"+e);
		}
		return code.toString();
	}
	
	/**
	 * LCS algorithm at https://www.geeksforgeeks.org/longest-common-subsequence/
     * Returns the length of longest common subsequence between the a line of
     * code of two files 
     *
     * @param string1 line of code of first file
     * @param string2 line of code of second file
     * @return length of longest common subsequence
     */
	public int getLCS(String string1, String string2) {
		int m = string1.length();
		int n = string2.length();
        int[][] L = new int[m+1][n+1];
        for (int i=0; i<=m; i++)
        {
            for (int j=0; j<=n; j++)
            {
                if (i == 0 || j == 0)
                    L[i][j] = 0;
                else if (string1.charAt(i-1) == string2.charAt(j-1))
                    L[i][j] = L[i-1][j-1] + 1;
                else
                    L[i][j] = Math.max(L[i-1][j], L[i][j-1]);
            }
        }
        return L[m][n];
	}
	
}
