package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class EmployeeServlet extends HttpServlet {



	/**
	 * 
	 */
	private static final long serialVersionUID = -3573390257778379679L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException
	{
	     SessionInfo info = new EmployeeHelper(getServletContext()).retrieveSessionInfo(req, resp);
	     ObjectMapper om = new ObjectMapper();
	     String UserInfoString = om.writeValueAsString(info);
	     System.out.println(UserInfoString);
	     resp.getWriter().write(UserInfoString);
	}
	   
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException
	{
		   String redirect = new EmployeeHelper(getServletContext()).createEmployeeRequest(req, resp);
		   resp.sendRedirect(redirect);
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException
	{
		System.out.println("issuing delete");
		new EmployeeHelper(getServletContext()).deleteEmployeeRequest(req, resp);
	}
}
