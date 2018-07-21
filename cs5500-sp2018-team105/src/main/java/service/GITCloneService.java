package service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import model.Submission;

/*
 * @author Anirudh
 * GITCloneService for cloning and pulling a remote git repo
 */
public class GITCloneService
{
	
	static Logger logger = Logger.getLogger(GITCloneService.class);

	Git git = null;
	org.eclipse.jgit.lib.Repository localRepo = null;

	
	private List<Submission> submissionList = new ArrayList<>();
	Git result = null;


	/*
	 * @see cloneOrPullRepos(String, String, String, String)
	 * @return List<Submissions>
	 * @param String hw
	 * @param String destination
	 * @param String username
	 * @param String password
	 * clones the repo at the specified destination if it doesn't exist already
	 * pulls if the local repo already exists
	 * returns the list of submissions (only of the homework specified before)
	 */
	public List<Submission> cloneOrPullRepos(String hw, String destination, String username, String password) throws IOException, ServletException
	{
		List<Submission> finalList = new ArrayList<>();
		GitHubClient client = new GitHubClient("github.ccs.neu.edu");
		RepositoryService service = new RepositoryService(client);
		
		//authenticates the git repository
		service.getClient().setCredentials(username, password);
		
		for (Repository repo : service.getRepositories())
		{
			try
			{
				
				// retrieving the clone url to clone or pull
				String repoUrl = repo.getCloneUrl();
				
				int lastSlash = repoUrl.lastIndexOf('/');
				int lastDot = repoUrl.lastIndexOf('.');

				String folderName = repoUrl.substring(lastSlash + 1, lastDot);

				String path = destination + "/" + folderName;
				
				File directory = new File(path);

				localRepo = new FileRepository(path + "/.git");

				git = new Git(localRepo);

				// method for cloning
				if (directory.exists())
					gitPull(username, password);

				// method for pulling
				else
					gitClone(username, password, directory, repoUrl);
				
				// method for fetching all the files from the repo
				finalList = fetchFiles(new File(path + "/" + hw), hw, folderName);

			}

			catch(Exception e)
			{
				logger.error("Wrong path or destination of files:"+ e);
			}

			finally 
			{
				if (localRepo != null)
					localRepo.close();

				if (result != null)
					result.close();

				if (git != null)
					git.close();
			}
		}
		
		return finalList;
	}

	/*
	 * @see pull(String, String)
	 * @param String 
	 * @param String
	 * pulls repos at the specified location
	 */
	public void gitPull(String username, String password)
	{
		try
		{
			PullCommand command = git.pull()
					.setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password));

			command.call();

		}

		catch(Exception e)
		{
			logger.error("Not able to pull:"+ e);
		}
	}

	/*
	 * @see clone(String, String String, String)
	 * @param String 
	 * @param String
	 * @param String
	 * @param String
	 * clones repos at the specified location if the directory does not exist already
	 */
	public void gitClone(String username, String password, File directory, String repoUrl)
	{
		try
		{
			result = Git.cloneRepository()
					.setURI(repoUrl).setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password))
					.setDirectory(directory)
					.call();

			result.getRepository().close();
		}

		catch (Exception e) 
		{
			logger.error("Not cloned:"+ e);
		}
	}
	

	/*
	 * @see fetchFiles(File, String String)
	 * @return List<Submission>
	 * @param File 
	 * @param String
	 * @param String
	 * returns list of submissions for the specified homework
	 */
	public List<Submission> fetchFiles(File f,String hw, String folderName)
	{
		
		File[] files = f.listFiles();
		for (File file : files)
		{
			if (file.isDirectory())
			{
				fetchFiles(file,hw,folderName);
			}

			else 
			{
				String fileName = file.getName();
				int extensionIndex = fileName.lastIndexOf('.');

				String extension = fileName.substring(extensionIndex);
				
				if (extension.equals(".py"))
				{
					
					Submission submission = new Submission(folderName,hw,file);
					submissionList.add(submission);
				}
					
			}
		}
		
		return submissionList;
		
	}
	
}



