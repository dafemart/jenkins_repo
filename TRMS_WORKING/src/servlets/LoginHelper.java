package servlets;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Employee;

import com.revature.util.ConnectionFactory;

import daos.EmployeeDaoImpl;

public class LoginHelper {
	private ServletContext context;
	public String processLogin(HttpServletRequest req,HttpServletResponse resp){
		Connection conn = ConnectionFactory.getInstance().getConnection(context);
		String dest = null;
		
		if(null != req.getSession(true).getAttribute("email")){
			if(req.getSession(true).getAttribute("usertype") == "employee")
			  return "entityPortal.html";
			else if(req.getSession(true).getAttribute("usertype") == "benco")
				return "bencoPortal.html";
			else return "404.html";
		}
		
		String username = req.getParameter("email");
		String password = req.getParameter("pwd");
		String type     = req.getParameter("employees");
		EmployeeDaoImpl EmpDao = new EmployeeDaoImpl();
		System.out.println(username + " " + password);
		
		switch(type){
		  case "employee":
			  Employee empEmployee = EmpDao.retrieveEmployeeByEmail(username, conn);
			  if(empEmployee != null) System.out.println(empEmployee.getEmployeeName() + " is logging in ");
			  if(empEmployee != null && password.compareTo(empEmployee.getEmployeePassword()) == 0){
				  System.out.println("entering portal");
				  req.getSession().setAttribute("email", username);
				  req.getSession().setAttribute("usertype", "EMPLOYEE");
				  System.out.println("Printing session value from sender" + (String)req.getSession().getAttribute("email"));
				  dest = "entityPortal.html"; 
			  }
			  else dest = "404.html";
			  break;
		  case "benco":
			  Employee empBenco = EmpDao.retrieveBencoByEmail(username, conn);
			  if(empBenco != null && password.compareTo(empBenco.getEmployeePassword()) == 0){
				  req.getSession().setAttribute("email", username);
				  req.getSession().setAttribute("usertype","BENCO");
				  System.out.println("Printing session value from sender" + (String)req.getSession().getAttribute("email"));
				  dest = "bencoPortal.html"; 
			  }
			  else dest = "404.html";
			  break;
		  case "DirectManager":
			  Employee empDirectManager = EmpDao.retrieveDirectManagerByEmail(username, conn);
			  if(empDirectManager != null && password == empDirectManager.getEmployeePassword()){
				  dest = "entityPortal.html"; 
			  }
			  else dest = "404.html";
			  break;
		  case "DirectSupervisor":
			  Employee empDirectSupervisor= EmpDao.retrieveDirectSupervisorByEmail(username, conn);
			  if(empDirectSupervisor != null && password == empDirectSupervisor.getEmployeePassword()){
				  dest = "entityPortal.html"; 
			  }
			  else dest = "404.html";
			  break;
		  case "DepartmentHead":
			  Employee empDepartmentHead= EmpDao.retrieveDepartmentHeadByEmail(username, conn);
			  if(empDepartmentHead != null && password == empDepartmentHead.getEmployeePassword()){
				  dest = "entityPortal.html"; 
			  }
			  else dest = "404.html";
			  break;
		  default : dest = "404.html";
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dest;
	}
	
	public LoginHelper(ServletContext context){
		super();
		this.context = context;
	}
}
