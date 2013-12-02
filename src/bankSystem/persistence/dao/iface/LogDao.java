package bankSystem.persistence.dao.iface;

import bankSystem.entity.*;

import java.util.ArrayList;
import java.util.Date;

public interface LogDao {
	ArrayList<Log> getLogListbyOperator(String operator);
	ArrayList<Log> getLogListbyAccountId(String accountId);
	//ArrayList<Log> getLogListByDate(Date date);
	ArrayList<Log> getLogListByDate(Date start, Date end);
	//ArrayList<Log> getLogListByOperatorDate(String operator, Date date);
	ArrayList<Log> getLogListByOperatorDate(String operator, Date start, Date end);
	//ArrayList<Log> getLogListByAccountIdDate(String accountId, Date date);
	ArrayList<Log> getLogListByAccountIdDate(String accountId, Date start, Date end);
	
	void insertLog(Log log);
}
