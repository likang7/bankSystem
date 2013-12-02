package bankSystem.persistence.dao.iface;

import bankSystem.entity.Sequence;

public interface SequenceDao {
	int getNextId(String name);
}
