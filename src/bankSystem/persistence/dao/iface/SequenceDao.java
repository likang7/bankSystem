package bankSystem.persistence.dao.iface;

public interface SequenceDao {
	String getNextId();
	void updateNextId(String id);
}
