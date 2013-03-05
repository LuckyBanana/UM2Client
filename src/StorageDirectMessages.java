import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import twitter4j.DirectMessage;
import twitter4j.ResponseList;


public class StorageDirectMessages {
	
	protected StorageList<DirectMessage> directMessages = new StorageList<DirectMessage>();
	protected Account currentUser;
	
	/*
	 * Loved Tweets
	 */
	
	public StorageDirectMessages() {
		File file = new File("cache/directMessages.tw");

		if (file.exists())
			loadTweets();
		else
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public StorageDirectMessages(Account account) {
		File file = new File("cache/"+account.getUserName()+"/directMessages.tw");
		currentUser = account;
		if (file.exists())
			loadTweets();
		else
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void loveTweet(DirectMessage dm) {
		directMessages.add(dm);
	}
	
	public void unloveTweet(DirectMessage dm) {
		directMessages.remove(dm);
	}
	

	public void saveTweets() {
		try {
			FileOutputStream fos = new FileOutputStream("cache/"+currentUser.getUserName()+"/directMessages.tw");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(directMessages);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void loadTweets() {
		try {
			FileInputStream fis = new FileInputStream("cache/"+currentUser.getUserName()+"/directMessages.tw");
			ObjectInputStream ois = new ObjectInputStream(fis);
			directMessages = (StorageList<DirectMessage>) ois.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EOFException e) {
			// No Loved Tweets
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

}
