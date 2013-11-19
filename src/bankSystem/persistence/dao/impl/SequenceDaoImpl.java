package bankSystem.persistence.dao.impl;

import bankSystem.persistence.dao.iface.SequenceDao;

public class SequenceDaoImpl implements SequenceDao {
	private static int currentId = 1000000;
	
	@Override
	public String getNextId() {
		// TODO Auto-generated method stub
		String id = String.valueOf(currentId);
		currentId += 1;
		return id;
	}

}
