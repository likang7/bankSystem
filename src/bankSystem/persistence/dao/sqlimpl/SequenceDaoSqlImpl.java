package bankSystem.persistence.dao.sqlimpl;

import java.sql.ResultSet;
import java.sql.SQLException;



import bankSystem.entity.Sequence;
import bankSystem.persistence.dao.iface.SequenceDao;

public class SequenceDaoSqlImpl implements SequenceDao {
	private DaoOperateTemplate daoTemplate = new DaoOperateTemplate();
	
	private void updateSequence(Sequence seq){
		try{
			String sql = "update sequence set nextid=? where name=?";
			Object[] args = new Object[]{seq.getNextid(), seq.getName()};
			daoTemplate.update(sql, args, false);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public int getNextId(String name) {
		// TODO Auto-generated method stub
		try{
			String sql = "select * from Sequence where name=?";
			Object[] args = new Object[]{name};
			Sequence seq = (Sequence)daoTemplate.find(sql, args, new SequenceRowMapper());
			
			if(seq == null){
				throw new Exception("Error: A null sequence was returned from the"
		    			+ " database (could not get next " + name + " sequence).");
			}
			
			int returnId = seq.getNextid();
			
			seq.setNextid(seq.getNextid() + 1);
			updateSequence(seq);
			
			return returnId;
		}catch (Exception e){
			e.printStackTrace();
		}
		return -1;
	}
	
	class SequenceRowMapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs) throws SQLException {
			// TODO Auto-generated method stub
			Sequence s = new Sequence(
					rs.getInt("nextid"),
					rs.getString("name"));
			return s;
		}
		
	}


}
