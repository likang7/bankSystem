package bankSystem.persistence.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;

import bankSystem.entity.IndividualUser;
import bankSystem.persistence.dao.iface.IndividualUserDao;

public class IndividualUserDaoImpl implements IndividualUserDao {
	private static ArrayList<IndividualUser> users = new ArrayList<IndividualUser>();
	
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
		
	}



}
