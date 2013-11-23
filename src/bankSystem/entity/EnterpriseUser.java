package bankSystem.entity;

public class EnterpriseUser extends User {
	private String enterpriseId;
	private boolean isSuper;
	
	public EnterpriseUser() {
		// TODO Auto-generated constructor stub
		enterpriseId = "";
		isSuper = false;
	}

	public EnterpriseUser(String id, String name, String enterpriseId,
			boolean isSuper) {
		super(id, name);
		// TODO Auto-generated constructor stub
		this.enterpriseId = enterpriseId;
		this.isSuper = isSuper;
	}

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public boolean isSuper() {
		return isSuper;
	}

	public void setSuper(boolean isSuper) {
		this.isSuper = isSuper;
	}	
}
