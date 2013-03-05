import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import twitter4j.Status;
import twitter4j.Tweet;

public class StorageSearch {

	StorageList<Tweet> statusList = new StorageList<Tweet>();
	private String filePath;
	private String search;
	protected Account currentUser;

	public StorageSearch(String search) {
		File file = new File("cache/search/"+search+".tw");

		if (file.exists())
			;
		else
			try {
				file.createNewFile();
				this.setFilePath("cache/search/"+search+".tw");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public StorageSearch(Account account, String search) {
		File file = new File("cache/"+account.getUserName()+"/search/"+search+".tw");
		currentUser = account;
		if (file.exists())
			;
		else
			try {
				file.createNewFile();
				this.setFilePath("cache/"+account.getUserName()+"/search/"+search+".tw");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void saveTweets() {
		try {
			FileOutputStream fos = new FileOutputStream(filePath);
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
			FileInputStream fis = new FileInputStream(filePath);
			ObjectInputStream ois = new ObjectInputStream(fis);
			statusList = (StorageList<Tweet>) ois.readObject();
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

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

}

