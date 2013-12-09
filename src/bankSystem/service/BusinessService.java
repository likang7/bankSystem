package bankSystem.service;

import java.util.Date;

import bankSystem.entity.Account;
import bankSystem.entity.AccountType;
import bankSystem.entity.Card;
import bankSystem.entity.Log;
import bankSystem.entity.User;
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
	
	protected Account getAccountById(String id){
		Account account = null;
		try{		
			account = ((VIPAccountDao)DaoFactory.getInstance().getDao("VIPAccountDao")).getAccount(id);
			if(account == null){
				account = ((AccountDao)DaoFactory.getInstance().getDao("AccountDao")).getAccount(id);
				if(account == null){
					account = ((EnterpriseAccountDao)DaoFactory.getInstance().
							getDao("EnterpriseAccountDao")).getAccount(id);
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return account;
	}
	
	protected boolean isUserIdUsernameAgreed(String userId, String username){
		try{
			User user = null;
			user = ((IndividualUserDao)DaoFactory.getInstance().getDao("IndividualUserDao")).
			getIndividualUser(userId);
			if(user != null && user.getName().equals(username)){
				return true;
			}
			else if(((VIPUserDao)DaoFactory.getInstance().getDao("VIPUserDao")).
					getVIPUser(userId)!= null && ((VIPUserDao)DaoFactory.getInstance().getDao("VIPUserDao")).
							getVIPUser(userId).getName().equals(username)){
				return true;
			}
			else if(((EnterpriseUserDao)DaoFactory.getInstance().getDao("EnterpriseUserDao")).
					getEnterpriseUser(userId) != null && ((EnterpriseUserDao)DaoFactory.getInstance().getDao("EnterpriseUserDao")).
							getEnterpriseUser(userId).getName().equals(username)){
				return true;
			}
		}catch (Exception e){
			e.printStackTrace();
		}

		return false;
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
	
	public abstract ReturnMsg changePasswd(String operator, String userId, String cardId,
			String oldPassword, String newPassword);
	
	public abstract ReturnMsg closeAccount(String operator, String userId, String cardId,
		 String password);
}
