/**
 * 
 */
package bankSystem.persistence.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;

import bankSystem.entity.Employee;
import bankSystem.persistence.dao.iface.EmployeeDao;

/**
 * @author LK
 *
 */
public class EmployeeDaoImpl implements EmployeeDao {
	private static ArrayList<Employee> employees = new ArrayList<Employee>();

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
				break;
			}
		}
	}

}
