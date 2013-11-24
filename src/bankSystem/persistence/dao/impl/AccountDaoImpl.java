package bankSystem.persistence.dao.impl;
import bankSystem.entity.Account;
import bankSystem.persistence.dao.iface.*;

import java.util.ArrayList;
import java.util.Iterator;

public class AccountDaoImpl extends basicPersistence implements AccountDao{
	private ArrayList<Account> accounts = new ArrayList<Account>();
	private static String persistencePath = persistenceRoot + "/accounts.obj";
	
	public AccountDaoImpl() throws Exception{
		ArrayList<Object> objects = readObject(persistenceRoot, persistencePath);
		for(Object object : objects){
			accounts.add((Account)object);
		}
//		File f = new File(persistenceRoot);
//		if(!f.exists()){
//			f.mkdirs();
//			f = new File(persistencePath);
//			f.createNewFile();
//		}
//		else {
//			new File(persistencePath).createNewFile();
//			// wrapper, avoid EOFException
//			FileInputStream istream = new FileInputStream(persistencePath);
//			if(istream.available() > 0){
//				ObjectInputStream in = new ObjectInputStream(istream);	
//				while(istream.available() > 0){
//					Account account = (Account)in.readObject();
//					accounts.add(account);
//				}
//				in.close();
//			}
//		}		
	}
	
	protected void save() throws Exception{
		ArrayList<Object> objects = new ArrayList<Object>();
		objects.addAll(accounts);
		super.writeObject(persistenceRoot, persistencePath, objects);
	}
	
	@Override
	public Account getAccount(String id) {
		// TODO Auto-generated method stub
		for(Account account : accounts){
			if(account.getId().equals(id))
				return account;
		}
		return null;
	}

	@Override
	public void insertAccount(Account account) {
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
		Iterator<Account> it = accounts.iterator();
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
	public void updateAccount(Account account) {
		// TODO Auto-generated method stub
		deleteAccount(account.getId());
		insertAccount(account);
	}

}
