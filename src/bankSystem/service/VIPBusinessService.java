package bankSystem.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import bankSystem.entity.Account;
import bankSystem.entity.EnterpriseAccount;
import bankSystem.entity.VIPAccount;
import bankSystem.entity.AccountType;
import bankSystem.entity.Card;
import bankSystem.entity.IndividualUser;
import bankSystem.entity.Log;
import bankSystem.entity.VIPAccount;
import bankSystem.entity.VIPUser;
import bankSystem.persistence.dao.DaoFactory;
import bankSystem.persistence.dao.iface.*;

public class VIPBusinessService extends BusinessService {
	private VIPUserDao userDao;
	private VIPAccountDao accountDao;
	private static String accountType = "VIP";
	
	public VIPBusinessService(){
		super();
		try{
			userDao = (VIPUserDao)DaoFactory.getInstance().getDao("VIPUserDao");
			accountDao = (VIPAccountDao)DaoFactory.getInstance().getDao("VIPAccountDao");
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public ReturnMsg openAccount(String operatorId, String userId,
			String name, String accountType, double money, String password){
		ReturnMsg returnMsg = new ReturnMsg();
		
		if(!VIPAccount.isBalanceEnough(money)){
			returnMsg.setStatus(Status.ERROR);
			returnMsg.setMsg("Balance not enough.");
			return returnMsg;
		}
		
		VIPUser user = userDao.getVIPUser(userId);
		if(user != null){
			if(!user.getName().equals(name)){
				returnMsg.setStatus(Status.ERROR);
				returnMsg.setMsg("userId and username not agreed");
				return returnMsg;
			}
		}
		else{
			user = new VIPUser(userId, name);
			userDao.insertUser(user);
		}
		String cardId = sequenceDao.getNextId();
		String accountId = sequenceDao.getNextId();
		Card card = new Card(cardId, password, accountId, userId);
		
		AccountType type = super.stringToAccountType(accountType);
		Date date = new Date();
		VIPAccount account = new VIPAccount(accountId, type, money, date);
		
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
			VIPAccount account = accountDao.getAccount(card.getAccountId());
			if(account == null){
				returnMsg.setStatus(Status.ERROR);
				returnMsg.setMsg("This is not VIP account");
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
		VIPAccount account = accountDao.getAccount(card.getAccountId());
		if(account == null){
			returnMsg.setStatus(Status.ERROR);
			returnMsg.setMsg("This is not VIP account");
			return returnMsg;
		}
		double balance = account.getBalance();
		
		if(account.isFrozen() == true){
			returnMsg.setStatus(Status.ERROR);
			returnMsg.setMsg("This card has benn frozen");
			return returnMsg;
		}
		
		if(balance + VIPAccount.getExcesslimit() < money){
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
			cardMsg.setMsg("This is not vip account.");
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

		if(!userDao.getVIPUser(userId).getName().equals(username)){
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
		VIPAccount outAccount = accountDao.getAccount(outAccountId);
		if(outAccount == null){
			returnMsg.setStatus(Status.ERROR);
			returnMsg.setMsg("This is not VIP account");
			return returnMsg;
		}
		
		if(outAccount.isFrozen() == true){
			returnMsg.setStatus(Status.ERROR);
			returnMsg.setMsg("This card has benn frozen");
			return returnMsg;
		}
		
		double outBalance = outAccount.getBalance();
		// check balance
		if(outBalance + VIPAccount.getExcesslimit() < money){
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
		accountDao.updateAccount(outAccount);
		try{
			if(inAccount.getClass().equals(Account.class)){
				((AccountDao)DaoFactory.getInstance().getDao("AccountDao")).updateAccount(inAccount);
			}
			else if(inAccount.getClass().equals(VIPAccount.class)){
				accountDao.updateAccount((VIPAccount)inAccount);
			}
			else if(inAccount.getClass().equals(EnterpriseAccount.class)){
				((EnterpriseAccountDao)DaoFactory.getInstance().getDao("EnterpriseAccountDao"))
					.updateAccount((EnterpriseAccount)inAccount);
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
	public ReturnMsg closeAccount(String operator, String userId,
			String cardId, String password) {
		// TODO Auto-generated method stub
		ReturnMsg cardMsg = checkCard(cardId, password, userId);
		if(cardMsg.getStatus().equals(Status.ERROR))
			return cardMsg;
		
		ReturnMsg returnMsg = new ReturnMsg();
		Card card = cardDao.getCard(cardId, password);
		VIPAccount account = accountDao.getAccount(card.getAccountId());
		if(account == null){
			returnMsg.setStatus(Status.ERROR);
			returnMsg.setMsg("This is not VIP account");
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
		VIPAccount account = accountDao.getAccount(card.getAccountId());
		if(account == null){
			returnMsg.setStatus(Status.ERROR);
			returnMsg.setMsg("This is not VIP account");
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
	
	public void checkPaidback(){
		ArrayList<VIPAccount> accounts = accountDao.getAllAccounts();
		for(VIPAccount account : accounts){
			//check has paid back money
			if(account.getBalance() < 0){
				if(account.isFrozen()){
					continue;
				}
				Date excessStart = account.getExcessStart();
				Date now = new Date();
				if(excessStart == null){
					account.setExcessStart(now);
				}
				else{
					long days = (now.getTime() - excessStart.getTime()) / (24*60*60*1000); 
					if(days >= 30){
						account.setFrozen(true);
						accountDao.updateAccount(account);
					}
				}
			}
			else{
				if(account.getExcessStart() == null){
					continue;
				}
				account.setExcessStart(null);
				account.setFrozen(false);
				accountDao.updateAccount(account);
			}
		}
	}
	
	public void checkBalance(){
		ArrayList<VIPAccount> accounts = accountDao.getAllAccounts();
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		
		Date now = new Date();
		String day = sdf.format(now);
		for(VIPAccount account : accounts){
			Date openDate = account.getOpenDate();
			long days = (now.getTime() - openDate.getTime()) / (24*60*60*1000); 
			if(days < 30){
				continue;
			}
			// every month's 1st check
			double totBalance = account.getAccBalanceThisMonth();
			if(day.equals("01")){
				short failMonths = account.getAccFailMonths();
				if(totBalance < 100000 * 30){
					failMonths++;
					if(failMonths >= 2){
						account.setBalance(account.getBalance() - 1000);
						Log log = new Log(new Date(), "managementFee", "root", "system", account.getId(), 
								accountType, 0,	1000, account.getBalance());
						logDao.insertLog(log);
					}
				}
				else{
					failMonths = 0;
				}
				account.setAccFailMonths(failMonths);
				account.setAccBalanceThisMonth(account.getBalance());
			}
			else{
				account.setAccBalanceThisMonth(totBalance + account.getBalance());
			}
			accountDao.updateAccount(account);
		}
	}
	
	public static void main(String[] args){
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		
		Date now = new Date();
		String str = sdf.format(now);
		System.out.println(str);
	}
	
}
