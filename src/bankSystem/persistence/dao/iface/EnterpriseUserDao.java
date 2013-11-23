package bankSystem.persistence.dao.iface;

import bankSystem.entity.EnterpriseUser;
import bankSystem.entity.User;

public interface EnterpriseUserDao {
	
	EnterpriseUser getEnterpriseUser(String userid);
	
	void insertUser(User user);
	
	void deleteUser(String userid);
}
