package bankSystem.persistence.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;

import bankSystem.entity.VIPUser;
import bankSystem.persistence.dao.iface.VIPUserDao;

public class VIPUserDaoImpl implements VIPUserDao {
	private static ArrayList<VIPUser> users = new ArrayList<VIPUser>();

	@Override
	public VIPUser getIndividualUser(String userid) {
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
	}

}
