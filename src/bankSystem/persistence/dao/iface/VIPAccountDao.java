package bankSystem.persistence.dao.iface;

import bankSystem.entity.VIPAccount;

public interface VIPAccountDao {
	VIPAccount getAccount(String id);
	
	void insertAccount(VIPAccount account);
	void deleteAccount(String id);
	void updateAccount(VIPAccount account);

	void checkAccount();
}
