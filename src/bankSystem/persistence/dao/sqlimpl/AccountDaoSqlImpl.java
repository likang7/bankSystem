/**
 * 
 */
package bankSystem.persistence.dao.sqlimpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import bankSystem.entity.Account;
import bankSystem.entity.AccountType;
import bankSystem.persistence.dao.iface.AccountDao;

/**
 * @author LK
 *
 */
public class AccountDaoSqlImpl implements AccountDao {
	private DaoOperateTemplate daoTemplate = new DaoOperateTemplate();
	/* (non-Javadoc)
	 * @see bankSystem.persistence.dao.iface.AccountDao#getAccount(java.lang.String)
	 */
	@Override
	public Account getAccount(String id) {
		// TODO Auto-generated method stub
		try{
			String sql = "select * from account where id = ?";
			Object[] args = new Object[] {id};
			Account account = (Account) daoTemplate.find(sql, args, new AccountRowMapper());
			return account;
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see bankSystem.persistence.dao.iface.AccountDao#insertAccount(bankSystem.entity.Account)
	 */
	@Override
	public void insertAccount(Account account){
		// TODO Auto-generated method stub
		try{
			String sql = "insert into account(id, type, balance, openDate) values (?,?,?,?)";
			Object[] args = new Object[]{account.getId(), account.getType().toString(), account.getBalance(),
					new java.sql.Timestamp(account.getOpenDate().getTime())};
			daoTemplate.update(sql, args, false);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see bankSystem.persistence.dao.iface.AccountDao#deleteAccount(java.lang.String)
	 */
	@Override
	public void deleteAccount(String id) {
		// TODO Auto-generated method stub
		try{
			String sql = "delete from account where id = ?";
			Object[] args = new Object[]{id};
			daoTemplate.update(sql, args, false);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see bankSystem.persistence.dao.iface.AccountDao#updateAccount(bankSystem.entity.Account)
	 */
	@Override
	public void updateAccount(Account account) {
		// TODO Auto-generated method stub
		try{
			String sql = "update account set balance=? where id=?";
			Object[] args = new Object[]{account.getBalance(), account.getId()};
			daoTemplate.update(sql, args, false);
		}catch (Exception e){
			e.printStackTrace();
		}

	}
	
	class AccountRowMapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs) throws SQLException {
			// TODO Auto-generated method stub
			Account account = new Account(rs.getString("id"), rs.getString("type"),
					rs.getDouble("balance") ,rs.getDate("openDate"));
			return account;
		}
		
	}

}
