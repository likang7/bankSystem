package bankSystem.persistence.dao.iface;

import bankSystem.entity.Enterprise;

public interface EnterpriseDao {
	Enterprise getEnterprise(String id);
	
	void insertEnterprise(Enterprise enterprise);
	void deleteEnterprise(String id);
	void update(Enterprise enterprise);
}
