package bankSystem.persistence.dao.iface;
import bankSystem.entity.*;

public interface CardDao {
	Card getCard(String id);
	Card getCard(String id, String password);
	Card getCard(String id, String password, String userId);
	
	void insertCard(Card card);
	
	void deleteCard(String id);
	
	void updateCard(Card card);
}
