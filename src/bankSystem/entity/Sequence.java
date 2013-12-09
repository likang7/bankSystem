package bankSystem.entity;

import java.io.Serializable;

public class Sequence implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1111312603056864595L;
	private int nextid = 1000000;
	private String name = "all";
	
	public Sequence(int nextid, String name) {
		super();
		this.nextid = nextid;
		this.name = name;
	}

	public Sequence() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getNextid() {
		return nextid;
	}
	
	public void setNextid(int nextid) {
		this.nextid = nextid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
