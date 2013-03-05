import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import twitter4j.Status;


public class HomeTimelineStorage {
        
        StorageList<Status> statusList = new StorageList<Status>();
        
        public HomeTimelineStorage() {
                File file = new File("htcache.tw");
                
                if(file.exists());
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
                        FileOutputStream fos = new FileOutputStream("htcache.tw");
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
                        FileInputStream fis = new FileInputStream("htcache.tw");
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
