package bankSystem.persistence.dao.sqlimpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import bankSystem.entity.Department;
import bankSystem.persistence.dao.iface.DepartmentDao;

public class DepartmentDaoSqlImpl implements DepartmentDao{
	private DaoOperateTemplate daoTemplate = new DaoOperateTemplate();
	
	@Override
	public Department getDepartment(String id) {
		// TODO Auto-generated method stub
		try{
			String sql = "select * from department where id=?";
			Object[] args = new Object[]{id};
			Department d = (Department)daoTemplate.find(sql, args, new DepartmentRowMapper());
			return d;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insertDepartment(Department department) {
		// TODO Auto-generated method stub
		try{
			String sql = "insert into department(id, name) values (?,?)";
			Object[] args = new Object[]{department.getId(), department.getName()};
			daoTemplate.update(sql, args, false);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	class DepartmentRowMapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs) throws SQLException {
			// TODO Auto-generated method stub
			Department d = new Department(
					rs.getString("id"),
					rs.getString("name")); 
			return d;
		}
		
	}

}
