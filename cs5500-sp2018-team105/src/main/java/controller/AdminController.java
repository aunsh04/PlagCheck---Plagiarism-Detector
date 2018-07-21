package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import model.Instructor;
import service.AdminService;


/**
 * Servlet implementation class AdminController
 * @author Anirudh
 * AdminController lets the admin run a new test, set up instructors
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static Logger logger = Logger.getLogger(AdminController.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() 
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
    	
    	String uname = null;
    	int flag = 0;
    	
    	try
    	{
    		HttpSession session = request.getSession();
    		
    		AdminService adminService = new AdminService();
    		
    		// gets the session attribute to differentiate between professor and TA
    		flag = (Integer)session.getAttribute("instructor");
    		
    		// user input fields from the front end
    		String fname = request.getParameter("fname");
    		String lname = request.getParameter("lname");
    		uname = request.getParameter("uname");
    		String pwd = request.getParameter("pwd");
    		String email = request.getParameter("email");
    		
    		List<String> userList = adminService.getUsers(flag);
    		
    		for (String str : userList)
    		{
    			if (uname.equals(str) && !(adminService.invalidForm(fname, lname, pwd, email)))
    				throw new UserExistsException("Username already exists. Please enter another username!");
    		}
    		
    		if (!(adminService.invalidForm(fname, lname, uname, pwd, email)))
    		{
    			
    			Instructor inst = new Instructor(fname, lname, uname, pwd, email);	
    			
    			// invokes service method for setting up instructor
    			adminService.setUpInstructor(inst, flag);
    			
    			request.setAttribute("emptyFields", "");
    			
    			// redirects to admin home screen
    			request.setAttribute("added", "Instructor has been added!");
    			
    			try
    			{
    				RequestDispatcher rd = request.getRequestDispatcher("/admin.jsp");
    			    rd.forward(request, response);
    			}
    			
    			catch(Exception ex)
    			{
    				logger.error("Error while forwarding " + ex);
    			}
    		}
    		
    		else
    		{
    			request.setAttribute("emptyFields", "Please enter all the fields!");
    			
    			if (flag == 0)
    			{
    				try
        			{
        				RequestDispatcher rd = request.getRequestDispatcher("/setupProf.jsp");
        			    rd.forward(request, response);
        			}
        			
        			catch(Exception ex)
        			{
        				logger.error("Error while forwarding " + ex);
        			}
    			}
    			
    			else
    			{
    				try
        			{
        				RequestDispatcher rd = request.getRequestDispatcher("/setupTA.jsp");
        			    rd.forward(request, response);
        			}
        			
        			catch(Exception ex)
        			{
        				logger.error("Error while forwarding " + ex);
        			}
    			}
    			
    		}
    	}
    	
    	catch(UserExistsException u)
    	{
    		
    		if (flag == 0)
			{
				String msg = "Username " + uname + " already exists. Please try another username"; 
				request.setAttribute("duplicate", msg);
				
				
				try
    			{
    				RequestDispatcher rd = request.getRequestDispatcher("/setupProf.jsp");
    			    rd.forward(request, response);
    			}
    			
    			catch(Exception ex)
    			{
    				logger.error("Error while forwarding " + ex);
    			}
			}
			
			else 
			{
				String msg = "Username " + uname + " already exists. Please try another username"; 
				request.setAttribute("duplicate", msg);
				
				try
    			{
    				RequestDispatcher rd = request.getRequestDispatcher("/setupTA.jsp");
    			    rd.forward(request, response);
    			}
    			
    			catch(Exception ex)
    			{
    				logger.error("Error while forwarding " + ex);
    			}
			}
    		
    		logger.error("Username already exists. " + u);
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
