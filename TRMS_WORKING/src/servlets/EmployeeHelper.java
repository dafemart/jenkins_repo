package servlets;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Employee;
import Entities.ReimbursementRequest;

import com.revature.util.ConnectionFactory;

import daos.EmployeeDaoImpl;
import daos.ReimbursementRequestDaoImpl;

public class EmployeeHelper {
	private ServletContext context;
	public EmployeeHelper(ServletContext context){
		super();
		this.context = context;
	}
	
	public SessionInfo retrieveSessionInfo(HttpServletRequest req, HttpServletResponse resp){
		Connection conn = ConnectionFactory.getInstance().getConnection(context);
		EmployeeDaoImpl EmpDao = new EmployeeDaoImpl();
		ReimbursementRequestDaoImpl ReimbReq = new ReimbursementRequestDaoImpl();
		System.out.println("changed");
		System.out.println((String)req.getSession().getAttribute("email"));
		Employee emp = EmpDao.retrieveEmployeeByEmail((String)req.getSession().getAttribute("email"), conn);
		SessionInfo info = new SessionInfo(emp, ReimbReq.retrieveReimbRequestsByEmpId(emp.getEmployeeID(), conn));
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return info;
     }
	
	public String createEmployeeRequest(HttpServletRequest req, HttpServletResponse resp){
		Connection conn = ConnectionFactory.getInstance().getConnection(context);
		EmployeeDaoImpl EmpDao = new EmployeeDaoImpl();
		Employee emp = EmpDao.retrieveEmployeeByEmail((String)req.getSession().getAttribute("email"), conn);
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
		java.util.Date date = null;
		try {
			date = sdf1.parse((String)(req.getParameter("date")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
		int EventType = Integer.parseInt((String)req.getParameter("eventtype"));
		String Location = req.getParameter("location");
		int cost = Integer.parseInt((String)req.getParameter("cost"));
		String justification = req.getParameter("justification");
		String issuer = "BENCO";
		int status = 0;
		
		System.out.println("Event type " + EventType + " location " + Location + " cost " 
		                    + cost + " justification " + justification + " issuer " + issuer +  
		                    "status" + status + "date" + sqlStartDate); 
		ReimbursementRequest reimbReq = new ReimbursementRequest(0,emp.getEmployeeID(),
                EventType, sqlStartDate, Location ,  cost, justification, issuer, status);
        ReimbursementRequestDaoImpl reqDao = new ReimbursementRequestDaoImpl();
        reqDao.createReimbursementRequest(reimbReq, conn);
		String redirectTo = "entityPortal.html";
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return redirectTo;
	}
	
	public void deleteEmployeeRequest(HttpServletRequest req, HttpServletResponse resp){
		Connection conn = ConnectionFactory.getInstance().getConnection(context);
		int RequestID = Integer.parseInt(req.getParameter("requestid"));
		ReimbursementRequestDaoImpl remDao = new ReimbursementRequestDaoImpl();
		remDao.deleteReimbursementRequest(RequestID, conn);
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
