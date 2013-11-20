package bankSystem.service;

import java.util.Date;

import bankSystem.entity.*;
import bankSystem.persistence.dao.DaoFactory;
import bankSystem.persistence.dao.iface.*;

public class IndividualBusinessService extends BusinessService {
	private IndividualUserDao userDao;
	private AccountDao accountDao;
	
	public IndividualBusinessService(){
		super();
		try{
			userDao = (IndividualUserDao)DaoFactory.getInstance().getDao("IndividualUserDao");
			accountDao = (AccountDao)DaoFactory.getInstance().getDao("AccountDao");
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public ReturnMsg openAccount(String operatorId, String userId,
			String name, String accountType, double money, String password){
		ReturnMsg returnMsg = new ReturnMsg();
		
		if(userDao.getIndividualUser(userId) == null){
			IndividualUser user = new IndividualUser(userId, name);
			userDao.insertUser(user);
		}
		String cardId = sequenceDao.getNextId();
		String accountId = sequenceDao.getNextId();
		Card card = new Card(cardId, password, accountId, userId);
		
		AccountType type = super.stringToAccountType(accountType);
		Date date = new Date();
		Account account = new Account(accountId, type, money, date);
		
		cardDao.insertCard(card);
		accountDao.insertAccount(account);
		
		Log log = new Log(date, "openAccount", operatorId, cardId, accountId, money, 0, money);
		logDao.insertLog(log);
		
		returnMsg.setStatus(Status.OK);
		returnMsg.setLog(log);
		return returnMsg;
	}

	@Override
	public ReturnMsg deposit(String operator, String cardId, 
			String password, double money){
		ReturnMsg returnMsg = null;
		try{
			ReturnMsg cardMsg = checkCard(cardId, password);
			if(cardMsg.getStatus().equals(Status.ERROR))
				return cardMsg;
			
			returnMsg = new ReturnMsg();
			returnMsg.setStatus(Status.OK);
			
			
			
			Card card = cardDao.getCard(cardId, password);
			Account account = accountDao.getAccount(card.getAccountId());
			account.setBalance(account.getBalance() + money);
			accountDao.updateAccount(account);
			
			Log log = new Log(new Date(), "deposit", operator, cardId, account.getId(), 
					money, 0, account.getBalance());
			returnMsg.setLog(log);
			logDao.insertLog(log);
		}catch (Exception e){
			e.printStackTrace();
		}
		return returnMsg;
	}
	
	@Override
	public ReturnMsg withdraw(String operator, String userId, String cardId,
			String password, String money) {
		// TODO Auto-generated method stub
		return null;
	}

}
