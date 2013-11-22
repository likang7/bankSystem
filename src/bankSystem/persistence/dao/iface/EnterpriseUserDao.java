package bankSystem.persistence.dao.iface;

import bankSystem.entity.User;

public interface EnterpriseUserDao {
	User getEnterpriseUser(String userid);
	
	void insertUser(User user);
	
	void deleteUser(String userid);
}
