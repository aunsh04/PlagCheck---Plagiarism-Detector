package strategies;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import model.PlagiarismResult;
import model.Submission;
import strategypattern.ComparisonStrategy;

/**
 * This class implements the Weighted Polynomial Strategy. It uses more the three comparison strategies 
 * and computes an overall score using a weighted polynomial function
 *
 * @author  Aunsh Chaudhari
 */

public class WeightedPolynomialStrategy implements ComparisonStrategy {
	static Logger logger =  Logger.getLogger("WeightedPolynomialStrategy.class");
	
	/**
     * Processes the list of submissions passed 
     * and moves ahead to compute plagiarism percent in both files with the help of the weighted polynomial
     * strategy 
     * @param list of submissions - each submission is an object with student id, hw id and the file
     * @return list of plagiarism cases - each case is an object with the 
     * plagiarism percentages, files, student ids
     */
	@Override
	public List<PlagiarismResult> getReport(List<Submission> submissionList) {
		EditDistance editDistance = new EditDistance();
		ASTStrategy ast = new ASTStrategy();
		LCS lcs = new LCS();
		List<PlagiarismResult> editDistanceList = editDistance.getReport(submissionList);
		List<PlagiarismResult> astList = ast.getReport(submissionList);
		List<PlagiarismResult> lcsList = lcs.getReport(submissionList);
		return getWeightedPlagiarismResult(editDistanceList,astList,lcsList,0.3,0.2,0.5);
			
	}

	/**
     * Processes the list of PlagiarismResults from each strategy
     * and moves ahead to compute plagiarism percent in both files with the help of the weighted polynomial
     * strategy where we have assigned weights to each strategy
     * @param editDistanceList list of results obtained from the Edit Distance strategy
     * @param astList list of results obtained from the AST strategy
     * @param lcsList list of results obtained from the LCS strategy
     * @param firstWeight weight to be assigned to Edit Distance Strategy
     * @param secondWeight weight to be assigned to LCS Strategy
     * @param thirdWeight weight to be assigned to AST Strategy
     * @return List<PlagiarismResult> that is the data of the case with the newly computed scores(percentages) after weighting
     */
	
	
	public List<PlagiarismResult> getWeightedPlagiarismResult(List<PlagiarismResult> editDistanceList,List<PlagiarismResult> astList,
			List<PlagiarismResult> lcsList, double firstWeight, double secondWeight, double thirdWeight) {
		List<PlagiarismResult> weightedList = new ArrayList<>();
		int i = 0;
		while (i<editDistanceList.size() && i<astList.size() && i<lcsList.size()) {
			PlagiarismResult edResult = editDistanceList.get(i);
			PlagiarismResult astResult = astList.get(i);
			PlagiarismResult lcsResult = lcsList.get(i);
			double newPercentage1 = edResult.getPercentage1()*firstWeight+lcsResult.getPercentage1()*secondWeight+astResult.getPercentage1()*thirdWeight;
			double newPercentage2 = edResult.getPercentage2()*firstWeight+lcsResult.getPercentage2()*secondWeight+astResult.getPercentage2()*thirdWeight;
			PlagiarismResult newResult =  new PlagiarismResult(astResult.getStudentId1(),astResult.getStudentId2(),astResult.getHwId(),
								astResult.getFile1(),astResult.getFile2(),newPercentage1,newPercentage2,astResult.getLines());
			weightedList.add(newResult);
			i++;		
	}
		return weightedList;
	}


}
	

