package bankSystem.entity;

import java.util.Date;

public class EnterpriseAccount extends Account {
	private String enterpriseId;
	
	public EnterpriseAccount(String id, AccountType type, double balance, Date openDate,
			String enterpriseId) {
		super(id, type, balance, openDate);
		// TODO Auto-generated constructor stub
		this.enterpriseId = enterpriseId;
	}

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
	
}
