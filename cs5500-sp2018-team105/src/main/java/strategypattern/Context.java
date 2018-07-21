package strategypattern;

import java.util.List;

import model.PlagiarismResult;
import model.Submission;
/**
 * Context class for strategy design pattern to switch between multiple strategies
 * @author Nirupama
 *
 */
public class Context {
	
	   private ComparisonStrategy strategy;

	   public void setStrategy(ComparisonStrategy strategy){
	      this.strategy = strategy;
	   }

	   public List<PlagiarismResult> executeStrategy(List<Submission> submission){
	      return strategy.getReport(submission);
	   }
	}