package servlets;

import java.util.ArrayList;

import Entities.Employee;
import Entities.ReimbursementRequest;

public class SessionInfo{
		Employee emp;
		ArrayList<ReimbursementRequest> PoolRequests;
		
		public SessionInfo(Employee emp, ArrayList<ReimbursementRequest> reqs){
			this.emp = emp;
			this.PoolRequests = reqs;
		}
		
		public void setEmp(Employee emp){
			this.emp = emp;
		}
		
		public void setPoolRequests(ArrayList<ReimbursementRequest> reqs){
			this.PoolRequests = reqs;
		}
		
		public Employee getEmployee(){
			return this.emp;
		}
		
		public ArrayList<ReimbursementRequest> getPoolRequests(){
			return this.PoolRequests;
		}
}