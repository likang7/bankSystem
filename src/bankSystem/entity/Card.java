package bankSystem.entity;

public class Card {
	private String id;
	private String password;
	private String accountId; 
	private String userId;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Card(String id, String password, String accountId, String userId) {
		super();
		this.id = id;
		this.password = password;
		this.accountId = accountId;
		this.userId = userId;
	}
	
	public Card() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAccountId() {
		return accountId;
	}
	
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
}
