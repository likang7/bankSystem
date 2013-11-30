package bankSystem.persistence.dao.sqlimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bankSystem.entity.Card;
import bankSystem.persistence.dao.iface.CardDao;

public class CardDaoSqlImpl implements CardDao {
	private DaoOperateTemplate daoTemplate = new DaoOperateTemplate();  
	@Override
	public Card getCard(String id) {
		// TODO Auto-generated method stub
		try{
			String sql = "select * from card where id=?";
			Object[] args = new Object[] {id};
			Card card = (Card) daoTemplate.find(sql, args, new CardRowMapper());
			return card;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Card getCard(String id, String password) {
		// TODO Auto-generated method stub
		try{
			String sql = "select * from card where id=? and password=?";
			Object[] args = new Object[] {id, password};
			Card card = (Card) daoTemplate.find(sql, args, new CardRowMapper());
			return card;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Card getCard(String id, String password, String userId) {
		// TODO Auto-generated method stub
		try{
			String sql = "select * from card where id=? and password=? and userId=?";
			Object[] args = new Object[] {id, password, userId};
			Card card = (Card) daoTemplate.find(sql, args, new CardRowMapper());
			return card;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Card> getCardsByAccountId(String accountId) {
		// TODO Auto-generated method stub
		try{
			String sql = "select * from card where accountId";
			Object[] args = new Object[] {accountId};
			List<Object> cardsList = daoTemplate.query(sql, args, new CardRowMapper());
			ArrayList<Card> cards = new ArrayList<Card>();
			Iterator<Object> it = cardsList.iterator();
			while(it.hasNext()){
				cards.add((Card)it.next());
			}
			return cards;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insertCard(Card card) {
		// TODO Auto-generated method stub
		try{
			String sql = "insert into card(id, password, accountId, userId) values (?,?,?,?)";
			Object[] args = new Object[]{card.getId(), card.getPassword(),
					card.getAccountId(), card.getUserId()};
			daoTemplate.update(sql, args, false);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void deleteCard(String id) {
		// TODO Auto-generated method stub
		try{
			String sql = "delete from card where id=?";
			Object[] args = new Object[]{id};
			daoTemplate.update(sql, args, false);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void updateCard(Card card) {
		// TODO Auto-generated method stub
		try{
			String sql = "update card set password=? where id=?";
			Object[] args = new Object[]{card.getPassword(), card.getId()};
			daoTemplate.update(sql, args, false);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	class CardRowMapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs) throws SQLException {
			// TODO Auto-generated method stub
			Card card = new Card(
					rs.getString("id"),
					rs.getString("password"),
					rs.getString("accountId"),
					rs.getString("userId"));
			return card;
		}
	}

}
