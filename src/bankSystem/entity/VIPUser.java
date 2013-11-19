package bankSystem.entity;

public class VIPUser extends User{
	private int excessLimit;

	public int getExcessLimit() {
		return excessLimit;
	}

	public void setExcessLimit(int excessLimit) {
		this.excessLimit = excessLimit;
	}

	public VIPUser(String id, String name) {
		super(id, name);
		// TODO Auto-generated constructor stub
		excessLimit = 100000;
	}

	public VIPUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public VIPUser(String id, String name, int excessLimit) {
		super(id, name);
		this.excessLimit = excessLimit;	
	}

}
