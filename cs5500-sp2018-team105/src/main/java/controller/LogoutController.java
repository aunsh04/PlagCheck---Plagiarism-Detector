package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@WebServlet("/LogoutController")
/*
 * @author Anirudh
 * LogoutController for logging a user out, ends the current session
 */
public class LogoutController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	static Logger logger = Logger.getLogger(LoginController.class);
	   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutController() 
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 */
    @Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
    	
    	// ends the current session and redirects to login
    	HttpSession session = request.getSession(false);
    	
    	if(session != null)
    	    session.invalidate();
    	try
    	{
    		request.getRequestDispatcher("/login.jsp").forward(request,response);
    	}
    	
    	catch(Exception e)
    	{
    		logger.error("Error while forwarding " + e);
    	}
	}
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 */
    @Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
	

}
