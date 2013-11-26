package bankSystem.persistence.dao.impl;
import java.util.ArrayList;

import bankSystem.entity.Department;
import bankSystem.persistence.dao.iface.DepartmentDao;

public class DepartmentDaoImpl extends basicPersistence implements DepartmentDao {
	private ArrayList<Department> departments = new ArrayList<Department>();
	private static String persistencePath = persistenceRoot + "/departments.obj";

	
	public DepartmentDaoImpl() throws Exception{
		super();
		// TODO Auto-generated constructor stub
		ArrayList<Object> objects = readObject(persistenceRoot, persistencePath);
		for(Object object : objects){
			departments.add((Department)object);
		}	
		if(getDepartment("1") == null){
			insertDepartment(new Department("1", "individual"));
		}
		if(getDepartment("2") == null){
			insertDepartment(new Department("2", "vip"));
		}
	}
	
	protected void save() throws Exception{
		ArrayList<Object> objects = new ArrayList<Object>();
		objects.addAll(departments);
		super.writeObject(persistenceRoot, persistencePath, objects);
	}
	
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
		try{
			save();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
