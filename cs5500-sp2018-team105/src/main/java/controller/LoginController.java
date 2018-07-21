package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import service.LoginService;


/**
 * Servlet implementation class LoginController
 * @author Anirudh
 * LoginController for logging a user in
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	static Logger logger = Logger.getLogger(LoginController.class);
	   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() 
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
    	
    	LoginService loginService = new LoginService();
    	
    	HttpSession session = request.getSession();
    	
    	// retrieving username and password from login page
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		
		
		try 
		{
			// invokes service method for login
			int status = loginService.validateUser(uname, pwd);
			
			// different criteria for login, includes erroneous/blank usernames and passwords
			if (status == -1) 
			{
				request.setAttribute("userNameErr", "");
				request.setAttribute("errMsg", "Username and Password are incorrect");
			    RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			    rd.forward(request, response);
			}
			
			
			else if (status == 0) 
			{
				response.sendRedirect("admin.jsp");
				session.setAttribute("userType", "admin");
				request.setAttribute("userNameErr", "");
				request.setAttribute("errMsg", "");
			}	
			
			else if (status == 1) 
			{
				response.sendRedirect("homePage.jsp");
				session.setAttribute("userType", "user");
				session.setAttribute("username", uname);
				request.setAttribute("userNameErr", "");
				request.setAttribute("errMsg", "");
			}
			
			else 
			{
				request.setAttribute("errMsg", "");
				request.setAttribute("userNameErr", "Please enter username or password ");
				RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			    rd.forward(request, response); 
			}
		}
		
		catch (Exception e) 
		{
			logger.error("Error while logging in" + e);
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
