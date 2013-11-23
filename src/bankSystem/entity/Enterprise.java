package bankSystem.entity;

public class Enterprise {
	private String id;
	private String enterpriseName;
	private double balanceLimit;
	
	public Enterprise(String id, String enterpriseName) {
		super();
		this.id = id;
		this.enterpriseName = enterpriseName;
		this.balanceLimit = 10000;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public double getBalanceLimit() {
		return balanceLimit;
	}

	public void setBalanceLimit(double balanceLimit) {
		this.balanceLimit = balanceLimit;
	}
	
}
