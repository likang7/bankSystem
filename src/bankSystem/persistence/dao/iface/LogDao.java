package bankSystem.persistence.dao.iface;

import bankSystem.entity.*;

import java.util.ArrayList;

public interface LogDao {
	ArrayList<Log> getLogListbyOperator(String operator);
	ArrayList<Log> getLogListbyAccountId(String accountId);
	
	void insertLog(Log log);
}
