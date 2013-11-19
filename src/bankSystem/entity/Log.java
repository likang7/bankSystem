package bankSystem.entity;
import java.util.Date;

public class Log {
	private Date time;
	private String operation;
	private String operator;
	private String cardId;
	private String accountId;
	private double income;
	private double expenditure;
	private double balance;
	
	public Log(Date time, String operation, String operator, String cardId,
			String accountId, double income, double expenditure, double balance) {
		super();
		this.time = time;
		this.operation = operation;
		this.operator = operator;
		this.cardId = cardId;
		this.accountId = accountId;
		this.income = income;
		this.expenditure = expenditure;
		this.balance = balance;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public double getExpenditure() {
		return expenditure;
	}

	public void setExpenditure(double expenditure) {
		this.expenditure = expenditure;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}