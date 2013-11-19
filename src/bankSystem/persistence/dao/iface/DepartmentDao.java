package bankSystem.persistence.dao.iface;

import bankSystem.entity.*;

public interface DepartmentDao {
	Department getDepartment(String id);
	
	void insertDepartment(Department department);
}
