package bankSystem.persistence.dao.sqlimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bankSystem.entity.Enterprise;
import bankSystem.entity.EnterpriseAccount;
import bankSystem.persistence.dao.iface.EnterpriseAccountDao;

public class EnterpriseAccountDaoSqlImpl implements EnterpriseAccountDao {
	private DaoOperateTemplate daoTemplate = new DaoOperateTemplate();
	@Override
	public EnterpriseAccount getAccount(String id) {
		// TODO Auto-generated method stub
		try{
			String sql = "select * from enterpriseaccount where id=?";
			Object[] args = new Object[]{id};
			EnterpriseAccount ea = (EnterpriseAccount)daoTemplate.find(sql, args, 
					new EnterpriseAccountRowMapper());
			return ea;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insertAccount(EnterpriseAccount account) {
		// TODO Auto-generated method stub
		try{
			String sql = "insert into enterpriseaccount(id, type, balance, "
					+ "openDate, enterpriseId) "
					+ "values (?,?,?,?,?)";
			Object[] args = new Object[]{account.getId(), account.getType().toString(),
					account.getBalance(), 
					new java.sql.Timestamp(account.getOpenDate().getTime()), 
					account.getEnterpriseId()};
			daoTemplate.update(sql, args, false);
		}catch (Exception e){
			e.printStackTrace();
		}

	}

	@Override
	public void deleteAccount(String id) {
		// TODO Auto-generated method stub
		try{
			String sql = "delete from enterpriseaccount where id=?";
			Object[] args = new Object[]{id};
			daoTemplate.update(sql, args, false);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void updateAccount(EnterpriseAccount account) {
		// TODO Auto-generated method stub
		try{
			String sql = "update enterpriseaccount set balance=? where id=?";
			Object[] args = new Object[]{account.getBalance(), account.getId()};
			daoTemplate.update(sql, args, false);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<EnterpriseAccount> getAccountsByEnterpriseId(
			String enterpriseId) {
		// TODO Auto-generated method stub
		try{
			String sql = "select * from enterpriseaccount where enterpriseId=?";
			Object[] args = new Object[]{enterpriseId};
			
			List<Object> os = daoTemplate.query(sql, args, new EnterpriseAccountRowMapper());
			ArrayList<EnterpriseAccount> as = new ArrayList<EnterpriseAccount>();
			for(Object o : os){
				as.add((EnterpriseAccount)o);
			}
			return as;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	class EnterpriseAccountRowMapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs) throws SQLException {
			// TODO Auto-generated method stub
			EnterpriseAccount ea = new EnterpriseAccount(
					rs.getString("id"),
					rs.getString("type"),
					rs.getDouble("balance"),
					new Date(rs.getTimestamp("openDate").getTime()),
					rs.getString("enterpriseId"));
			return ea;
		}
		
	}

}
