package Entities;

public class Employee {
	private int EmployeeID;
	private String EmployeeName;
	private String EmployeeLastName;
	private String EmployeeEmail;
	private String EmployeePassword;
	private int DirectSupervisorID;
	
	public Employee(int EmployeeID, 
			        String EmployeeName, 
			        String EmployeeLastName,
			        String EmployeeEmail, 
			        String EmployeePassword){
		
		this.EmployeeID = EmployeeID;
		this.EmployeeName = EmployeeName;
	    this.EmployeeLastName= EmployeeLastName;
		this.EmployeeEmail = EmployeeEmail;
		this.EmployeePassword = EmployeePassword;
	}
	
	public void setEmployeeID(int ID){
		this.EmployeeID = ID;
	}
	
	public void setDirectSupervisor(int DirectSupervisorID){
		this.DirectSupervisorID = DirectSupervisorID;
	}
	
	public int getDirectSupervisorID(){
		return DirectSupervisorID;
	}
	
	public int getEmployeeID(){
		return this.EmployeeID;
	}
	
	public String getEmployeeName(){
		return this.EmployeeName;
	}
	
	public String getEmployeeLastName(){
		return this.EmployeeLastName;
	}
	
	public String getEmployeeEmail(){
		return this.EmployeeEmail;
	}
	
	public String getEmployeePassword(){
		return this.EmployeePassword;
	}
	
}
