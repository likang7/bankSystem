package bankSystem.persistence.dao.impl;

import bankSystem.entity.Card;
import bankSystem.persistence.dao.iface.CardDao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class CardDaoImpl extends basicPersistence implements CardDao{
	private ArrayList<Card> cards = new ArrayList<Card>();
	private String persistencePath = persistenceRoot + "/cards.obj";
	
	public CardDaoImpl() throws Exception{
		ArrayList<Object> objects = readObject(persistenceRoot, persistencePath);
		for(Object object : objects){
			cards.add((Card)object);
		}
	}
	
	protected void save() throws Exception{
		ArrayList<Object> objects = new ArrayList<Object>();
		objects.addAll(cards);
		super.writeObject(persistenceRoot, persistencePath, objects);
	}
	
	@Override
	public Card getCard(String id) {
		// TODO Auto-generated method stub
		for(Card card : cards){
			if(card.getId().equals(id)){
				return card;
			}
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
	public ArrayList<Card> getCardsByAccountId(String accountId) {
		// TODO Auto-generated method stub
		ArrayList<Card> cards = new ArrayList<Card>();
		for(Card card : cards){
			if(card.getAccountId().equals(accountId))
				cards.add(card);
		}
		return cards;
	}

	@Override
	public void insertCard(Card card) {
		// TODO Auto-generated method stub
		cards.add(card);
		try{
			save();
		}catch (Exception e){
			e.printStackTrace();
		}

	}

	@Override
	public void deleteCard(String id) {
		// TODO Auto-generated method stub
		Iterator<Card> it = cards.iterator();
		while(it.hasNext()){
			Card a = it.next();
			if(a.getId().equals(id)){
				it.remove();
				try{
					save();
				}catch (Exception e){
					e.printStackTrace();
				}
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
	
	public static void main(String args[]) throws Exception{
//		File f = new File("persistence");
//		if(!f.exists()){
//			f.mkdirs();
//		}
//		//FileOutputStream out = new FileOutputStream("persistence/cards.obj");
//		//out.write(12345);
//		//out.close();
//		ObjectOutputStream out = new ObjectOutputStream
//                (new FileOutputStream("persistence/cards.obj"));
//		out.writeObject("hello world");
//		out.close();
//		
//		ObjectInputStream in = new ObjectInputStream
//				(new FileInputStream("persistence/cards.obj"));
//		String s = (String)in.readObject();
//		System.out.println(s);
//		in.close();
		CardDaoImpl c = new CardDaoImpl();
		Card card = new Card("10001240", "123456", "1000002", "111111");
		c.insertCard(card);
		System.out.println(c.getCard(card.getId()));
		ObjectOutputStream out = new ObjectOutputStream
				(new FileOutputStream("persistence/cards.obj"));
		out.writeObject(card);
		out.close();
		
		FileInputStream istream = new FileInputStream("persistence/cards.obj");
		ObjectInputStream in = new ObjectInputStream(istream);
		while(istream.available() > 0){
			Card cc = (Card)in.readObject();
			System.out.println(cc.toString());
		}
//		System.out.println(in.available());
//		Card cc = (Card)in.readObject();
//		System.out.println(cc.toString());
//		System.out.println(in.readObject());
		in.close();
		Object o = card;
		System.out.println(o);
	}

}
