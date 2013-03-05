import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import twitter4j.Status;

public class StorageTrendTimeline {

	StorageList<Status> statusList = new StorageList<Status>();
	private String filePath;
	protected Account currentUser;

	public StorageTrendTimeline(String trendId) {
		File file = new File("cache/trends/"+trendId + ".tw");

		if (file.exists())
			;
		else
			try {
				file.createNewFile();
				this.setFilePath("cache/trends/"+trendId + ".tw");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public StorageTrendTimeline(Account account, String trendId) {
		File file = new File("cache/"+account.getUserName()+"/trends/"+trendId + ".tw");
		currentUser = account;
		if (file.exists())
			;
		else
			try {
				file.createNewFile();
				this.setFilePath("cache/"+account.getUserName()+"/trends/"+trendId + ".tw");
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

}

