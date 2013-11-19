package bankSystem.persistence.dao.impl;
import java.util.ArrayList;

import bankSystem.entity.Department;
import bankSystem.persistence.dao.iface.DepartmentDao;

public class DepartmentDaoImpl implements DepartmentDao {
	private static ArrayList<Department> departments = new ArrayList<Department>();
	
	@Override
	public Department getDepartment(String id) {
		// TODO Auto-generated method stub
		for(Department department : departments){
			if(department.getId().equals(id))
				return department;
		}
		return null;
	}

	@Override
	public void insertDepartment(Department department) {
		// TODO Auto-generated method stub
		departments.add(department);
	}

}
