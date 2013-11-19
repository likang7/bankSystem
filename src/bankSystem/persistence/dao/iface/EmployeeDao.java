package bankSystem.persistence.dao.iface;

import bankSystem.entity.*;

public interface EmployeeDao {
	Employee getEmployee(String username);
	Employee getEmployee(String username, String password);
	
	void insertEmployee(Employee employee);
	void updateEmployee(Employee employee);
	void deleteEmployee(String username);
}
