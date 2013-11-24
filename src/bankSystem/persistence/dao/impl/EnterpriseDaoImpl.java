package bankSystem.persistence.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;

import bankSystem.entity.Enterprise;
import bankSystem.persistence.dao.iface.EnterpriseDao;

public class EnterpriseDaoImpl extends basicPersistence implements EnterpriseDao {
	private ArrayList<Enterprise> enterprises = 
			new ArrayList<Enterprise>();
	private static String persistencePath = persistenceRoot + "/enterprises.obj";

	public EnterpriseDaoImpl() throws Exception{
		ArrayList<Object> objects = readObject(persistenceRoot, persistencePath);
		for(Object object : objects){
			enterprises.add((Enterprise)object);
		}
	}	
	
	protected void save() throws Exception{
		ArrayList<Object> objects = new ArrayList<Object>();
		objects.addAll(enterprises);
		super.writeObject(persistenceRoot, persistencePath, objects);
	}

	@Override
	public Enterprise getEnterprise(String id) {
		// TODO Auto-generated method stub
		for(Enterprise enterprise : enterprises){
			if(enterprise.getId().equals(id))
				return enterprise;
		}		
		return null;
	}

	@Override
	public void insertEnterprise(Enterprise enterprise) {
		// TODO Auto-generated method stub
		enterprises.add(enterprise);
		try{
			save();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void deleteEnterprise(String id) {
		// TODO Auto-generated method stub
		Iterator<Enterprise> it = enterprises.iterator();
		while(it.hasNext()){
			Enterprise a = it.next();
			if(a.getId().equals(id)){
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

	@Override
	public void updateEnterprise(Enterprise enterprise) {
		// TODO Auto-generated method stub
		deleteEnterprise(enterprise.getId());
		insertEnterprise(enterprise);
	}

}
