/**
 * 
 */
package bankSystem.persistence.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;

import bankSystem.entity.Employee;
import bankSystem.entity.Position;
import bankSystem.persistence.dao.iface.EmployeeDao;

/**
 * @author LK
 *
 */
public class EmployeeDaoImpl extends basicPersistence implements EmployeeDao {
	private ArrayList<Employee> employees = new ArrayList<Employee>();
	private static String persistencePath = persistenceRoot + "/employees.obj";

	public EmployeeDaoImpl() throws Exception{
		ArrayList<Object> objects = readObject(persistenceRoot, persistencePath);
		for(Object object : objects){
			employees.add((Employee)object);
		}
		
		if(getEmployee("root") == null){
			insertEmployee(new Employee("root", "123456", Position.Administrator, null, null));
		}
		save();
	}
	
	protected void save() throws Exception{
		ArrayList<Object> objects = new ArrayList<Object>();
		objects.addAll(employees);
		super.writeObject(persistenceRoot, persistencePath, objects);
	}

	@Override
	public Employee getEmployee(String username) {
		// TODO Auto-generated method stub
		for(Employee e : employees){
			if (e.getUsername().equals(username))
				return e;
		}
		return null;
	}

	@Override
	public Employee getEmployee(String username, String password) {
		// TODO Auto-generated method stub
		for(Employee e : employees){
			if (e.getUsername().equals(username) && e.getPassword().equals(password))
				return e;
		}
		return null;
	}

	@Override
	public void insertEmployee(Employee employee) {
		// TODO Auto-generated method stub
		employees.add(employee);
		try{
			save();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		deleteEmployee(employee.getUsername());
		insertEmployee(employee);
	}

	@Override
	public void deleteEmployee(String username) {
		// TODO Auto-generated method stub
		Iterator<Employee> it = employees.iterator();
		while(it.hasNext()){
			Employee e = it.next();
			if(e.getUsername().equals(username)){
				it.remove();
				try{
					save();
				}catch (Exception ex){
					ex.printStackTrace();
				}
				break;
			}
		}
	}

	@Override
	public ArrayList<Employee> getEmployeesByDepartmentId(String departmentId) {
		// TODO Auto-generated method stub
		ArrayList<Employee> results = new ArrayList<Employee>();
		for(Employee e : employees){
			if(e.getDepartmentId().equals(departmentId))
				results.add(e);
		}
		return results;
	}

	@Override
	public ArrayList<Employee> getEmployeesByDepartmentIdSuperiorId(
			String departmentId, String superiorId) {
		// TODO Auto-generated method stub
		ArrayList<Employee> results = new ArrayList<Employee>();
		for(Employee e : employees){
			if(e.getDepartmentId().equals(departmentId) && e.getSuperiorId().equals(superiorId))
				results.add(e);
		}
		return results;
	}

	@Override
	public ArrayList<Employee> getSubordinates(String username) {
		// TODO Auto-generated method stub
		Employee employee = this.getEmployee(username);
		String departmentId = employee.getDepartmentId();
		
		return this.getEmployeesByDepartmentIdSuperiorId(departmentId, username);
	}

	@Override
	public ArrayList<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return (ArrayList<Employee>) employees.clone();
	}
	
	
}
