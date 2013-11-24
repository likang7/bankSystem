package bankSystem.persistence.dao.iface;

import bankSystem.entity.EnterpriseUser;

public interface EnterpriseUserDao {
	
	EnterpriseUser getEnterpriseUser(String userid);
	
	void insertUser(EnterpriseUser user);
	
	void deleteUser(String userid);
}
