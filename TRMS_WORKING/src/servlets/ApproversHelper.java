package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.util.ConnectionFactory;

import daos.EmployeeDaoImpl;
import daos.ReimbursementRequestDaoImpl;
import Entities.Employee;
import Entities.ReimbursementRequest;

public class ApproversHelper {
	private ServletContext context;
	public ApproversHelper(ServletContext context){
		this.context = context;
	}
	public SessionInfo retrieveSessionInfo(HttpServletRequest req, HttpServletResponse resp){
		Connection conn = ConnectionFactory.getInstance().getConnection(context);
		EmployeeDaoImpl EmpDao = new EmployeeDaoImpl();
		ReimbursementRequestDaoImpl ReimbReq = new ReimbursementRequestDaoImpl();
		System.out.println("changed");
		System.out.println((String)req.getSession().getAttribute("email"));
		String usertype = (String) req.getSession().getAttribute("usertype");
		Employee emp = null;
		SessionInfo info = null;
		switch(usertype){
		  case "BENCO":
			emp = EmpDao.retrieveBencoByEmail((String)req.getSession().getAttribute("email"), conn);
			info = new SessionInfo(emp, ReimbReq.retrieveReimbRequestByIssuer("BENCO", conn));
		  break;
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return info;
     }
	
	 public void issueReimbursementRequest(HttpServletRequest req, HttpServletResponse resp){
		 Connection conn = ConnectionFactory.getInstance().getConnection(context);
		 String decision = req.getParameter("decision");
		 try {
			System.out.println(req.getReader());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 int RequestID = Integer.parseInt(req.getParameter("requestid"));
		 ReimbursementRequestDaoImpl reqDao = new ReimbursementRequestDaoImpl();
		 final String DENNIED = "deny";
		 final String APPROVED = "approve";
		 ReimbursementRequest request = reqDao.retrieveReimbRequestById(RequestID, conn);
		 if(decision == DENNIED)
			 request.setStatus(ReimbursementRequest.getDenniedValue());
		 else if(decision == APPROVED)
			 request.setStatus(ReimbursementRequest.getApprovedValue());
		 request.setCurrentIssuer(ReimbursementRequest.getDirectSupervisorValue());
		 reqDao.updateReimbursementRequest(request, conn);
	 }
}
