package bankSystem.persistence.dao.sqlimpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import bankSystem.entity.Enterprise;
import bankSystem.persistence.dao.iface.EnterpriseDao;

public class EnterpriseDaoSqlImpl implements EnterpriseDao {
	private DaoOperateTemplate daoTemplate = new DaoOperateTemplate();
	@Override
	public Enterprise getEnterprise(String id) {
		// TODO Auto-generated method stub
		try{
			String sql = "select * from enterprise where id=?";
			Object[] args = new Object[]{id};
			Enterprise enterprise = (Enterprise)daoTemplate.find(sql, args, new EnterpriseRowMapper());
			return enterprise;
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insertEnterprise(Enterprise enterprise) {
		// TODO Auto-generated method stub
		try{
			String sql = "insert into enterprise(id, enterpriseName) values (?,?)";
			Object[] args = new Object[]{enterprise.getId(), enterprise.getEnterpriseName()};
			daoTemplate.update(sql, args, false);
		}catch (Exception e){
			e.printStackTrace();
		}

	}

	@Override
	public void deleteEnterprise(String id) {
		// TODO Auto-generated method stub
		try{
			String sql = "delete from enterprise where id=?";
			Object[] args = new Object[]{id};
			daoTemplate.update(sql, args, false);
		}catch (Exception e){
			e.printStackTrace();
		}

	}

	@Override
	public void updateEnterprise(Enterprise enterprise) {
		// TODO Auto-generated method stub
		try{
			String sql = "update enterprise set enterpriseName=? where id=?";
			Object[] args = new Object[]{enterprise.getEnterpriseName(), enterprise.getId()};
			daoTemplate.update(sql, args, false);
		}catch (Exception e){
			e.printStackTrace();
		}

	}
	
	class EnterpriseRowMapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs) throws SQLException {
			// TODO Auto-generated method stub
			Enterprise enterprise = new Enterprise(
					rs.getString("id"),
					rs.getString("enterpriseName"));
			return enterprise;
		}
		
	}

}
