package bankSystem.service;

import java.util.ArrayList;
import java.util.Date;

import bankSystem.entity.*;
import bankSystem.persistence.dao.DaoFactory;
import bankSystem.persistence.dao.iface.*;

public class EnterpriseBusinessService extends BusinessService {
	private EnterpriseUserDao userDao;
	private EnterpriseAccountDao accountDao;
	private EnterpriseDao enterpriseDao;
	private static String accountType = "enterprise";
	
	public EnterpriseBusinessService() {
		super();
		try{
			userDao = (EnterpriseUserDao)DaoFactory.getInstance().getDao("EnterpriseUserDao");
			accountDao = (EnterpriseAccountDao)DaoFactory.getInstance().getDao("EnterpriseAccountDao");
			enterpriseDao = (EnterpriseDao)DaoFactory.getInstance().getDao("EnterpriseDao");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private double getEnterpriseBalance(String enterpriseId){
		double sum = 0.0;
		ArrayList<EnterpriseAccount> accounts = accountDao.getAccountsByEnterpriseId(enterpriseId);
		for(EnterpriseAccount account : accounts){
			sum += account.getBalance();
		}
		return sum;
	}
	
	public ReturnMsg openAccount(String operatorId, String enterpriseId, String enterpriseName, String userId,
			String name, String accountType, double money, String password){
		ReturnMsg returnMsg = new ReturnMsg();
		
		// 检查企业id是否已注册过,如有测试企业id与企业名称是否一致
		Enterprise enterprise = enterpriseDao.getEnterprise(enterpriseId);
		if(enterprise != null){
			if(!enterprise.getEnterpriseName().equals(enterpriseName)){
				returnMsg.setStatus(Status.ERROR);
				returnMsg.setMsg("enperiseId and enterpriseName not agreed");
			}
			else{
				enterprise = new Enterprise(enterpriseId, enterpriseName);
				enterpriseDao.insertEnterprise(enterprise);
			}
		}
		
		//检查用户是否已注册过，如有测试id与名字是否一致
		EnterpriseUser user = userDao.getEnterpriseUser(userId);
		if(user != null){
			if(!user.getName().equals(name)){
				returnMsg.setStatus(Status.ERROR);
				returnMsg.setMsg("userId and username not agreed");
				return returnMsg;
			}
		}
		else {
			user = new EnterpriseUser(userId, name, enterpriseId, true);
			userDao.insertUser(user);
		}
		
		double totalBalance = getEnterpriseBalance(enterpriseId);
		if(totalBalance + money < Enterprise.getBalanceLimit()){
			returnMsg.setStatus(Status.ERROR);
			returnMsg.setMsg("not enough balance.Enterprise should have balance more than " 
					+ Enterprise.getBalanceLimit());
			return returnMsg;
		}
		
		String cardId = String.valueOf(sequenceDao.getNextId("all"));
		String accountId = String.valueOf(sequenceDao.getNextId("all"));
		Card card = new Card(cardId, password, accountId, userId);
		
		AccountType type = super.stringToAccountType(accountType);
		Date date = new Date();
		EnterpriseAccount account = new EnterpriseAccount(accountId, type, money, date, enterpriseId);
		
		cardDao.insertCard(card);
		accountDao.insertAccount(account);
		
		Log log = new Log(date, "openAccount", operatorId, cardId, accountId, accountType, money, 0, money);
		logDao.insertLog(log);
		
		returnMsg.setStatus(Status.OK);
		returnMsg.setMsg(cardId);
		returnMsg.setLog(log);
		
		return returnMsg;
	}

	@Override
	public ReturnMsg deposit(String operator, String cardId, String password,
			double money) {
		ReturnMsg returnMsg = null;
		try{
			ReturnMsg cardMsg = checkCard(cardId, password);
			if(cardMsg.getStatus().equals(Status.ERROR))
				return cardMsg;
			
			returnMsg = new ReturnMsg();
			returnMsg.setStatus(Status.OK);
			
			Card card = cardDao.getCard(cardId, password);
			EnterpriseAccount account = accountDao.getAccount(card.getAccountId());
			if(account == null){
				returnMsg.setStatus(Status.ERROR);
				returnMsg.setMsg("This is not enterprise account");
				return returnMsg;
			}
			account.setBalance(account.getBalance() + money);
			accountDao.updateAccount(account);
			
			Log log = new Log(new Date(), "deposit", operator, cardId, account.getId(), 
					accountType, money, 0, account.getBalance());
			returnMsg.setLog(log);
			returnMsg.setMsg(String.valueOf(account.getBalance()));
			logDao.insertLog(log);
		}catch (Exception e){
			e.printStackTrace();
		}
		return returnMsg;
	}

	@Override
	public ReturnMsg withdraw(String operator, String cardId,
			String password, double money) {
		// TODO Auto-generated method stub
		ReturnMsg cardMsg = checkCard(cardId, password);
		if(cardMsg.getStatus().equals(Status.ERROR))
			return cardMsg;
		
		ReturnMsg returnMsg = new ReturnMsg();
		Card card = cardDao.getCard(cardId, password);

		EnterpriseAccount account = accountDao.getAccount(card.getAccountId());
		if(account == null){
			returnMsg.setStatus(Status.ERROR);
			returnMsg.setMsg("This is not enterprise account");
			return returnMsg;
		}
		String enterpriseId = account.getEnterpriseId();
		double totalBalance = this.getEnterpriseBalance(enterpriseId);
		if(totalBalance - money < Enterprise.getBalanceLimit()){
			returnMsg.setStatus(Status.ERROR);
			returnMsg.setMsg("Enterprise's balance should exceed " + Enterprise.getBalanceLimit());
			return returnMsg;
		}
		
		double balance = account.getBalance();
		if(balance - money < 0){
			returnMsg.setStatus(Status.ERROR);
			returnMsg.setMsg("This account does not have enough balance.");
			return returnMsg;
		}
		else{
			account.setBalance(balance - money);
			accountDao.updateAccount(account);
			
			Log log = new Log(new Date(), "withdraw", operator, cardId, account.getId(), 
					accountType, 0, money, account.getBalance());
			returnMsg.setStatus(Status.OK);
			returnMsg.setMsg(String.valueOf(account.getBalance()));
			returnMsg.setLog(log);
			logDao.insertLog(log);
		}
		
		return returnMsg;
	}

	@Override
	public ReturnMsg query(String operator, String userId, String cardId,
			String password, Date start, Date end) {
		// TODO Auto-generated method stub
		ReturnMsg cardMsg = checkCard(cardId, password, userId);
		if(cardMsg.getStatus().equals(Status.ERROR))
			return cardMsg;
		
		Card card = cardDao.getCard(cardId, password);
		Account account = accountDao.getAccount(card.getAccountId());
		if(account == null){
			cardMsg.setStatus(Status.ERROR);
			cardMsg.setMsg("This is not enterprise account.");
			return cardMsg;
		}
		ArrayList<Log> logs = logDao.getLogListByAccountIdDate(card.getAccountId(), start, end);
		ReturnMsg returnMsg = new ReturnMsg();
		Log log = new Log(new Date(), "query", operator, cardId, card.getAccountId(), accountType, 0, 
				0, account.getBalance());
		logDao.insertLog(log);
		
		returnMsg.setStatus(Status.OK);
		returnMsg.setLogs(logs);
		
		return returnMsg;
	}

	@Override
	public ReturnMsg transfer(String operator, String userId, String cardId,
			String password, String username, String inCardId,
			String inUsername, double money) {
		// TODO Auto-generated method stub
		// check transfer out account 
		ReturnMsg cardMsg = checkCard(cardId, password, userId);
		if(cardMsg.getStatus().equals(Status.ERROR))
			return cardMsg;

		if(!userDao.getEnterpriseUser(userId).getName().equals(username)){
			cardMsg.setStatus(Status.ERROR);
			cardMsg.setMsg("The card " + cardId +" is not belong to " + username);
		}
		
		ReturnMsg returnMsg = new ReturnMsg();
		//check transfer in account
		Card inCard = cardDao.getCard(inCardId);
		if(inCard == null){
			returnMsg.setStatus(Status.ERROR);
			returnMsg.setMsg("Transfer in account not exist.");
			return returnMsg;
		}
		String inUserId = inCard.getUserId();
		if(!super.isUserIdUsernameAgreed(inUserId, inUsername)){
			returnMsg.setStatus(Status.ERROR);
			returnMsg.setMsg("inCard is not belong to " + inUsername);
			return returnMsg;
		}

		Card outCard = cardDao.getCard(cardId, password);
		
		// start transfer
		String outAccountId = outCard.getAccountId();
		String inAccountId = inCard.getAccountId();
		EnterpriseAccount outAccount = accountDao.getAccount(outAccountId);
		if(outAccount == null){
			returnMsg.setStatus(Status.ERROR);
			returnMsg.setMsg("This is not enterprise account");
			return returnMsg;
		}
		// check enterprise balance
		double totalBalance = this.getEnterpriseBalance(outAccount.getEnterpriseId());
		if(totalBalance - money < Enterprise.getBalanceLimit()){
			returnMsg.setStatus(Status.ERROR);
			returnMsg.setMsg("Enterprise's balance should exceed " + Enterprise.getBalanceLimit());
			return returnMsg;
		}
		
		// check balance
		double outBalance = outAccount.getBalance();
		if(outBalance < money){
			returnMsg.setStatus(Status.ERROR);
			returnMsg.setMsg("This account has not enough balance.");
			return returnMsg;
		}
		
		Account inAccount = super.getAccountById(inAccountId);

		if(inAccount == null){
			returnMsg.setStatus(Status.ERROR);
			returnMsg.setMsg("Something wrong with the code!!!");
			return returnMsg;
		}
		
		//update data
		outAccount.setBalance(outBalance - money);
		inAccount.setBalance(inAccount.getBalance() + money);
		try{
			if(inAccount.getClass().equals(Account.class) || inAccount.getClass().equals(VIPAccount.class)){
				//((AccountDao)DaoFactory.getInstance().getDao("AccountDao"))
				//.updateAccount(inAccount);
				returnMsg.setStatus(Status.ERROR);
				returnMsg.setMsg("enterprise user cannot transfer to individual user.");
				return returnMsg;
			}
			/*else if(inAccount.getClass().equals(VIPAccount.class)){
				((AccountDao)DaoFactory.getInstance().getDao("VIPAccountDao"))
				.updateAccount((VIPAccount)inAccount);
			}*/
			else if(inAccount.getClass().equals(EnterpriseAccount.class)){
				accountDao.updateAccount((EnterpriseAccount)inAccount);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		accountDao.updateAccount(outAccount);
		//record the log
		Log log = new Log(new Date(), "transferOut", operator, cardId, outAccountId, 
				accountType, 0,	money, outAccount.getBalance());
		logDao.insertLog(log);
		returnMsg.setLog(log);
		
		log = new Log(new Date(), "transferIn", operator, inCardId, inAccountId, 
				accountType, money, 0, inAccount.getBalance());
		logDao.insertLog(log);
		
		// return
		returnMsg.setStatus(Status.OK);
		returnMsg.setMsg(String.valueOf(outAccount.getBalance()));
			
		return returnMsg;
	}

	@Override
	public ReturnMsg closeAccount(String operator, String userId,
			String cardId, String password) {
		// TODO Auto-generated method stub
		ReturnMsg cardMsg = checkCard(cardId, password, userId);
		if(cardMsg.getStatus().equals(Status.ERROR))
			return cardMsg;
		
		ReturnMsg returnMsg = new ReturnMsg();
		Card card = cardDao.getCard(cardId, password);
		EnterpriseAccount account = accountDao.getAccount(card.getAccountId());
		if(account == null){
			returnMsg.setStatus(Status.ERROR);
			returnMsg.setMsg("This is not enterprise account");
			return returnMsg;
		}
		String accountId = account.getId();
		double balance = account.getBalance();
		if(balance > 0.01){
			returnMsg.setStatus(Status.ERROR);
			returnMsg.setMsg("please take the balance out first.");
			return returnMsg;
			//withdraw(operator, cardId, password, balance);
		}
		else if(balance < 0){
			returnMsg.setStatus(Status.ERROR);
			returnMsg.setMsg("please pay back the money first.");
			return returnMsg;
		}
		
			
		cardDao.deleteCard(cardId);
		if(cardDao.getCardsByAccountId(accountId).isEmpty()){
			accountDao.deleteAccount(accountId);
		}
		
		Log log = new Log(new Date(), "closeAccount", operator, cardId, accountId, 
				accountType, 0, balance, 0);
		returnMsg.setStatus(Status.OK);
		returnMsg.setLog(log);
		logDao.insertLog(log);
		
		return returnMsg;
	}

	@Override
	public ReturnMsg changePasswd(String operator, String userId,
			String cardId, String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		ReturnMsg cardMsg = checkCard(cardId, oldPassword, userId);
		if(cardMsg.getStatus().equals(Status.ERROR))
			return cardMsg;
		
		ReturnMsg returnMsg = new ReturnMsg();
		Card card = cardDao.getCard(cardId, oldPassword, userId);
		EnterpriseAccount account = accountDao.getAccount(card.getAccountId());
		if(account == null){
			returnMsg.setStatus(Status.ERROR);
			returnMsg.setMsg("This is not enterprise account");
			return returnMsg;
		}
		card.setPassword(newPassword);
		
		cardDao.updateCard(card);
		Log log = new Log(new Date(), "changePasswd", operator, cardId, account.getId(), 
				accountType, 0,	0, account.getBalance());
		logDao.insertLog(log);
		returnMsg.setLog(log);
		returnMsg.setStatus(Status.OK);
		return returnMsg;
	}
	
	public ReturnMsg addOperator(String operator, String userId, String cardId,
			String password, String newUserId, String newUsername, String newpassword){
		ReturnMsg cardMsg = checkCard(cardId, password, userId);
		if(cardMsg.getStatus().equals(Status.ERROR))
			return cardMsg;
		
		ReturnMsg returnMsg = new ReturnMsg();
		EnterpriseUser user = userDao.getEnterpriseUser(userId);
		if(user.isSuper() == false){
			returnMsg.setStatus(Status.ERROR);
			returnMsg.setMsg("privilege denied, only super user can do this.");
			return returnMsg;
		}
		
		Card card = cardDao.getCard(cardId, password);
		EnterpriseAccount account = accountDao.getAccount(card.getAccountId());
		if(account == null){
			returnMsg.setStatus(Status.ERROR);
			returnMsg.setMsg("This is not enterprise account");
			return returnMsg;
		}
		String accountId = account.getId();
		String enterpriseId = account.getEnterpriseId();
		
		//检查用户是否已注册过，如有测试id与名字是否一致
		EnterpriseUser newUser = userDao.getEnterpriseUser(newUserId);
		if(newUser != null){
			if(!newUser.getName().equals(newUsername)){
				returnMsg.setStatus(Status.ERROR);
				returnMsg.setMsg("newUserId and newUsername not agreed");
				return returnMsg;
			}
		}
		else {
			newUser = new EnterpriseUser(newUserId, newUsername, enterpriseId, true);
			userDao.insertUser(newUser);
		}
		
		
		String newCardId = String.valueOf(sequenceDao.getNextId("all"));
		Card newCard = new Card(newCardId, newpassword, accountId, newUserId);
		
		Date date = new Date();
		
		cardDao.insertCard(newCard);
		
		Log log = new Log(date, "addOperator", operator, cardId, accountId, accountType, 0, 0, account.getBalance());
		logDao.insertLog(log);
		log = new Log(date, "openAccount", operator, newCardId, accountId, accountType, 0, 0, account.getBalance());
		logDao.insertLog(log);
		
		returnMsg.setStatus(Status.OK);
		returnMsg.setMsg(newCardId);
		returnMsg.setLog(log);
		
		return returnMsg;
	}

}
