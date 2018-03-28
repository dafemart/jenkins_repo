package daos;

import java.sql.Connection;

import Entities.Employee;

public interface EmployeeDao {
	public void createEmployee(Employee employee, Connection conn);
    public void createDirectSupervisor(Employee employee, Connection conn);
    public void createDirectManager(Employee employee, Connection conn);
    public void createDepartmentHead(Employee employee, Connection conn);
    public void createBenco(Employee employee, Connection conn);
    public Employee retrieveEmployeeByEmail(String email, Connection conn);
    public Employee retrieveDirectSupervisorByEmail(String email, Connection conn);
    public Employee retrieveDirectManagerByEmail(String email, Connection conn);
    public Employee retrieveDepartmentHeadByEmail(String email, Connection conn);
    public Employee retrieveBencoByEmail(String email, Connection conn);
}
