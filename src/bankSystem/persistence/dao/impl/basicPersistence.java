package bankSystem.persistence.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class basicPersistence {
	protected String persistenceRoot = new String();
	private static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	
	public basicPersistence(){
		persistenceRoot = this.getClass().getClassLoader().getResource("/").getPath();
	     
	    try {
	    	persistenceRoot =URLDecoder.decode(persistenceRoot, "gb2312");
	    } catch (UnsupportedEncodingException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    persistenceRoot += "persistence";
	}
	
	public ArrayList<Object> readObject(String persistenceRoot, 
			String persistencePath) throws Exception{
		rwl.readLock().lock();
		
		System.out.println(persistencePath);
		ArrayList<Object> objects = new ArrayList<Object>();
		try{
			File f = new File(persistenceRoot);
			if(!f.exists()){
				System.out.println(persistenceRoot);
				f.mkdirs();
			}
			else {
				new File(persistencePath).createNewFile();
				// wrapper, avoid EOFException
				FileInputStream istream = new FileInputStream(persistencePath);
				if(istream.available() > 0){
					ObjectInputStream in = new ObjectInputStream(istream);	
					while(istream.available() > 0){
						objects.add(in.readObject());
					}
					in.close();
				}
			}		
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			rwl.readLock().unlock();
		}
		return objects;
	}
	
	public void writeObject(String persistenceRoot, 
			String persistencePath, ArrayList<Object> objects) throws Exception{
		rwl.writeLock().lock();
		try{
			ObjectOutputStream out = new ObjectOutputStream
					 (new FileOutputStream(persistencePath));
			for(Object object : objects){
				out.writeObject(object);
			}
			out.close();
		} catch (Exception e){
			e.printStackTrace();
		} finally{
			rwl.writeLock().unlock();
		}
	}
	
}
