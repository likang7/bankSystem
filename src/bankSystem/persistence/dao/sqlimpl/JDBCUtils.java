package bankSystem.persistence.dao.sqlimpl;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSourceFactory;

public class JDBCUtils {
	private static DataSource dataSource = null;
	private static String proFile = "properties/dbcpconfig.properties";
	
	static{
		try{
			Properties prop = new Properties();
			InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream(proFile);
			prop.load(is);
			dataSource = BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private JDBCUtils(){
		
	}
	
	public static DataSource getDataSource() {
		return dataSource;
	}
	
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
	
	public static void free(ResultSet rs, Statement st, Connection conn) throws SQLException{
		try{
			if(rs != null)
				rs.close();
		} catch (SQLException e){
			throw e;
		} finally{
			try{
				if(st != null)
					st.close();
			} catch (SQLException e){
				throw e;
			} finally{
				if(conn != null)
					conn.close();
			}
		}
	}
	
	public static void main(String[] args) throws SQLException{
		Connection conn = JDBCUtils.getConnection();
		if(!conn.isClosed()){
			System.out.println("Succeeded connecting to the Database!");

			// statement用来执行SQL语句

			Statement statement = conn.createStatement();

			// 要执行的SQL语句

			String sql = "select * from student";
		}
	}

}
