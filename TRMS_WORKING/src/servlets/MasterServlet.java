package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MasterServlet extends HttpServlet{  



/**
	 * 
	 */
	private static final long serialVersionUID = -3024902662280726964L;

protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException
   {
	   System.out.println("DEPLOYING OTHER R p p");
	   String forward = new RequestHelper(getServletContext()).process(req, resp);
	   req.getRequestDispatcher(forward).forward(req, resp);
   }
   
   protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException
   {
	   System.out.println("issuing POST");
   }
}

