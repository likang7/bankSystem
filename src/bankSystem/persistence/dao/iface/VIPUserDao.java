package bankSystem.persistence.dao.iface;
import bankSystem.entity.*;

public interface VIPUserDao {
	VIPUser getVIPUser(String userid);
	
	void insertUser(VIPUser user);
	
	void deleteUser(String userid);
}
