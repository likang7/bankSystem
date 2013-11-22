package bankSystem.persistence.dao.iface;
import java.util.ArrayList;

import bankSystem.entity.*;

public interface CardDao {
	Card getCard(String id);
	Card getCard(String id, String password);
	Card getCard(String id, String password, String userId);
	ArrayList<Card> getCardsByAccountId(String accountId);
	
	void insertCard(Card card);
	
	void deleteCard(String id);
	
	void updateCard(Card card);
}
