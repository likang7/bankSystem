package bankSystem.service;

import java.util.Date;

public class VIPBusinessService extends BusinessService {

	@Override
	public ReturnMsg deposit(String operator, String cardId, String password,
			double money) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReturnMsg withdraw(String operator, String cardId,
			String password, double money) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReturnMsg query(String operator, String userId, String cardId,
			String password, Date start, Date end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReturnMsg transfer(String operator, String userId, String cardId,
			String password, String username, String outCardId,
			String outUsername, double money) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReturnMsg closeAccount(String operator, String userId,
			String cardId, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
