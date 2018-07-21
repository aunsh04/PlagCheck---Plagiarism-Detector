package model;

import java.io.File;
/**
 * POJO for storing the result of plagiriarism between two files
 * @author Nirupama
 *
 */
public class PlagiarismResult {
	private String studentId1;
	private String studentId2;
	private String hwId;
	private File file1;
	private File file2;
	private double percentage1;
	private double percentage2;
    private SimilarLines lines;
	
	public PlagiarismResult(String studentId1, String studentId2, String hwId, File file1, File file2,
			double percentage1, double percentage2, SimilarLines lines) {
		super();
		this.studentId1 = studentId1;
		this.studentId2 = studentId2;
		this.hwId = hwId;
		this.file1 = file1;
		this.file2 = file2;
		this.percentage1 = percentage1;
		this.percentage2 = percentage2;
		this.lines = lines;
	}
	
	public PlagiarismResult() {
		
	}
	/**
	 * 
	 * @return similar lines of two file under plagiarism test
	 */
	public SimilarLines getLines() {
		return lines;
	}
	/**
	 * 
	 * @param lines list of lines of two files which are similar
	 */
	public void setLines(SimilarLines lines) {
		this.lines = lines;
	}
	
    /**
     * Returns the studentId of the first student from the PlagiarismResult
     * 
     * @return studentId of the first student from the PlagiarismResult
     */
	public String getStudentId1() {
		return studentId1;
	}
	
	
    /**
     * Set the studentId of the first student in the PlagiarismResult
     * @param studentId1 first student's id 
     */
	public void setStudentId1(String studentId1) {
		this.studentId1 = studentId1;
	}
	
    /**
     * Returns the studentId of the second student from the PlagiarismResult
     * 
     * @return studentId of the second student from the PlagiarismResult
     */
	public String getStudentId2() {
		return studentId2;
	}
	
    /**
     * Set the studentId of the second student in the PlagiarismResult
     * 
     */
	public void setStudentId2(String studentId2) {
		this.studentId2 = studentId2;
	}
	
    /**
     * Returns the file of the first student from the PlagiarismResult
     * 
     * @return file1 file of the first student from the PlagiarismResult
     */
	public File getFile1() {
		return file1;
	}
	
    /**
     * Set the file of the first student in the PlagiarismResult
     * @param file1 first student's submission's file
     */
	public void setFile1(File file1) {
		this.file1 = file1;
	}
	
	
    /**
     * Returns the file of the second student from the PlagiarismResult
     * 
     * @return file2 file of the second student from the PlagiarismResult
     */
	public File getFile2() {
		return file2;
	}
	
    /**
     * Set the file of the second student in the PlagiarismResult
     * @param file2 second student's submission's file
     */
	public void setFile2(File file2) {
		this.file2 = file2;
	}
	
	/**
     * Get the first student's submission's plagiarism percent from the PlagiarismResult
     * 
     * @return percentage1 percentage of the first student's submission from the PlagiarismResult 
     */
	public double getPercentage1() {
		return percentage1;
	}
	
	
    /**
     * Set the first student's submission's plagiarism percent in the PlagiarismResult
     * @param percentage1 second student's submission's plagiarism percent
     */
	public void setPercentage1(double percentage1) {
		this.percentage1 = percentage1;
	}
	
	
	/**
     * Get the id of the homework from the PlagiarismResult
     * 
     * @return hwId id of the homework from the PlagiarismResult 
     */
	public String getHwId() {
		return hwId;
	}
	
    /**
     * Set the homework id of the test in the PlagiarismResult
     * @param homework id of the test
     */
	public void setHwId(String hwId) {
		this.hwId = hwId;
	}
	
	/**
     * Get the second student's submission's plagiarism percent from the PlagiarismResult
     * 
     * @return percentage2 percentage of the second student's submission from the PlagiarismResult 
     */
	public double getPercentage2() {
		return percentage2;
	}
	
    /**
     * Set the second student's submission's plagiarism percent in the PlagiarismResult
     * @param percentage2 second student's submission's plagiarism percent
     */
	public void setPercentage2(double percentage2) {
		this.percentage2 = percentage2;
	}

}
