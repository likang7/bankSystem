package bankSystem.persistence.dao.iface;

import java.util.ArrayList;

import bankSystem.entity.EnterpriseAccount;

public interface EnterpriseAccountDao {
	EnterpriseAccount getAccount(String id);
	
	void insertAccount(EnterpriseAccount account);
	void deleteAccount(String id);
	void updateAccount(EnterpriseAccount account);
	
	ArrayList<EnterpriseAccount> getAccountsByEnterpriseId(String enterpriseId);
}
