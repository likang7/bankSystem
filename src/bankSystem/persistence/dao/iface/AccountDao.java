package bankSystem.persistence.dao.iface;
import bankSystem.entity.*;

public interface AccountDao {
	Account getAccount(String id);
	
	void insertAccount(Account account);
	
	void deleteAccount(String id);
	
	void updateAccount(Account account);
}
