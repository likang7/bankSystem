package bankSystem.service;

import java.util.Date;

import bankSystem.entity.Account;
import bankSystem.entity.AccountType;
import bankSystem.entity.Card;
import bankSystem.entity.Log;
import bankSystem.persistence.dao.DaoFactory;
import bankSystem.persistence.dao.iface.*;

public abstract class BusinessService {
	protected LogDao logDao;
	protected CardDao cardDao;
	protected SequenceDao sequenceDao;
	
	public BusinessService(){
		try{
			logDao = (LogDao)DaoFactory.getInstance().getDao("LogDao");
			cardDao = (CardDao)DaoFactory.getInstance().getDao("CardDao");
			sequenceDao = (SequenceDao)DaoFactory.getInstance().getDao("SequenceDao");
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
//	public abstract void openAccount(String operatorId, String userId,
//			String name, String accountType, double money, 
//			String password);
	
	protected static AccountType stringToAccountType(String stype){
		AccountType type;
		if(stype.equals("current"))
			type = AccountType.Current;
		else 
			type = AccountType.Fixed;
		return type;
	}
	
	protected ReturnMsg checkCard(String cardId, String password){
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			Card card = cardDao.getCard(cardId, password);
			if(card == null){
				returnMsg.setStatus(Status.ERROR);
				if(cardDao.getCard(cardId) == null){
					returnMsg.setMsg("Card not exist.");
				}
				else{
					returnMsg.setMsg("Password Error.");
				}
			}
			else {
				returnMsg.setStatus(Status.OK);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return returnMsg;
	}
	
	protected ReturnMsg checkCard(String cardId, String password, String userId){
		ReturnMsg returnMsg = new ReturnMsg();
		try{
			Card card = cardDao.getCard(cardId, password, userId);
			if(card == null){
				returnMsg.setStatus(Status.ERROR);
				if(cardDao.getCard(cardId) == null){
					returnMsg.setMsg("Card not exist.");
				} 
				else if(cardDao.getCard(cardId, password) == null){
					returnMsg.setMsg("Password Error");
				}
				else{
					returnMsg.setMsg("The card is not belong to this user.");
				}
			}
			else{
				returnMsg.setStatus(Status.OK);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return returnMsg;
	}
	
	public abstract ReturnMsg deposit(String operator, String cardId, 
			String password, double money);
	
	public abstract ReturnMsg withdraw(String operator, String cardId, 
			String password, double money);
	
	public abstract ReturnMsg query(String operator, String userId, String cardId,
			String password, Date start, Date end);
		
	public abstract ReturnMsg transfer(String operator, String userId, String cardId,
			String password, String username, String outCardId, String outUsername,
			double money);
	
	//普通个人用户以及企业用户均能继承使用
	public ReturnMsg changePasswd(String operator, String userId, String cardId,
			String oldPassword, String newPassword){
		ReturnMsg cardMsg = checkCard(cardId, oldPassword, userId);
		if(cardMsg.getStatus().equals(Status.ERROR))
			return cardMsg;
		
		ReturnMsg returnMsg = new ReturnMsg();
		Card card = cardDao.getCard(cardId, oldPassword, userId);
		try{
			AccountDao accountDao = (AccountDao)DaoFactory.getInstance().getDao("AccountDao");
			Account account = accountDao.getAccount(card.getAccountId());
			card.setPassword(newPassword);
			
			cardDao.updateCard(card);
			Log log = new Log(new Date(), "changePasswd", operator, cardId, account.getId(), 0, 
					0, account.getBalance());
			logDao.insertLog(log);
			cardMsg.setLog(log);
			cardMsg.setStatus(Status.OK);
		}catch (Exception e){
			e.printStackTrace();
		}
		return returnMsg;
	}
	
	public abstract ReturnMsg closeAccount(String operator, String userId, String cardId,
		 String password);
}
