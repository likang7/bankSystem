package bankSystem.persistence.dao.sqlimpl;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import bankSystem.entity.Card;

public class DaoOperateTemplate{
	// 查找单个记录
	public Object find(String sql, Object[] args, RowMapper rowMapper) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;  
        ResultSet rs = null;  
        
        try{
        	conn = JDBCUtils.getConnection();
        	ps = conn.prepareStatement(sql);
        	for(int i = 0; i < args.length; i++){
        		ps.setObject(i + 1, args[i]);
        	}
        	rs = ps.executeQuery();
        	Object obj = null;
        	if(rs.next()){
        		obj = rowMapper.mapRow(rs);
        	}
        	return obj;
        } catch (SQLException e){
        	throw e;
        } finally{
        	try{
        		JDBCUtils.free(rs, ps, conn);
        	} catch (SQLException e){
        		throw e;
        	}
        }
	}
	
	//查找多个记录
	public List<Object> query(String sql, Object[] args, RowMapper rowMapper)  
            throws Exception {  
        Connection conn = null;  
        PreparedStatement ps = null;  
        ResultSet rs = null;  
        List<Object> results = new ArrayList<Object>();  
        try {  
            conn = JDBCUtils.getConnection();  
            ps = conn.prepareStatement(sql);  
            for (int i = 0; i < args.length; i++)  
                ps.setObject(i + 1, args[i]);  
            rs = ps.executeQuery();  
            Object obj = null;  
            while (rs.next()) {  
                obj = rowMapper.mapRow(rs);  
                results.add(obj);  
            }  
            return results;  
        } catch (SQLException e) {  
            throw e; 
        } finally {  
            try {  
                JDBCUtils.free(rs, ps, conn);  
            } catch (SQLException e) {  
                throw e;
            }  
        }  
    }  
	
	//更新对象
	public void update(String sql, Object[] args, boolean isGeneralKey)  
            throws Exception {  
        Connection conn = null;  
        PreparedStatement ps = null;  
        ResultSet rs = null;  
        try {  
            conn = JDBCUtils.getConnection();  
            ps = (isGeneralKey ? conn.prepareStatement(sql,  
                    Statement.RETURN_GENERATED_KEYS) : conn  
                    .prepareStatement(sql));  
            for (int i = 0; i < args.length; i++)  
                ps.setObject(i + 1, args[i]);  
            ps.executeUpdate();  
        } catch (SQLException e) {  
            throw e;
        } finally {  
            try {  
                JDBCUtils.free(rs, ps, conn);  
            } catch (SQLException e) {  
                throw e; 
            }  
        }  
    }  
}
