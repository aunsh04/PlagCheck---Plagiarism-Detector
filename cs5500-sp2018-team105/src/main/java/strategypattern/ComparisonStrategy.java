package strategypattern;
/**
 * Interface for strategy design pattern
 */

import java.util.List;

import model.PlagiarismResult;
import model.Submission;

public interface ComparisonStrategy {
	/**
	 * This method compares submission files of multiple students
	 * @param file is a list of submissions of students
	 * @return a list of plagiarism reports
	 */
	public List<PlagiarismResult> getReport(List<Submission> file);
}
