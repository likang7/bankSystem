package bankSystem.persistence.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DaoFactory {
	private static DaoFactory instance = null;
	private static Properties pro;
	private static String proFile="properties/dao.properties";
	
	public static DaoFactory getInstance(){
		if(instance == null)
			instance = new DaoFactory();
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
