package bankSystem.persistence.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import bankSystem.entity.Log;
import bankSystem.persistence.dao.iface.LogDao;

public class LogDaoImpl implements LogDao {
	private static ArrayList<Log> logs = new ArrayList<Log>();
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
	
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
	public ArrayList<Log> getLogListByDate(Date date) {
		// TODO Auto-generated method stub
		ArrayList<Log> aLogs = new ArrayList<Log>();
		String dateStr = simpleDateFormat.format(date);
		
		for(Log log : logs){
			String logDateStr = simpleDateFormat.format(log.getTime());
			if(logDateStr.equals(dateStr))
				aLogs.add(log);
		}
		return aLogs;
	}

	@Override
	public ArrayList<Log> getLogListByDate(Date start, Date end) {
		// TODO Auto-generated method stub
		ArrayList<Log> aLogs = new ArrayList<Log>();
		
		for(Log log : logs){
			if((log.getTime().after(start) && log.getTime().before(end)) ||
					(log.getTime().equals(end)))
				aLogs.add(log);
		}
		return aLogs;
	}

	@Override
	public ArrayList<Log> getLogListByOperatorDate(String operator, Date date) {
		// TODO Auto-generated method stub
		ArrayList<Log> aLogs = new ArrayList<Log>();
		String dateStr = simpleDateFormat.format(date);
		
		for(Log log : logs){
			String logDateStr = simpleDateFormat.format(log.getTime());
			if(logDateStr.equals(dateStr) && log.getOperator().equals(operator))
				aLogs.add(log);
		}
		return aLogs;
	}

	@Override
	public ArrayList<Log> getLogListByOperatorDate(String operator, Date start,
			Date end) {
		// TODO Auto-generated method stub
		ArrayList<Log> aLogs = new ArrayList<Log>();
		
		for(Log log : logs){
			if(((log.getTime().after(start) && log.getTime().before(end)) ||
					(log.getTime().equals(end)))
					&& log.getOperator().equals(operator))
				aLogs.add(log);
		}
		return aLogs;
	}

	@Override
	public ArrayList<Log> getLogListByAccountIdDate(String accountId, Date date) {
		// TODO Auto-generated method stub
		ArrayList<Log> aLogs = new ArrayList<Log>();
		String dateStr = simpleDateFormat.format(date);
		
		for(Log log : logs){
			String logDateStr = simpleDateFormat.format(log.getTime());
			if(logDateStr.equals(dateStr) && log.getAccountId().equals(accountId))
				aLogs.add(log);
		}
		return aLogs;
	}

	@Override
	public ArrayList<Log> getLogListByAccountIdDate(String accountId,
			Date start, Date end) {
		// TODO Auto-generated method stub
		ArrayList<Log> aLogs = new ArrayList<Log>();
		
		for(Log log : logs){
			if(((log.getTime().after(start) && log.getTime().before(end)) ||
					(log.getTime().equals(end)))
					&& log.getAccountId().equals(accountId))
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
