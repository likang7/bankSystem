package bankSystem.persistence.dao.impl;

import java.util.ArrayList;

import bankSystem.persistence.dao.iface.SequenceDao;

public class SequenceDaoImpl extends basicPersistence implements SequenceDao {
	private int currentId = 1000000;
	private  String persistencePath = persistenceRoot + "/sequence.obj";
	
	public SequenceDaoImpl() throws Exception{
		ArrayList<Object> objects = readObject(persistenceRoot, persistencePath);
		for(Object object : objects){
			currentId = (int)object;
		}
	}
	
	protected void save() throws Exception{
		ArrayList<Object> objects = new ArrayList<Object>();
		objects.add(currentId);
		super.writeObject(persistenceRoot, persistencePath, objects);
	}
	
	@Override
	public String getNextId() {
		// TODO Auto-generated method stub
		String id = String.valueOf(currentId);
		updateNextId(String.valueOf(currentId + 1));
		return id;
	}

	@Override
	public void updateNextId(String nextid) {
		// TODO Auto-generated method stub
		currentId = Integer.valueOf(nextid);
		try{
			save();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
