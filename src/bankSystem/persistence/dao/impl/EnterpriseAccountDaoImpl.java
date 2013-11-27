package bankSystem.persistence.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;

import bankSystem.entity.Account;
import bankSystem.entity.EnterpriseAccount;
import bankSystem.persistence.dao.iface.EnterpriseAccountDao;

public class EnterpriseAccountDaoImpl extends basicPersistence implements EnterpriseAccountDao {
	private ArrayList<EnterpriseAccount> accounts = new ArrayList<EnterpriseAccount>();
	private  String persistencePath = persistenceRoot + "/enterpriseAccounts.obj";
	
	public EnterpriseAccountDaoImpl() throws Exception {
		super();
		// TODO Auto-generated constructor stub
		ArrayList<Object> objects = readObject(persistenceRoot, persistencePath);
		for(Object object : objects){
			accounts.add((EnterpriseAccount)object);
		}
	}
	
	protected void save() throws Exception{
		ArrayList<Object> objects = new ArrayList<Object>();
		objects.addAll(accounts);
		super.writeObject(persistenceRoot, persistencePath, objects);
	}
	
	@Override
	public EnterpriseAccount getAccount(String id) {
		// TODO Auto-generated method stub
		for(EnterpriseAccount account : accounts){
			if(account.getId().equals(id))
				return account;
		}
		return null;
	}

	@Override
	public void insertAccount(EnterpriseAccount account) {
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
		Iterator<EnterpriseAccount> it = accounts.iterator();
		while(it.hasNext()){
			Account a = it.next();
			if(a.getId().equals(id)){
				it.remove();
				try{
					save();
				}catch (Exception e){
					e.printStackTrace();
				}
				break;
			}
		}	
	}

	@Override
	public void updateAccount(EnterpriseAccount account) {
		// TODO Auto-generated method stub
		deleteAccount(account.getId());
		insertAccount(account);	
	}

	@Override
	public ArrayList<EnterpriseAccount> getAccountsByEnterpriseId(
			String enterpriseId) {
		// TODO Auto-generated method stub
		ArrayList<EnterpriseAccount> eaccountList = new ArrayList<EnterpriseAccount>();
		for(EnterpriseAccount account : accounts){
			if(account.getEnterpriseId().equals(enterpriseId)){
				eaccountList.add(account);
			}
		}
		return eaccountList;
	}

}
