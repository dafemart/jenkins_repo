package daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;

import com.revature.util.ConnectionPool;

import Entities.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	public void createEmployee(Employee employee, Connection conn) {
		try{
		   CallableStatement createEmployeeStmt = conn.prepareCall("{call INSERTEMPLOYEE(?,?,?,?)}");
		   createEmployeeStmt.setString(1,employee.getEmployeeName());
		   createEmployeeStmt.setString(2, employee.getEmployeeLastName());
		   createEmployeeStmt.setString(3, employee.getEmployeeEmail());
		   createEmployeeStmt.setString(4, employee.getEmployeePassword());
		   createEmployeeStmt.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public void createDirectSupervisor(Employee employee, Connection conn) {
		try{
		   CallableStatement createEmployeeStmt = conn.prepareCall("{call INSERTDIRECTSUPERVISOR(?,?,?,?)}");
		   createEmployeeStmt.setString(1,employee.getEmployeeName());
		   createEmployeeStmt.setString(2, employee.getEmployeeLastName());
		   createEmployeeStmt.setString(3, employee.getEmployeeEmail());
		   createEmployeeStmt.setString(4, employee.getEmployeePassword());
		   createEmployeeStmt.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}

	public void createDirectManager(Employee employee, Connection conn) {
		try{
		   CallableStatement createEmployeeStmt = conn.prepareCall("{call INSERTDIRECTMANAGER(?,?,?,?)}");
		   createEmployeeStmt.setString(1,employee.getEmployeeName());
		   createEmployeeStmt.setString(2, employee.getEmployeeLastName());
		   createEmployeeStmt.setString(3, employee.getEmployeeEmail());
		   createEmployeeStmt.setString(4, employee.getEmployeePassword());
		   createEmployeeStmt.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}

	public void createBenco(Employee employee, Connection conn){
		try{
		   CallableStatement createEmployeeStmt = conn.prepareCall("{call INSERTBENCO(?,?,?,?,?)}");
		   createEmployeeStmt.setString(1, employee.getEmployeeName());
		   createEmployeeStmt.setString(2, employee.getEmployeeLastName());
		   createEmployeeStmt.setString(3, employee.getEmployeeEmail());
		   createEmployeeStmt.setString(4, employee.getEmployeePassword());
		   createEmployeeStmt.setInt(5, employee.getDirectSupervisorID());
		   createEmployeeStmt.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void createDepartmentHead(Employee employee, Connection conn) {
		try{
		   CallableStatement createEmployeeStmt = conn.prepareCall("{call INSERTDEPARTMENTHEAD(?,?,?,?)}");
		   createEmployeeStmt.setString(1,employee.getEmployeeName());
		   createEmployeeStmt.setString(2, employee.getEmployeeLastName());
		   createEmployeeStmt.setString(3, employee.getEmployeeEmail());
		   createEmployeeStmt.setString(4, employee.getEmployeePassword());
		   createEmployeeStmt.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}

	public Employee retrieveEmployeeByEmail(String email, Connection conn) {
		Employee employee = null;
		try{
			CallableStatement employeeStmt = conn.prepareCall("{? = call GETEMPLOYEEBYEMAIL(?)}");
			employeeStmt.registerOutParameter(1, OracleTypes.CURSOR);
			employeeStmt.setString(2, email);
			employeeStmt.execute();
			ResultSet rset = (ResultSet)employeeStmt.getObject(1);
			if(rset.next()){
			employee = new Employee(rset.getInt(1), 
					   rset.getString(2),  rset.getString(3), 
					   rset.getString(4),  rset.getString(5));
			}
			rset.close();
			employeeStmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return employee;
	}

	public Employee retrieveDirectSupervisorByEmail(String email, Connection conn) {
		Employee employee = null;
		try{
			CallableStatement employeeStmt = conn.prepareCall("{? = call GETDSBYEMAIL(?)}");
			employeeStmt.registerOutParameter(1, OracleTypes.CURSOR);
			employeeStmt.setString(2, email);
			employeeStmt.execute();
			ResultSet rset = (ResultSet)employeeStmt.getObject(1);
			rset.next();
			employee = new Employee(rset.getInt(1), 
					   rset.getString(2),  rset.getString(3), 
					   rset.getString(4),  rset.getString(5));
		}catch(SQLException e){
			e.printStackTrace();
		}
		return employee;
	}

	public Employee retrieveDirectManagerByEmail(String email, Connection conn) {
		Employee employee = null;
		try{
			CallableStatement employeeStmt = conn.prepareCall("{? = call GETDMBYEMAIL(?)}");
			employeeStmt.registerOutParameter(1, OracleTypes.CURSOR);
			employeeStmt.setString(2, email);
			employeeStmt.execute();
			ResultSet rset = (ResultSet)employeeStmt.getObject(1);
			rset.next();
			employee = new Employee(rset.getInt(1), 
					   rset.getString(2),  rset.getString(3), 
					   rset.getString(4),  rset.getString(5));
		}catch(SQLException e){
			e.printStackTrace();
		}
		return employee;
	}

	public Employee retrieveDepartmentHeadByEmail(String email, Connection conn) {
		Employee employee = null;
		try{
			CallableStatement employeeStmt = conn.prepareCall("{? = call GETDHBYEMAIL(?)}");
			employeeStmt.registerOutParameter(1, OracleTypes.CURSOR);
			employeeStmt.setString(2, email);
			employeeStmt.execute();
			ResultSet rset = (ResultSet)employeeStmt.getObject(1);
			rset.next();
			employee = new Employee(rset.getInt(1), 
					   rset.getString(2),  rset.getString(3), 
					   rset.getString(4),  rset.getString(5));
		}catch(SQLException e){
			e.printStackTrace();
		}
		return employee;
	}
	
	public Employee retrieveBencoByEmail(String email, Connection conn){
		Employee employee = null;
		try{
			CallableStatement employeeStmt = conn.prepareCall("{? = call GETBENCOBYEMAIL(?)}");
			employeeStmt.registerOutParameter(1, OracleTypes.CURSOR);
			employeeStmt.setString(2, email);
			employeeStmt.execute();
			ResultSet rset = (ResultSet)employeeStmt.getObject(1);
			if(rset.next()){
			employee = new Employee(rset.getInt(1), 
					   rset.getString(2),  rset.getString(3), 
					   rset.getString(4),  rset.getString(5));
			employee.setDirectSupervisor(rset.getInt(6));
			}
			rset.close();
			employeeStmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return employee;
	}	
}
