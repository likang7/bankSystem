package bankSystem.service;

public class ServiceFactory {
	public static ServiceFactory instance = new ServiceFactory();
	
	public static ServiceFactory getInstance(){
		return instance;
	}
	
	private ServiceFactory(){
		super();
	}
	
	public Object getService(String name) throws Exception{
		if(name.equals("individual")){
			return new IndividualBusinessService();
		}
		else if(name.equals("vip")){
			return new VIPBusinessService();
		}
		else if(name.equals("enterprise")){
			return new EnterpriseBusinessService();
		}
		else if(name.equals("department")){
			return new DepartmentService();
		}
		else{
			throw new Exception("cannot find service " + name);
		}
	}

}
