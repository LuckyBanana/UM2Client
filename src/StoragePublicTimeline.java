import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import twitter4j.Status;

public class StoragePublicTimeline {

	StorageList<Status> statusList = new StorageList<Status>();
	Account currentUser;

	public StoragePublicTimeline() {
		File file = new File("cache/ptcache.tw");

		if (file.exists())
			;
		else
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public StoragePublicTimeline(Account account) {
		File file = new File("cache/"+account.getUserName()+"/ptcache.tw");
		currentUser = account;
		if (file.exists())
			;
		else
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void saveTweets() {
		try {
			FileOutputStream fos = new FileOutputStream("cache/"+currentUser.getUserName()+"/ptcache.tw");
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
			FileInputStream fis = new FileInputStream("cache/"+currentUser.getUserName()+"/ptcache.tw");
			ObjectInputStream ois = new ObjectInputStream(fis);
			statusList = (StorageList<Status>) ois.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
