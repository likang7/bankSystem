package bankSystem.service;

import java.util.ArrayList;

import bankSystem.entity.Employee;
import bankSystem.entity.Log;

public class ReturnMsg {
	private Status status;
	private String msg;
	private Log log;
	private ArrayList<Log> logs;
	private ArrayList<Employee> employees;
	
	public ReturnMsg() {
		super();
		// TODO Auto-generated constructor stub
		status = Status.OK;
		msg = "";
		log = null;
		logs = null;
	}

	public ReturnMsg(Status status, String msg, Log log, ArrayList<Log> logs) {
		super();
		this.status = status;
		this.msg = msg;
		this.log = log;
		this.logs = logs;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public Log getLog() {
		return log;
	}
	
	public void setLog(Log log) {
		this.log = log;
	}

	public ArrayList<Log> getLogs() {
		return logs;
	}

	public void setLogs(ArrayList<Log> logs) {
		this.logs = logs;
	}
}
