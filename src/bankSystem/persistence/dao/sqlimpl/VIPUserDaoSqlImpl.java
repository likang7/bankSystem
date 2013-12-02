package bankSystem.persistence.dao.sqlimpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import bankSystem.entity.VIPUser;
import bankSystem.persistence.dao.iface.VIPUserDao;

public class VIPUserDaoSqlImpl implements VIPUserDao {
	private DaoOperateTemplate daoTemplate = new DaoOperateTemplate();
	@Override
	public VIPUser getVIPUser(String userid) {
		// TODO Auto-generated method stub
		try{
			String sql = "select * from vipuser where id=?";
			Object[] args = new Object[]{userid};
			VIPUser vu = (VIPUser)daoTemplate.find(sql, args, new VIPUserRowMapper());
			return vu;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insertUser(VIPUser user) {
		// TODO Auto-generated method stub
		try{
			String sql = "insert into vipuser(id,name) values (?,?)";
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
			String sql = "delete from vipuser where id=?";
			Object[] args = new Object[]{userid};
			daoTemplate.update(sql, args, false);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	class VIPUserRowMapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs) throws SQLException {
			// TODO Auto-generated method stub
			VIPUser vu = new VIPUser(
					rs.getString("id"),
					rs.getString("name"));
			return vu;
		}
		
	}

}
