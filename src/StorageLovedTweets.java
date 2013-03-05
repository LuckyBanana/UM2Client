import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import twitter4j.Status;


public class StorageLovedTweets {
	
	protected StorageList<Status> lovedTweets;
	Account currentUser;
	
	/*
	 * Loved Tweets
	 */
	
	public StorageLovedTweets() {
		File file = new File("cache/lovedTweets.tw");

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
	
	public StorageLovedTweets(Account account) {
		File file = new File("cache/"+account.getUserName()+"/lovedTweets.tw");
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
	
	public void loveTweet(Status status) {
		lovedTweets.add(status);
	}
	
	public void unloveTweet(Status status) {
		lovedTweets.remove(status);
	}
	

	public void saveTweets() {
		try {
			FileOutputStream fos = new FileOutputStream("cache/"+currentUser.getUserName()+"/lovedTweets.tw");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(lovedTweets);
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
			FileInputStream fis = new FileInputStream("cache/"+currentUser.getUserName()+"/lovedTweets.tw");
			ObjectInputStream ois = new ObjectInputStream(fis);
			lovedTweets = (StorageList<Status>) ois.readObject();
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
