package bankSystem.persistence.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;

import bankSystem.entity.Account;
import bankSystem.entity.VIPAccount;
import bankSystem.persistence.dao.iface.VIPAccountDao;

public class VIPAccountDaoImpl extends basicPersistence implements VIPAccountDao {
	private ArrayList<VIPAccount> accounts = 
			new ArrayList<VIPAccount>();
	private  String persistencePath = persistenceRoot + "/VIPAccounts.obj";
	
	public VIPAccountDaoImpl() throws Exception{
		ArrayList<Object> objects = readObject(persistenceRoot, persistencePath);
		for(Object object : objects){
			accounts.add((VIPAccount)object);
		}
	}
	
	protected void save() throws Exception{
		ArrayList<Object> objects = new ArrayList<Object>();
		objects.addAll(accounts);
		super.writeObject(persistenceRoot, persistencePath, objects);
	}
	
	@Override
	public VIPAccount getAccount(String id) {
		// TODO Auto-generated method stub
		for(VIPAccount account : accounts){
			if(account.getId().equals(id))
				return account;
		}
		return null;
	}

	@Override
	public void insertAccount(VIPAccount account) {
		// TODO Auto-generated method stub
		accounts.add(account);
		try{
			save();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAccount(String id) {
		// TODO Auto-generated method stub
		Iterator<VIPAccount> it = accounts.iterator();
		while(it.hasNext()){
			Account a = it.next();
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
	public void updateAccount(VIPAccount account) {
		// TODO Auto-generated method stub
		deleteAccount(account.getId());
		insertAccount(account);
	}

	@Override
	public ArrayList<VIPAccount> getAllAccounts() {
		// TODO Auto-generated method stub
		return (ArrayList<VIPAccount>)accounts.clone();
	}


}
