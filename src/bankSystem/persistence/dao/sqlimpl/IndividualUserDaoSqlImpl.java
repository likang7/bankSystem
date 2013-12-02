package bankSystem.persistence.dao.sqlimpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import bankSystem.entity.IndividualUser;
import bankSystem.persistence.dao.iface.IndividualUserDao;

public class IndividualUserDaoSqlImpl implements IndividualUserDao {
	private DaoOperateTemplate daoTemplate = new DaoOperateTemplate();
	@Override
	public IndividualUser getIndividualUser(String userid) {
		// TODO Auto-generated method stub
		try{
			String sql = "select * from individualuser where id=?";
			Object[] args = new Object[]{userid};
			IndividualUser iu = (IndividualUser)daoTemplate.find(sql, args, 
					new IndividualUserRowMapper());
			return iu;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insertUser(IndividualUser user) {
		// TODO Auto-generated method stub
		try{
			String sql = "insert into individualuser(id,name) values (?,?)";
			Object[] args = new Object[]{user.getId(), user.getName()};
			daoTemplate.update(sql, args, false);
		}catch (Exception e){
			e.printStackTrace();
		}

	}

	@Override
	public void deleteUser(String userid) {
		// TODO Auto-generated method stub
		try{
			String sql = "delete from individualuser where id=?";
			Object[] args = new Object[]{userid};
			daoTemplate.update(sql, args, false);
		}catch (Exception e){
			e.printStackTrace();
		}

	}
	
	class IndividualUserRowMapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs) throws SQLException {
			// TODO Auto-generated method stub
			IndividualUser iu = new IndividualUser(
					rs.getString("id"),
					rs.getString("name"));
			return iu;
		}
		
	}

}
