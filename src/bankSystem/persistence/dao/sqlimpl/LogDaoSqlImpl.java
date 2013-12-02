package bankSystem.persistence.dao.sqlimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bankSystem.entity.Log;
import bankSystem.persistence.dao.iface.LogDao;

public class LogDaoSqlImpl implements LogDao {
	private DaoOperateTemplate daoTemplate = new DaoOperateTemplate();
	@Override
	public ArrayList<Log> getLogListbyOperator(String operator) {
		// TODO Auto-generated method stub
		try{
			String sql = "select * from log where operator=?";
			Object[] args = new Object[]{operator};
			List<Object> os = daoTemplate.query(sql, args, new LogRowMapper());
			ArrayList<Log> logs = new ArrayList<Log>();
			for(Object o : os){
				logs.add((Log)o);
			}
			return logs;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Log> getLogListbyAccountId(String accountId) {
		// TODO Auto-generated method stub
		try{
			String sql = "select * from log where accountId=?";
			Object[] args = new Object[]{accountId};
			List<Object> os = daoTemplate.query(sql, args, new LogRowMapper());
			ArrayList<Log> logs = new ArrayList<Log>();
			for(Object o : os){
				logs.add((Log)o);
			}
			return logs;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/*@Override
	public ArrayList<Log> getLogListByDate(Date date) {
		// TODO Auto-generated method stub
		try{
			String sql = "select * from log where time=?";
			Object[] args = new Object[]{new Timestamp(date.getTime())};
			List<Object> os = daoTemplate.query(sql, args, new LogRowMapper());
			ArrayList<Log> logs = new ArrayList<Log>();
			for(Object o : os){
				logs.add((Log)o);
			}
			return logs;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}*/

	@Override
	public ArrayList<Log> getLogListByDate(Date start, Date end) {
		// TODO Auto-generated method stub
		try{
			String sql = "select * from log where `time` between ? and ?";
			Object[] args = new Object[]{new Timestamp(start.getTime()), 
					new Timestamp(end.getTime())};
			List<Object> os = daoTemplate.query(sql, args, new LogRowMapper());
			ArrayList<Log> logs = new ArrayList<Log>();
			for(Object o : os){
				logs.add((Log)o);
			}
			return logs;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/*@Override
	public ArrayList<Log> getLogListByOperatorDate(String operator, Date date) {
		// TODO Auto-generated method stub
		try{
			String sql = "";
			Object[] args = new Object[]{};
			List<Object> os = daoTemplate.query(sql, args, new LogRowMapper());
			ArrayList<Log> logs = new ArrayList<Log>();
			for(Object o : os){
				logs.add((Log)o);
			}
			return logs;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}*/

	@Override
	public ArrayList<Log> getLogListByOperatorDate(String operator, Date start,
			Date end) {
		// TODO Auto-generated method stub
		try{
			String sql = "select * from log where operator=? and `time` between ? and ?";
			Object[] args = new Object[]{operator, new Timestamp(start.getTime()),
					new Timestamp(end.getTime())};
			List<Object> os = daoTemplate.query(sql, args, new LogRowMapper());
			ArrayList<Log> logs = new ArrayList<Log>();
			for(Object o : os){
				logs.add((Log)o);
			}
			return logs;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/*@Override
	public ArrayList<Log> getLogListByAccountIdDate(String accountId, Date date) {
		// TODO Auto-generated method stub
		try{
			String sql = "";
			Object[] args = new Object[]{};
			List<Object> os = daoTemplate.query(sql, args, new LogRowMapper());
			ArrayList<Log> logs = new ArrayList<Log>();
			for(Object o : os){
				logs.add((Log)o);
			}
			return logs;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}*/

	@Override
	public ArrayList<Log> getLogListByAccountIdDate(String accountId,
			Date start, Date end) {
		// TODO Auto-generated method stub
		try{
			String sql = "select * from log where accountId=? and `time` between ? and ?";
			Object[] args = new Object[]{accountId, new Timestamp(start.getTime()),
					new Timestamp(end.getTime())};
			List<Object> os = daoTemplate.query(sql, args, new LogRowMapper());
			ArrayList<Log> logs = new ArrayList<Log>();
			for(Object o : os){
				logs.add((Log)o);
			}
			return logs;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insertLog(Log log) {
		// TODO Auto-generated method stub
		try{
			String sql = "insert into log(`time`,operation,operator,cardId,"
					+ "accountId, accountType, income, expenditure, balance) values"
					+ " (?,?,?,?,?,?,?,?,?)";
			Object[] args = new Object[]{new Timestamp(log.getTime().getTime()),
					log.getOperation(), log.getOperator(), log.getCardId(),
					log.getAccountId(), log.getAccountType(), log.getIncome(),
					log.getExpenditure(), log.getBalance()};
			daoTemplate.update(sql, args, false);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	class LogRowMapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs) throws SQLException {
			// TODO Auto-generated method stub
			Log log = new Log(
					new Date(rs.getTimestamp("time").getTime()),
					rs.getString("operation"),
					rs.getString("operator"),
					rs.getString("cardId"),
					rs.getString("accountId"),
					rs.getString("accountType"),
					rs.getDouble("income"),
					rs.getDouble("expenditure"),
					rs.getDouble("balance"));
			return log;
		}
		
	}
}
