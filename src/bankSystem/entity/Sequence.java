package bankSystem.entity;

public class Sequence {
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
