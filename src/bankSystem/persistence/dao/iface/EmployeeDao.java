package bankSystem.persistence.dao.iface;

import java.util.ArrayList;

import bankSystem.entity.*;

public interface EmployeeDao {
	Employee getEmployee(String username);
	Employee getEmployee(String username, String password);
	ArrayList<Employee> getEmployeesByDepartmentId(String departmentId);
	ArrayList<Employee> getEmployeesByDepartmentIdSuperiorId(String departmentId, String superiorId);
	ArrayList<Employee> getSubordinates(String username);
	ArrayList<Employee> getAllEmployees();
	
	void insertEmployee(Employee employee);
	void updateEmployee(Employee employee);
	void deleteEmployee(String username);
}
