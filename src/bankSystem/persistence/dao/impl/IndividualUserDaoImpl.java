package bankSystem.persistence.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;

import bankSystem.entity.IndividualUser;
import bankSystem.persistence.dao.iface.IndividualUserDao;

public class IndividualUserDaoImpl extends basicPersistence implements IndividualUserDao {
	private ArrayList<IndividualUser> users = new ArrayList<IndividualUser>();
	private  String persistencePath = persistenceRoot + "/individualUsers.obj";
	
	public IndividualUserDaoImpl() throws Exception{
		ArrayList<Object> objects = readObject(persistenceRoot, persistencePath);
		for(Object object : objects){
			users.add((IndividualUser)object);
		}
	}
	
	protected void save() throws Exception{
		ArrayList<Object> objects = new ArrayList<Object>();
		objects.addAll(users);
		super.writeObject(persistenceRoot, persistencePath, objects);
	}
	
	@Override
	public IndividualUser getIndividualUser(String userid) {
		// TODO Auto-generated method stub
		for(IndividualUser user : users){
			if(user.getId().equals(userid)){
				return user;
			}
		}
		return null;
	}

	@Override
	public void insertUser(IndividualUser user) {
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
		Iterator<IndividualUser> it = users.iterator();
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
