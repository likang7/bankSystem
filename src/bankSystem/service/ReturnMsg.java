package bankSystem.service;

import bankSystem.entity.Log;

enum Status{OK, ERROR};

public class ReturnMsg {
	private Status status;
	private String msg;
	private Log log;
	
	public ReturnMsg() {
		super();
		// TODO Auto-generated constructor stub
		status = Status.OK;
		msg = "";
		log = null;
	}

	public ReturnMsg(Status status, String msg, Log log) {
		super();
		this.status = status;
		this.msg = msg;
		this.log = log;
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

}
