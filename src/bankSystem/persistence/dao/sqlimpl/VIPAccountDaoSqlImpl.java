package bankSystem.persistence.dao.sqlimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bankSystem.entity.EnterpriseAccount;
import bankSystem.entity.VIPAccount;
import bankSystem.persistence.dao.iface.VIPAccountDao;
import bankSystem.persistence.dao.sqlimpl.EnterpriseAccountDaoSqlImpl.EnterpriseAccountRowMapper;

public class VIPAccountDaoSqlImpl implements VIPAccountDao {
	private DaoOperateTemplate daoTemplate = new DaoOperateTemplate();
	@Override
	public VIPAccount getAccount(String id) {
		// TODO Auto-generated method stub
		try{
			String sql = "select * from vipaccount where id=?";
			Object[] args = new Object[]{id};
			VIPAccount va = (VIPAccount)daoTemplate.find(sql, args, new VIPAccountRowMapper());
			return va;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insertAccount(VIPAccount account) {
		// TODO Auto-generated method stub
		try{
			String sql = "insert into vipaccount(id,`type`,balance,"
					+ "openDate, excessStart, isFrozen, accBalanceThisMonth,"
					+ "accFailMonths) values (?,?,?,?,?,?,?,?)";
			Object[] args = new Object[]{account.getId(), account.getBalance(),account.getBalance(),
					new Timestamp(account.getOpenDate().getTime()), new Timestamp(account.getExcessStart().getTime())
					, account.isFrozen(), account.getAccBalanceThisMonth(), account.getAccFailMonths()};
			daoTemplate.update(sql, args, false);
		}catch (Exception e){
			e.printStackTrace();
		}

	}

	@Override
	public void deleteAccount(String id) {
		// TODO Auto-generated method stub
		try{
			String sql = "delete from vipaccount where id=?";
			Object[] args = new Object[]{id};
			daoTemplate.update(sql, args, false);	
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void updateAccount(VIPAccount account) {
		// TODO Auto-generated method stub
		try{
			String sql = "update vipaccount set balance=?, excessStart=?, "
					+ "isFrozen=?, accBalanceThisMonth=?, accFailMonths=? where "
					+ "id=?";
			Object[] args = new Object[]{account.getBalance(),
					new Timestamp(account.getExcessStart().getTime()),
					account.isFrozen(), account.getAccBalanceThisMonth(),
					account.getAccFailMonths(), account.getId()};
			daoTemplate.update(sql, args, false);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<VIPAccount> getAllAccounts() {
		// TODO Auto-generated method stub
		try{
			String sql = "select * from vipaccount";
			Object[] args = new Object[]{};
			
			List<Object> os = daoTemplate.query(sql, args, new VIPAccountRowMapper());
			ArrayList<VIPAccount> as = new ArrayList<VIPAccount>();
			for(Object o : os){
				as.add((VIPAccount)o);
			}
			return as;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	class VIPAccountRowMapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs) throws SQLException {
			// TODO Auto-generated method stub
			VIPAccount va = new VIPAccount(
					rs.getString("id"),
					rs.getString("type"),
					rs.getDouble("balance"),
					new Date(rs.getTimestamp("openDate").getTime()),
					new Date(rs.getTimestamp("excessStart").getTime()),
					rs.getBoolean("isFrozen"),
					rs.getDouble("accBalanceThisMonth"),
					rs.getShort("accFailMonths"));
			return va;
		}
		
	}
}
