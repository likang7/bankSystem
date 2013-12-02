package bankSystem.persistence.dao.sqlimpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import bankSystem.entity.EnterpriseUser;
import bankSystem.persistence.dao.iface.EnterpriseUserDao;
import bankSystem.persistence.dao.sqlimpl.EnterpriseDaoSqlImpl.EnterpriseRowMapper;

public class EnterpriseUserDaoSqlImpl implements EnterpriseUserDao {
	private DaoOperateTemplate daoTemplate = new DaoOperateTemplate();
	@Override
	public EnterpriseUser getEnterpriseUser(String userid) {
		// TODO Auto-generated method stub
		try{
			String sql = "select * from enterpriseuser where userid=?";
			Object[] args = new Object[]{userid};
			EnterpriseUser eu = (EnterpriseUser)daoTemplate.find(sql, args, new EnterpriseUserRowMapper());
			return eu;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insertUser(EnterpriseUser user) {
		// TODO Auto-generated method stub
		try{
			String sql = "insert into enterpriseuser(id,name,enterpriseId, isSuper)"
					+ " values (?,?,?,?)";
			Object[] args = new Object[]{user.getId(), user.getName(), user.getEnterpriseId(),
					user.isSuper()};
			daoTemplate.update(sql, args, false);
		}catch (Exception e){
			e.printStackTrace();
		}

	}

	@Override
	public void deleteUser(String userid) {
		// TODO Auto-generated method stub
		try{
			String sql = "delete from enterpriseuser where userid=?";
			Object[] args = new Object[]{userid};
			daoTemplate.update(sql, args, false);
		}catch (Exception e){
			e.printStackTrace();
		}

	}
	
	class EnterpriseUserRowMapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs) throws SQLException {
			// TODO Auto-generated method stub
			EnterpriseUser eu = new EnterpriseUser(
					rs.getString("id"),
					rs.getString("name"),
					rs.getString("enterpriseId"),
					rs.getBoolean("isSuper"));
			return eu;
		}
		
	}

}
