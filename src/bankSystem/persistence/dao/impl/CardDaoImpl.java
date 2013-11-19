package bankSystem.persistence.dao.impl;

import bankSystem.entity.Card;
import bankSystem.persistence.dao.iface.CardDao;

import java.util.ArrayList;
import java.util.Iterator;

public class CardDaoImpl implements CardDao{
	private static ArrayList<Card> cards = new ArrayList<Card>();
	
	@Override
	public Card getCard(String id) {
		// TODO Auto-generated method stub
		for(Card card : cards){
			if(card.getId().equals(id))
				return card;
		}
		return null;
	}

	@Override
	public Card getCard(String id, String password) {
		// TODO Auto-generated method stub
		for(Card card : cards){
			if(card.getId().equals(id) && card.getPassword().equals(password))
				return card;
		}
		return null;
	}

	@Override
	public Card getCard(String id, String password, String userId) {
		// TODO Auto-generated method stub
		for(Card card : cards){
			if(card.getId().equals(id) && card.getPassword().equals(password) && 
					card.getUserId().equals(userId))
				return card;
		}
		return null;
	}

	@Override
	public void insertCard(Card card) {
		// TODO Auto-generated method stub
		cards.add(card);
	}

	@Override
	public void deleteCard(String id) {
		// TODO Auto-generated method stub
		Iterator<Card> it = cards.iterator();
		while(it.hasNext()){
			Card a = it.next();
			if(a.getId().equals(id)){
				it.remove();
				break;
			}
		}
	}

	@Override
	public void updateCard(Card card) {
		// TODO Auto-generated method stub
		deleteCard(card.getId());
		insertCard(card);
	}

}
