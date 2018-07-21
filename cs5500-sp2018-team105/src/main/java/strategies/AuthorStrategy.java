package strategies;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.apache.log4j.Logger;

import model.Submission;


/**
 * This class implements a strategy to compare two files
 * for the owner and author of the file
 *
 * @author  Aunsh Chaudhari
 */
public class AuthorStrategy{
	
	static Logger logger = Logger.getLogger(AuthorStrategy.class);
	
	/**
     * Processes the array of files passed to convert them to arrays of string
     * in order to compare author and owner
     * @param firstSubmission submission of first student
     * @param secondSubmission submission of second student
     * @return boolean true if the two files have the same author or owner
     */
	public boolean checkIfSameAuthor(Submission firstSubmission, Submission secondSubmission) {
			File firstFile = firstSubmission.getFile();
			File secondFile = secondSubmission.getFile();
			String firstFileAuthor = getAuthorOfFile(firstFile);
			String secondFileAuthor = getAuthorOfFile(secondFile);
			if (firstFileAuthor.equals("") || secondFileAuthor.equals("")) {
				return false;
			}
			return firstFileAuthor.equals(secondFileAuthor);
	}
	
	/**
     * Processes the file passed and retrieves the author of the file
     * @param file file to be checked owner for
     * @return string that is the author from the doc of the file passed
     */
	public String getAuthorOfFile(File file) {
		String author = "";
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				if (line.contains("@author")) {
					author = parseAuthorComment(line);
					break;
				}
			}
		} catch (FileNotFoundException e) {
			logger.error("File not found:"+e);
		} catch (IOException e) {
			logger.error("Cannot read file:"+e);
		}
		return author;
	}
	
	/**
     * Parses the comment line for the author
     * @param line comment to be parsed to return author
     * @return author of the file parsed from comment
     */
	public String parseAuthorComment(String line) {
		String lineWithoutSpaces =  line.replaceAll("\\s+","");
		int indexOfAuthorAnnotation = lineWithoutSpaces.indexOf("@author");
		int indexOfAuthor = indexOfAuthorAnnotation + 7;
		return lineWithoutSpaces.substring(indexOfAuthor);
	}
}
