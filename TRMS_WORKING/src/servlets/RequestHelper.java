package servlets;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Employee;

import com.revature.util.ConnectionFactory;

import daos.EmployeeDaoImpl;

public class RequestHelper {
 
	private ServletContext context;
	
	public String process(HttpServletRequest req, HttpServletResponse resp){
		Connection conn = ConnectionFactory.getInstance().getConnection(context);
		
		String path = req.getRequestURI();
		
		String dest = null;
		
		System.out.println(path);
		
	    
	    if(path.equals("/TRMS_WORKING/"))  
	    	dest = "intropage.html";
	    else dest = "404.html";
	                     
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return dest;
	}
	
	public RequestHelper(ServletContext context){
		super();
		this.context = context;
	}
}
