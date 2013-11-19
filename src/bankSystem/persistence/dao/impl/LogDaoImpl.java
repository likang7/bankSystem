package bankSystem.persistence.dao.impl;

import java.util.ArrayList;

import bankSystem.entity.Log;
import bankSystem.persistence.dao.iface.LogDao;

public class LogDaoImpl implements LogDao {
	private static ArrayList<Log> logs = new ArrayList<Log>();
	
	@Override
	public ArrayList<Log> getLogListbyOperator(String operator) {
		// TODO Auto-generated method stub
		ArrayList<Log> oLogs = new ArrayList<Log>();
		for(Log log : logs){
			if(log.getOperator().equals(operator))
				oLogs.add(log);
		}
		return oLogs;
	}

	@Override
	public ArrayList<Log> getLogListbyAccountId(String accountId) {
		// TODO Auto-generated method stub
		ArrayList<Log> aLogs = new ArrayList<Log>();
		for(Log log : logs){
			if(log.getAccountId().equals(accountId))
				aLogs.add(log);
		}
		return aLogs;
	}

	@Override
	public void insertLog(Log log) {
		// TODO Auto-generated method stub
		logs.add(log);
	}

}
