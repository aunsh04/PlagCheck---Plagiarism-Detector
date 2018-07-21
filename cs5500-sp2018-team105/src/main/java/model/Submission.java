package model;

import java.io.File;

/**
 * POJO for storing the submission details
 * @author Nirupama
 *
 */
public class Submission {
	private String studentId;
	private String hwId;
	private File file;
	
	
	public Submission() {
		
	}
	public Submission(String studentId, String hwId, File file) {
		super();
		this.studentId = studentId;
		this.hwId = hwId;
		this.file = file;
	}
	
    /**
     * Returns the student id from the submission
     * 
     * @return studentId the student id of the submission(file)
     */
	public String getStudentId() {
		return studentId;
	}
	
    /**
     * Set the student id of the submission
     * 
     */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

    /**
     * Returns the hw id from the submission
     * 
     * @return hwId the hw id of the submission(file)
     */
	public String getHwId() {
		return hwId;
	}
	
    /**
     * Set the hw id of the submission
     * 
     */
	public void setHwId(String hwId) {
		this.hwId = hwId;
	}
	
    /**
     * Returns the file from the submission
     * 
     * @return file the file of the submission
     */
	public File getFile() {
		return file;
	}
	
    /**
     * Set the file of the submission
     * 
     */
	public void setFile(File file) {
		this.file = file;
	}


}
