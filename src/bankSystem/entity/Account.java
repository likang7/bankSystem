package bankSystem.entity;
import java.io.Serializable;
import java.util.Date;

public class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private AccountType type;
	private double balance;
	private Date openDate;
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
		id = "";
		type = AccountType.Current;
		balance = 0.0;
		openDate = new Date();
	}

	public Account(String id, AccountType type, double balance, Date openDate) {
		super();
		this.id = id;
		this.type = type;
		this.balance = balance;
		this.openDate = openDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

}

