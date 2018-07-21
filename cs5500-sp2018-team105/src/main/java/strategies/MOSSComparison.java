package strategies;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.net.URL;
import it.zielke.moji.SocketClient;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * MOSSComparison class compares all submission files present in the course directory structure
 * @author Nirupama
 * citation: https://github.com/nordicway/moji
 * https://jsoup.org/cookbook/extracting-data/attributes-text-html
 *
 */
public class MOSSComparison {
	static Logger logger = Logger.getLogger(MOSSComparison.class);
	/**
	 * mossResult method takes path of course directory and get all files of students 
	 * from directory and compare all student1 files with student files
	 * @param path directory path
	 * @return two percentages of plagiarism in student1 and student2 files respectively
	 */
	public List<Double> mossResult(String path)
	{
		List<Double> result=new ArrayList<>();
		try
		{
			//getting files from path directory structure
			Collection<File> files = FileUtils.listFiles(new File(path), new String[] { "py" }, true);
			SocketClient socketClient = new SocketClient();
			//setting up connection, used friend's moss id as we didn't receive our 
			//moss id mail yet
			socketClient.setUserID("149201555");
			socketClient.setLanguage("python");
			socketClient.run();
			for (File f : files) {
				socketClient.uploadFile(f);
			}
			socketClient.sendQuery();
			URL results = socketClient.getResultURL();
			String url = results.toString();
			// Jsoup is used to extract data from MOSS html result page
			// it uses tr and td for moss result table row and column to 
			// extract percentages of two files
			String percent = Jsoup.connect(url).get().html();
			Document doc = Jsoup.parse(percent, "UTF-8");
			Elements mossResultTable = doc.getElementsByTag("table");

			Element row = mossResultTable.select("tr").get(1);
			//percent for first file
			Element column1 = row.select("td").get(0);
			String percent1 = column1.text();
			int index1 = percent1.indexOf('%');
			String percentage1 = percent1.substring(index1 - 2, index1);
			//percent for second file
			Element column2 = row.select("td").get(1);			
			String percent2 = column2.text();
			int index2 = percent2.indexOf('%');			
			String percentage2 = percent2.substring(index2 - 2, index2);
			//adding percentages of both files in result
			result.add(Double.parseDouble(percentage1));
			result.add(Double.parseDouble(percentage2));
		}
		catch(Exception e)
		{
			logger.error("moss exception", e);
		}
		return result;
	}


}


