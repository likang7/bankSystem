package bankSystem.persistence.dao.sqlimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bankSystem.entity.Employee;
import bankSystem.persistence.dao.iface.EmployeeDao;

public class EmployeeDaoSqlImpl implements EmployeeDao {
	private DaoOperateTemplate daoTemplate = new DaoOperateTemplate();
	@Override
	public Employee getEmployee(String username) {
		// TODO Auto-generated method stub
		try{
			String sql = "select * from employee where username=?";
			Object[] args = new Object[]{username};
			Employee employee = (Employee)daoTemplate.find(sql, args, new EmployeeRowMapper());
			return employee;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Employee getEmployee(String username, String password) {
		// TODO Auto-generated method stub
		try{
			String sql = "select * from employee where username=? and password=?";
			Object[] args = new Object[]{username, password};
			Employee employee = (Employee)daoTemplate.find(sql, args, new EmployeeRowMapper());
			return employee;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Employee> getEmployeesByDepartmentId(String departmentId) {
		// TODO Auto-generated method stub
		try{
			String sql = "select * from employee where departmentId=?";
			Object[] args = new Object[]{departmentId};
			List<Object> oemployees = daoTemplate.query(sql, args, new EmployeeRowMapper());
			ArrayList<Employee> employees = new ArrayList<Employee>();
			for(Object o : oemployees){
				employees.add((Employee)o);
			}
			return employees;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Employee> getEmployeesByDepartmentIdSuperiorId(
			String departmentId, String superiorId) {
		// TODO Auto-generated method stub
		try{
			String sql = "select * from employee where departmentId=? and superiorId=?";
			Object[] args = new Object[]{departmentId, superiorId};
			List<Object> oemployees = daoTemplate.query(sql, args, new EmployeeRowMapper());
			ArrayList<Employee> employees = new ArrayList<Employee>();
			for(Object o : oemployees){
				employees.add((Employee)o);
			}
			return employees;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Employee> getSubordinates(String username) {
		// TODO Auto-generated method stub
		try{
			Employee employee = getEmployee(username);
			String departmentId = employee.getDepartmentId();
			
			String sql = "select * from employee where superiorId=? and departmentId=?";
			Object[] args = new Object[]{username, departmentId};
			List<Object> osubs = daoTemplate.query(sql, args, new EmployeeRowMapper());
			ArrayList<Employee> asubs = new ArrayList<Employee>();
			for (Object o : osubs){
				asubs.add((Employee)o);
			}
			return asubs;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		try{
			String sql = "select * from employee";
			Object[] args = new Object[]{};
			List<Object> os = daoTemplate.query(sql, args, new EmployeeRowMapper());
			ArrayList<Employee> employees = new ArrayList<Employee>();
			for(Object o : os){
				employees.add((Employee)o);
			}
			return employees;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insertEmployee(Employee employee) {
		// TODO Auto-generated method stub
		try{
			String sql = "insert into employee(username, password, position, departmentId, superiorId) values (?,?,?,?,?)";
			Object[] args = new Object[]{employee.getUsername(), employee.getPassword(), employee.getPosition().toString(),
					employee.getDepartmentId(), employee.getSuperiorId()};
			daoTemplate.update(sql, args, false);
		}catch (Exception e){
			e.printStackTrace();
		}

	}

	@Override
	public void updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		try{
			String sql = "update employee set password=?, position=?,departmentId=?,superiorId=? where username=?";
			Object[] args = new Object[]{employee.getPassword(), employee.getPosition().toString(),
					employee.getDepartmentId(), employee.getSuperiorId(), employee.getUsername()};
			daoTemplate.update(sql, args, false);
		}catch (Exception e){
			e.printStackTrace();
		}

	}

	@Override
	public void deleteEmployee(String username) {
		// TODO Auto-generated method stub
		try{
			String sql = "delete from employee where username=?";
			Object[] args = new Object[]{username};
			daoTemplate.update(sql, args, false);
		}catch (Exception e){
			e.printStackTrace();
		}

	}
	
	class EmployeeRowMapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs) throws SQLException {
			// TODO Auto-generated method stub
			Employee e = new Employee(
					rs.getString("username"),
					rs.getString("password"),
					rs.getString("position"),
					rs.getString("departmentId"),
					rs.getString("superiorId"));
			return e;
		}
		
	}
}
