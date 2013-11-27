package bankSystem.persistence.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;

import bankSystem.entity.EnterpriseUser;
import bankSystem.persistence.dao.iface.EnterpriseUserDao;

public class EnterpriseUserDaoImpl extends basicPersistence implements EnterpriseUserDao {
	private ArrayList<EnterpriseUser> users = 
			new ArrayList<EnterpriseUser>();
	private  String persistencePath = persistenceRoot + "/enterpriseUsers.obj";
	
	public EnterpriseUserDaoImpl() throws Exception{
		ArrayList<Object> objects = readObject(persistenceRoot, persistencePath);
		for(Object object : objects){
			users.add((EnterpriseUser)object);
		}
	}
	
	protected void save() throws Exception{
		ArrayList<Object> objects = new ArrayList<Object>();
		objects.addAll(users);
		super.writeObject(persistenceRoot, persistencePath, objects);
	}
	
	@Override
	public EnterpriseUser getEnterpriseUser(String userid) {
		// TODO Auto-generated method stub
		for(EnterpriseUser user : users){
			if(user.getId().equals(userid)){
				return user;
			}
		}		
		return null;
	}

	@Override
	public void insertUser(EnterpriseUser user) {
		// TODO Auto-generated method stub
		users.add(user);
		try{
			save();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(String userid) {
		// TODO Auto-generated method stub
		Iterator<EnterpriseUser> it = users.iterator();
		while(it.hasNext()){
			if(it.next().getId().equals(userid)){
				it.remove();
				break;
			}
		}
		try{
			save();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
