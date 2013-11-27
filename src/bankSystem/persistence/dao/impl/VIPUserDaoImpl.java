package bankSystem.persistence.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;

import bankSystem.entity.VIPUser;
import bankSystem.persistence.dao.iface.VIPUserDao;

public class VIPUserDaoImpl extends basicPersistence implements VIPUserDao {
	private ArrayList<VIPUser> users = new ArrayList<VIPUser>();
	private  String persistencePath = persistenceRoot + "/VIPUsers.obj";
	
	public VIPUserDaoImpl() throws Exception{
		ArrayList<Object> objects = readObject(persistenceRoot, persistencePath);
		for(Object object : objects){
			users.add((VIPUser)object);
		}
	}
	
	protected void save() throws Exception{
		ArrayList<Object> objects = new ArrayList<Object>();
		objects.addAll(users);
		super.writeObject(persistenceRoot, persistencePath, objects);
	}
	
	@Override
	public VIPUser getVIPUser(String userid) {
		// TODO Auto-generated method stub
		for(VIPUser u : users){
			if(u.getId().equals(userid)){
				return u;
			}
		}
		return null;
	}

	@Override
	public void insertUser(VIPUser user) {
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
		Iterator<VIPUser> it = users.iterator();
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
