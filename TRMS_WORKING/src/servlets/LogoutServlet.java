package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {



	/**
	 * 
	 */
	private static final long serialVersionUID = 3037031971056516798L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException
	   {
		 System.out.println("Logging out");
		 HttpSession session = req.getSession(false); 
		 session.removeAttribute("email"); 
		 if(session!=null)
		   session.invalidate();  
	   }
}
