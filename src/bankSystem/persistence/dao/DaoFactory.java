package bankSystem.persistence.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DaoFactory {
	//private static String proFile="properties/dao.properties";
	private static String proFile="properties/dao_sql.properties";
	private static DaoFactory instance = new DaoFactory();
	private static Properties pro;
	
	public static DaoFactory getInstance(){
		return instance;
	}
	
	private DaoFactory() {
		super();
		// TODO Auto-generated constructor stub
		try{
			pro = new Properties();
			InputStream inputStream = DaoFactory.class
					.getClassLoader().getResourceAsStream(proFile);
			pro.load(inputStream);
		} catch (IOException e){
			throw new ExceptionInInitializerError(e);
		}	
	}
	
	public Object getDao(String key) throws Exception{
		String className = (String)pro.get(key);
		return (Class.forName(className).newInstance());
	}
}
