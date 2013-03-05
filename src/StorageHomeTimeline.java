import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import twitter4j.Status;

public class StorageHomeTimeline {

	StorageList<Status> statusList = new StorageList<Status>();
	Account currentUser;

	public StorageHomeTimeline() {
		File file = new File("cache/htcache.tw");

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
	
	public StorageHomeTimeline(Account account) {
		
		currentUser = account;
		File file = new File("cache/"+ account.getUserName()+ "/htcache.tw");
		
		if (file.exists())
		{
			loadTweets();
			System.out.println("file exist");
		}
		else
		{
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void saveTweets() {
		try {
			statusList.sortTweets();
			FileOutputStream fos = new FileOutputStream("cache/"+currentUser.getUserName()+"/htcache.tw");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(statusList);
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
			FileInputStream fis = new FileInputStream("cache/"+currentUser.getUserName()+"/htcache.tw");
			ObjectInputStream ois = new ObjectInputStream(fis);
			statusList = (StorageList<Status>) ois.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EOFException e) {

		}	
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
