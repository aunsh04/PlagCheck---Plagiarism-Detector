package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dao.UsageStatsDao;
import model.PlagiarismResult;
import model.Submission;
import service.GITCloneService;
import service.UsageStatsService;
import strategies.ASTStrategy;
import strategies.EditDistance;
import strategies.LCS;
import strategies.WeightCalculation;
import strategies.WeightedPolynomialStrategy;
import strategypattern.Context;


/**
 * Servlet implementation class GITController
 * @author Anirudh
 * GITController class for cloning and pulling a remote git repo
 */
@WebServlet("/GITController")
public class GITController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static Logger logger = Logger.getLogger(GITController.class);
	
	// holds the respective severity objects
	
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GITController() 
	{
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		List<PlagiarismResult> result = new ArrayList<>();
		
		GITCloneService service = new GITCloneService();
		
		// parameters from newTest.jsp
		String hw = request.getParameter("hw");
		
		String destination = request.getParameter("path");
		
		String strategy = request.getParameter("strategy");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try
		{
			// the files of the homework selected from the local repo
			List<Submission> submissionList = service.cloneOrPullRepos(hw, destination, username, password);
			
			UsageStatsDao usageDao = new UsageStatsDao();
			UsageStatsService usageStats = new UsageStatsService(usageDao);
			long startTime = System.currentTimeMillis();
			// different comparison strategies
			
			if (strategy.equals("AST")) 
			{
                
				Context context = new Context();
				context.setStrategy(new ASTStrategy());
                result = context.executeStrategy(submissionList);
                long endTime = System.currentTimeMillis();
                double timeElapsed = ((double)endTime- startTime)/1000;
                usageStats.updateUsageStats(result.size(), timeElapsed);
                renderResults(result, request, response);
			}
			
			
			else if (strategy.equals("LCS"))
			{
				Context context = new Context();
                context.setStrategy(new LCS());
                result = context.executeStrategy(submissionList);
                long endTime = System.currentTimeMillis();
                double timeElapsed = ((double)endTime- startTime)/1000;
                usageStats.updateUsageStats(result.size(), timeElapsed);
                renderResults(result, request, response);
            }
			else if (strategy.equals("Edit"))
			{
				Context context = new Context();
                context.setStrategy(new EditDistance());
                result = context.executeStrategy(submissionList);
                long endTime = System.currentTimeMillis();
                double timeElapsed = ((double)endTime- startTime)/1000;
                usageStats.updateUsageStats(result.size(), timeElapsed);
                renderResults(result, request, response);
            }
			
			else if (strategy.equals("Weighted"))
			{
				Context context = new Context();
                context.setStrategy(new WeightedPolynomialStrategy());
                result = context.executeStrategy(submissionList);
                long endTime = System.currentTimeMillis();
                double timeElapsed = ((double)endTime- startTime)/1000;
                usageStats.updateUsageStats(result.size(), timeElapsed);
                renderResults(result, request, response);
            }
			else 
			{
				Context context = new Context();
                context.setStrategy(new WeightCalculation());
                result = context.executeStrategy(submissionList);
                long endTime = System.currentTimeMillis();
                double timeElapsed = ((double)endTime- startTime)/1000;
                usageStats.updateUsageStats(result.size(), timeElapsed);
                renderResults(result, request, response);
				
			}
			
		}
		
		catch (Exception e) 
		{
			
			if (e.getMessage().indexOf("Bad credentials") != -1 || (e.getMessage().indexOf("Must authenticate to access this API.") != -1) )
			{
				String msg = "Invalid github credentials. Please try again!"; 
				request.setAttribute("github", msg);
				
				try
				{
					RequestDispatcher rd = request.getRequestDispatcher("/newTest.jsp");
				    rd.forward(request, response);
				}
				
				catch(Exception ex)
				{
					logger.error("Error while forwarding " + ex);
				}
				
			}
			
			
			logger.error("GIT Exception" + e);
		}
	
	}
	
	// method which renders results on the testResults page
	public void renderResults(List<PlagiarismResult> result,  HttpServletRequest request, HttpServletResponse response)
	{
		
		List<PlagiarismResult> highSeverity = new ArrayList<>();
		List<PlagiarismResult> mediumSeverity = new ArrayList<>();
		List<PlagiarismResult> lowSeverity = new ArrayList<>();
		
		for (PlagiarismResult pr : result)
        {
        
			// different severity criteria
        	if (pr.getPercentage1() >= 60.0 || pr.getPercentage2() >= 60.0) 
        		highSeverity.add(pr);
        	
        	else if ((pr.getPercentage1() >= 30.0 && pr.getPercentage1() < 60.0) 
					|| (pr.getPercentage2() >= 30.0 && pr.getPercentage2() < 60.0))
        		mediumSeverity.add(pr);
        	
        	else if ((pr.getPercentage1() >= 0.0 && pr.getPercentage1() < 30.0) 
					|| (pr.getPercentage2() >= 0.0 && pr.getPercentage2() < 30.0))
        		lowSeverity.add(pr);
        	
        	else 
        		continue;
        
        }
		
		
		// session attributes based on plagiarism criteria to be sent to the front end
        request.setAttribute("high", highSeverity);
        request.setAttribute("medium", mediumSeverity);
        request.setAttribute("low", lowSeverity);
        
        try 
        {
        	RequestDispatcher rd = request.getRequestDispatcher("/testResults.jsp");
		    rd.forward(request, response);
		} 
        
        catch (IOException e) 
        {
        	logger.error("Error while generating results" + e);
		} 
        
        catch (ServletException e) 
        {
			logger.error("Error while rendering results " + e);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
