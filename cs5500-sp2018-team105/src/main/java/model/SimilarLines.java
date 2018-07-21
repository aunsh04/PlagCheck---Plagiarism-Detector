package model;

import java.util.List;
/**
 * POJO for getting similar lines in two files
 * @author Nirupama
 *
 */
public class SimilarLines {
	
	public SimilarLines(List<Integer> lineFile1, List<Integer> lineFile2) {
		super();
		this.lineFile1 = lineFile1;
		this.lineFile2 = lineFile2;
	}
	public List<Integer> getLineFile1() {
		return lineFile1;
	}
	public void setLineFile1(List<Integer> lineFile1) {
		this.lineFile1 = lineFile1;
	}
	public List<Integer> getLineFile2() {
		return lineFile2;
	}
	public void setLineFile2(List<Integer> lineFile2) {
		this.lineFile2 = lineFile2;
	}
	private List<Integer> lineFile1;
	private List<Integer> lineFile2;

}
