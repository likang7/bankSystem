package bankSystem.entity;

import java.util.Date;

public class VIPAccount extends Account {
	private Date excessStart;
	private boolean isFrozen;
	private double accBalanceThisMonth;
	private short accFailMonths;

	public VIPAccount() {
		// TODO Auto-generated constructor stub
		super();
		excessStart = null;
		isFrozen = false;
		accBalanceThisMonth = 0.0;
		accFailMonths = 0;
	}

	public VIPAccount(String id, AccountType type, double balance, Date openDate) {
		super(id, type, balance, openDate);
		// TODO Auto-generated constructor stub
		excessStart = null;
		isFrozen = false;
		accBalanceThisMonth = 0.0;
		accFailMonths = 0;
	}

	public Date getExcessStart() {
		return excessStart;
	}

	public void setExcessStart(Date excessStart) {
		this.excessStart = excessStart;
	}

	public boolean isFrozen() {
		return isFrozen;
	}

	public void setFrozen(boolean isFrozen) {
		this.isFrozen = isFrozen;
	}

	public double getAccBalanceThisMonth() {
		return accBalanceThisMonth;
	}

	public void setAccBalanceThisMonth(double accBalanceThisMonth) {
		this.accBalanceThisMonth = accBalanceThisMonth;
	}

	public short getAccFailMonths() {
		return accFailMonths;
	}

	public void setAccFailMonths(short accFailMonths) {
		this.accFailMonths = accFailMonths;
	}
}
