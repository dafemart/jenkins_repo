package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{



	/**
	 * 
	 */
	private static final long serialVersionUID = 4286940923785367241L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException
	   {
		   System.out.println("GET");
		   String forward = new LoginHelper(getServletContext()).processLogin(req, resp);
		   req.getRequestDispatcher(forward).forward(req, resp);
	   }
	   
	   protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException
	   {
		   System.out.println("POST");
		   String forward = new LoginHelper(getServletContext()).processLogin(req, resp);
		   req.getRequestDispatcher(forward).forward(req, resp);
	   }

}
