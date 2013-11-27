package bankSystem.service;

import java.util.ArrayList;
import java.util.Date;

import bankSystem.entity.*;
import bankSystem.persistence.dao.DaoFactory;
import bankSystem.persistence.dao.iface.*;

public class IndividualBusinessService extends BusinessService {
	private IndividualUserDao userDao;
	private AccountDao accountDao;
	private static String accountType = "individual";
	
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
		
		IndividualUser user = userDao.getIndividualUser(userId);
		if(user != null){
			if(!user.getName().equals(name)){
				returnMsg.setStatus(Status.ERROR);
				returnMsg.setMsg("userId and username not agreed");
				return returnMsg;
			}
		}
		else{
			user = new IndividualUser(userId, name);
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
		
		Log log = new Log(date, "openAccount", operatorId, cardId, accountId, accountType, money, 0, money);
		logDao.insertLog(log);
		
		returnMsg.setStatus(Status.OK);
		returnMsg.setMsg(cardId);
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
		Account account = accountDao.getAccount(card.getAccountId());
		double balance = account.getBalance();
		
		if(balance - money < 0){
			returnMsg.setStatus(Status.ERROR);
			returnMsg.setMsg("This account does not have enough balance.");
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
	public ReturnMsg transfer(String operator, String userId, String cardId,
			String password, String username, String inCardId,
			String inUsername, double money) {
		// TODO Auto-generated method stub
		// check transfer out account 
		ReturnMsg cardMsg = checkCard(cardId, password, userId);
		if(cardMsg.getStatus().equals(Status.ERROR))
			return cardMsg;

		if(!userDao.getIndividualUser(userId).getName().equals(username)){
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
		
		if(!inUserId.equals(userId)){
			returnMsg.setStatus(Status.ERROR);
			returnMsg.setMsg("individual user can only transfer to his own card.");
			return returnMsg;
		}
		Card outCard = cardDao.getCard(cardId, password);		
		// start transfer
		String outAccountId = outCard.getAccountId();
		String inAccountId = inCard.getAccountId();
		Account outAccount = accountDao.getAccount(outAccountId);
		double outBalance = outAccount.getBalance();
		// check balance
		if(outBalance < money){
			returnMsg.setStatus(Status.ERROR);
			returnMsg.setMsg("This account has not enough balance.");
			return returnMsg;
		}
		
		
		Account inAccount = super.getAccountById(inAccountId);
		//here we should check VIP account in the future
		if(inAccount == null){
			returnMsg.setStatus(Status.ERROR);
			returnMsg.setMsg("Something wrong with the code!!!");
			return returnMsg;
		}
		
		//update data
		outAccount.setBalance(outBalance - money);
		inAccount.setBalance(inAccount.getBalance() + money);
		accountDao.updateAccount(outAccount);
		try{
			if(inAccount.getClass().equals(VIPAccount.class)){
				((AccountDao)DaoFactory.getInstance().getDao("VIPAccountDao"))
					.updateAccount((VIPAccount)inAccount);
			}
			else if(inAccount.getClass().equals(Account.class)){
				accountDao.updateAccount(inAccount);
			}
			else if(inAccount.getClass().equals(EnterpriseAccount.class)){
				((EnterpriseAccountDao)DaoFactory.getInstance().getDao("EnterpriseAccountDao"))
					.updateAccount((EnterpriseAccount)inAccount);
			}
			else{
				throw new Exception();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		
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
			cardMsg.setMsg("This is not individual account.");
			return cardMsg;
		}
		
		ArrayList<Log> logs = logDao.getLogListByAccountIdDate(card.getAccountId(), start, end);
		ReturnMsg returnMsg = new ReturnMsg();
		Log log = new Log(new Date(), "query", operator, cardId, card.getAccountId(), accountType, 0, 
				0, account.getBalance());
		
		returnMsg.setStatus(Status.OK);
		returnMsg.setLogs(logs);
		
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
		Account account = accountDao.getAccount(card.getAccountId());
		String accountId = account.getId();
		double balance = account.getBalance();
		if(balance > 0.01){
			returnMsg.setStatus(Status.ERROR);
			returnMsg.setMsg("please take the balance out first.");
			return returnMsg;
			//withdraw(operator, cardId, password, balance);
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
	
	public ReturnMsg changePasswd(String operator, String userId, String cardId,
			String oldPassword, String newPassword){
		ReturnMsg cardMsg = checkCard(cardId, oldPassword, userId);
		if(cardMsg.getStatus().equals(Status.ERROR))
			return cardMsg;
		
		ReturnMsg returnMsg = new ReturnMsg();
		Card card = cardDao.getCard(cardId, oldPassword, userId);
		Account account = accountDao.getAccount(card.getAccountId());
		card.setPassword(newPassword);
		
		cardDao.updateCard(card);
		Log log = new Log(new Date(), "changePasswd", operator, cardId, account.getId(), 
				accountType, 0,	0, account.getBalance());
		logDao.insertLog(log);
		returnMsg.setLog(log);
		returnMsg.setStatus(Status.OK);
		return returnMsg;
	}

	public static void main(String args[]){
		IndividualBusinessService businessService = new IndividualBusinessService();
		ReturnMsg msg1 = businessService.openAccount("test", "001", "user1", "fixed", 10000, "123456");
		System.out.println(msg1.getStatus());
		System.out.println(msg1.getMsg());
		ReturnMsg msg2 = businessService.openAccount("test", "002", "user2", "fixed", 10000, "123456");
		System.out.println(msg2.getStatus());
		System.out.println(msg2.getMsg());
		
		ReturnMsg msg3 = businessService.deposit("test", msg1.getMsg(), "123456", 8000);
		if(msg3.getStatus().equals(Status.OK)){
			System.out.println(msg3.getLog());
		}
		else{
			System.out.println(msg3.getMsg());
		}
		
		ReturnMsg msg4 = businessService.withdraw("test", msg1.getMsg(), "123456", 10000);
		if(msg4.getStatus().equals(Status.OK)){
			System.out.println(msg4.getLog());
		}
		else{
			System.out.println(msg4.getMsg());
		}

	}
}
