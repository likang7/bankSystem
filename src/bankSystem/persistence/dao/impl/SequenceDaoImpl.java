package bankSystem.persistence.dao.impl;

import java.util.ArrayList;

import bankSystem.entity.Sequence;
import bankSystem.persistence.dao.iface.SequenceDao;

public class SequenceDaoImpl extends basicPersistence implements SequenceDao {
	//private int currentId = 1000000;
	//private Sequence sequence = new Sequence();
	private  String persistencePath = persistenceRoot + "/sequence.obj";
	private ArrayList<Sequence> seqs = new ArrayList<Sequence>();
	
	
	public SequenceDaoImpl() throws Exception{
		ArrayList<Object> objects = readObject(persistenceRoot, persistencePath);
		for(Object object : objects){
			//currentId = (int)object;
			//sequence = (Sequence)object;
			seqs.add((Sequence)object);
		}
		
		if(seqs.isEmpty()){
			seqs.add(new Sequence(1000000, "all"));
		}
	}
	
	protected void save() throws Exception{
		ArrayList<Object> objects = new ArrayList<Object>();
		//objects.add(currentId);
		//objects.add(sequence);
		objects.addAll(seqs);
		super.writeObject(persistenceRoot, persistencePath, objects);
	}
	
	/*public String getNextId() {
		// TODO Auto-generated method stub
		//String id = String.valueOf(currentId);
		//updateNextId(String.valueOf(currentId + 1));
		String id = String.valueOf(sequence.getNextid());
		updateNextId(String.valueOf(sequence.getNextid() + 1));
		return id;
	}

	@Override
	public void updateNextId(String nextid) {
		// TODO Auto-generated method stub
		//currentId = Integer.valueOf(nextid);
		sequence.setNextid(Integer.valueOf(nextid));
		try{
			save();
		}catch (Exception e){
			e.printStackTrace();
		}
	}*/

	@Override
	public int getNextId(String name) {
		// TODO Auto-generated method stub
		try{
			Sequence sequence = null;
		    for(Sequence s : seqs){
		    	if(s.getName().equals(name)){
		    		sequence = s;
		    		break;
		    	}
		    }	
		    
		    if(sequence == null){
		    	throw new Exception("Error: A null sequence was returned from the"
		    			+ " database (could not get next " + name + " sequence).");
		    }
		    
		    int nextid = sequence.getNextid();
		    sequence.setNextid(sequence.getNextid() + 1);
		    save();
		    
		    return nextid;
		} catch (Exception e){
			e.printStackTrace();
		}
		return -1;
	}
}
