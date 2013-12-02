package bankSystem.persistence.dao.sqlimpl;
import java.sql.*; 

public class test {
	public static void main(String[] args){
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/test";
		
		String user = "root";
		String password = "";
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();  
			Connection conn = DriverManager.getConnection(url, user, password);
			if(!conn.isClosed()){
				System.out.println("Succeeded connecting to the Database!");

				// statement用来执行SQL语句

				Statement statement = conn.createStatement();

				// 要执行的SQL语句

				String sql = "select * from student";
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
}
