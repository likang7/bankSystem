package bankSystem.service;

import java.util.ArrayList;
import java.util.Date;

import bankSystem.entity.Employee;
import bankSystem.entity.Log;
import bankSystem.entity.Position;
import bankSystem.persistence.dao.DaoFactory;
import bankSystem.persistence.dao.iface.*;

public class DepartmentService {
	private DepartmentDao departmentDao;
	private EmployeeDao employeeDao;
	private LogDao logDao;
	
	public DepartmentService() {
		super();
		// TODO Auto-generated constructor stub
		try{
			departmentDao = (DepartmentDao)DaoFactory.getInstance().getDao("DepartmentDao");
			employeeDao = (EmployeeDao)DaoFactory.getInstance().getDao("EmployeeDao");
			logDao = (LogDao)DaoFactory.getInstance().getDao("LogDao");
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public ReturnMsg logIn(String username, String password){
		ReturnMsg returnMsg = new ReturnMsg();
		
		if(employeeDao.getEmployee(username, password) == null){
			returnMsg.setStatus(Status.ERROR);
			returnMsg.setMsg("user not exist or password error");
		}
		else{
			returnMsg.setStatus(Status.OK);
		}
		
		return returnMsg;
	}
	
	public ReturnMsg addEmployee(String username, String password, String departmentId, Position position, String superiorId){
		ReturnMsg returnMsg = new ReturnMsg();
		returnMsg.setStatus(Status.ERROR);
		if(employeeDao.getEmployee(username) != null){
			returnMsg.setMsg("username has existed.");
			return returnMsg;
		}
		
		if(departmentDao.getDepartment(departmentId) == null){
			returnMsg.setMsg("department not exist.");
			return returnMsg;
		}
		
		if(employeeDao.getEmployee(superiorId) == null){
			returnMsg.setMsg("superior not exist.");
			return returnMsg;
		}
		
		Employee employee = new Employee(username, password, position, departmentId, superiorId);
		employeeDao.insertEmployee(employee);
		
		returnMsg.setStatus(Status.OK);
		
		return returnMsg;
	}
	
	public ReturnMsg deleteEmployee(String username){
		ReturnMsg returnMsg = new ReturnMsg();
		returnMsg.setStatus(Status.ERROR);
		if(employeeDao.getEmployee(username) == null){
			returnMsg.setMsg("username not existed.");
			return returnMsg;
		}
		
		employeeDao.deleteEmployee(username);
		returnMsg.setStatus(Status.OK);
		
		return returnMsg;
	}
	
	public ArrayList<Log> getEmployeeLog(String username, Date start, Date end){
		ArrayList<Log> logs = logDao.getLogListByOperatorDate(username, start, end);
		return logs;
	}
	
	/*public ArrayList<Employee> getSubordinates(String departmentId, String superiorId){
		ArrayList<Employee> subordinates = employeeDao.getEmployeesByDepartmentIdSuperiorId(departmentId, superiorId); 
		return subordinates;
	}*/
	
	/*public ArrayList<Employee> getSubordinatesByDepartmentId(String departmentId){
		ArrayList<Employee> subordinates = employeeDao.getEmployeesByDepartmentId(departmentId);
		return subordinates;
	}*/
	
	public ArrayList<Employee> getUserAndSubordinatesByUsername(String username){
		Employee employee = employeeDao.getEmployee(username);
		Position position = employee.getPosition();
		ArrayList<Employee> results = new ArrayList<Employee>();
		if(position.equals(Position.Administrator)){
			results.addAll(employeeDao.getAllEmployees());
		}
		else if(position.equals(Position.Supervisor)){
			results.addAll(employeeDao.getEmployeesByDepartmentId(employee.getDepartmentId()));
		}
		else if(position.equals(Position.Manager)){
			results.add(employeeDao.getEmployee(username));
			results.addAll(employeeDao.getSubordinates(username));
		}
		else if(position.equals(Position.Operator)){
			results.add(employeeDao.getEmployee(username));
		}
		
		return results;
	}
	
	public ArrayList<Log> getAllLogs(String departmentId, String superiorId, Date start, Date end){
		ArrayList<Log> logs = new ArrayList<Log>();
		ArrayList<Employee> subordinates = employeeDao.getEmployeesByDepartmentIdSuperiorId(departmentId, superiorId); 
		for(Employee subordinate : subordinates){
			logs.addAll(getEmployeeLog(subordinate.getUsername(), start, end));
		}
		return logs;
	}
	
	
	
	public static void main(String args[]){
		//Employee employee = new Employee("root", "123456", Position.Administrator, "0001");
		//employeeDao.insertEmployee(employee);
	}
}
