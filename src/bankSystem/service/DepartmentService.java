package bankSystem.service;

import bankSystem.persistence.dao.DaoFactory;
import bankSystem.persistence.dao.iface.*;

public class DepartmentService {
	private DepartmentDao departmentDao;
	private EmployeeDao employeeDao;
	
	public DepartmentService() {
		super();
		// TODO Auto-generated constructor stub
		try{
			departmentDao = (DepartmentDao)DaoFactory.getInstance().getDao("DepartmentDao");
			employeeDao = (EmployeeDao)DaoFactory.getInstance().getDao("EmployeeDao");
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
	
	
	public static void main(String args[]){
		//Employee employee = new Employee("root", "123456", Position.Administrator, "0001");
		//employeeDao.insertEmployee(employee);
	}
}
