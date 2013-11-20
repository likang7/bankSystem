package bankSystem.service;

import bankSystem.entity.AccountType;
import bankSystem.entity.Card;
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
			CardDao cardDao = (CardDao)DaoFactory.getInstance().getDao("CardDao");
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
	
	public abstract ReturnMsg deposit(String operator, String cardId, 
			String password, double money);
	
	public abstract ReturnMsg withdraw(String operator, String userId, String cardId, 
			String password, String money);
}
