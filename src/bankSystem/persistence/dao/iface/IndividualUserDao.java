package bankSystem.persistence.dao.iface;
import bankSystem.entity.*;

public interface IndividualUserDao {
	IndividualUser getIndividualUser(String userid);
	
	void insertUser(IndividualUser user);
	
	void deleteUser(String userid);
}
