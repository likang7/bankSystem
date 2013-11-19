package bankSystem.persistence.dao.impl;
import bankSystem.entity.Account;
import bankSystem.persistence.dao.iface.*;

import java.util.ArrayList;
import java.util.Iterator;

public class AccountDaoImpl implements AccountDao{
	private static ArrayList<Account> accounts = new ArrayList<Account>();
	
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
		
	}

	@Override
	public void deleteAccount(String id) {
		// TODO Auto-generated method stub
		Iterator<Account> it = accounts.iterator();
		while(it.hasNext()){
			Account a = it.next();
			if(a.getId().equals(id)){
				it.remove();
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
