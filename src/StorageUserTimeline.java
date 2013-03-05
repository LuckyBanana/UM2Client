import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import twitter4j.Status;

public class StorageUserTimeline {

	StorageList<Status> statusList = new StorageList<Status>();
	private String filePath;
	Account currentUser;

	public StorageUserTimeline(String userId) {
		File file = new File("cache/usertweets/"+userId + ".tw");

		if (file.exists())
			;
		else
			try {
				file.createNewFile();
				this.setFilePath("cache/usertweets/"+userId + ".tw");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public StorageUserTimeline(Account account, String userId) {
		File file = new File("cache/"+account.getUserName()+"/usertweets/"+userId + ".tw");
		currentUser = account;
		if (file.exists())
			;
		else
			try {
				file.createNewFile();
				this.setFilePath("cache/"+account.getUserName()+"/usertweets/"+userId + ".tw");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void saveTweets() {
		try {
			FileOutputStream fos = new FileOutputStream("cache/"+currentUser.getUserName()+"/usertweets/"+currentUser.getUserName()+ ".tw");
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
			FileInputStream fis = new FileInputStream("cache/"+currentUser.getUserName()+"/usertweets/"+currentUser.getUserName()+ ".tw");
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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public int nbTweets() {
		return this.statusList.size();
	}

}
