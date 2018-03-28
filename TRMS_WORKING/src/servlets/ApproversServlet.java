package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ApproversServlet extends HttpServlet {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1251159016035244519L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException
	{
		 System.out.println("sending approver info session");
	     SessionInfo info = new ApproversHelper(getServletContext()).retrieveSessionInfo(req, resp);
	     ObjectMapper om = new ObjectMapper();
	     String UserInfoString = om.writeValueAsString(info);
	     System.out.println(UserInfoString);
	     resp.getWriter().write(UserInfoString);  
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException
	{
		System.out.println("Issuing reimbursement request");
		new ApproversHelper(getServletContext()).issueReimbursementRequest(req, resp);
		
	}
}
