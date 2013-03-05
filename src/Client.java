import java.awt.Desktop;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;

import twitter4j.DirectMessage;
import twitter4j.HashtagEntity;
import twitter4j.MediaEntity;
import twitter4j.Paging;
import twitter4j.ProfileImage;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.RelatedResults;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.URLEntity;
import twitter4j.User;
import twitter4j.UserList;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class Client {
	
	Account currentUser;
	Twitter twitter = new TwitterFactory().getInstance();
	protected StorageLovedTweets lovedTweets;
	protected StorageHomeTimeline homeTimelineTweets;
	protected StorageDirectMessages directMessage;
	protected StorageFavorites favsTweets;
	protected StorageMentions mentionTweet;
	protected StorageUserTimeline currentUserTweets;

	public Client() {

		twitter.setOAuthConsumer("Z2zk13tMZFzNUiDxCs4Bw",
				"HYk91Rol6BqiI0D2JdOiukZZYe6Nj9B0uFPFhSzmN7A");
		createCacheDIR();
		createCredentialsDB();
	}
	
	public void createCacheDIR() {
		@SuppressWarnings("unused")
		boolean file = new File("cache/").mkdir();
	}
	
	public void createCredentialsDB() {
		try {
			File file = new File("cache/storeCredentials.tw");
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("Impossible de créer le fichier");
		}
	}
	
	public void loadStorageUnits() {
		this.homeTimelineTweets = new StorageHomeTimeline(currentUser);
		this.lovedTweets = new StorageLovedTweets(currentUser);
		this.directMessage = new StorageDirectMessages(currentUser);
		this.favsTweets = new StorageFavorites(currentUser);
		this.mentionTweet = new StorageMentions(currentUser);
		this.currentUserTweets = new StorageUserTimeline(currentUser, currentUser.userName);
	}

	// Step 1 : Create account
	// Step 2 : Link account with pin
	// Step 3 : Get authentication from twitter
	// Step 4 : Store the new account in storeCredentials.tw

	// Step 1 :

	@SuppressWarnings("unused")
	public Account createAccount(String userName, String password) throws UserAlreadyRegisteredException{	
		try {
			FileInputStream fis;
			fis = new FileInputStream(new File("cache/storeCredentials.tw"));
			ObjectInputStream ois = new ObjectInputStream(fis);
			AccountList credentials = new AccountList();
			credentials.addAccount((Account) ois.readObject());
			
			for(int i = 0; i < credentials.getList().size(); i++) {
				if(credentials.getList().get(i).getUserName().equalsIgnoreCase(userName)) {
					throw new UserAlreadyRegisteredException();
				}
				else {
					Account newUser = new Account(userName, password);
					return newUser;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch(EOFException e) {
			Account newUser = new Account(userName, password);
			return newUser;
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
		
	}

	// Step 2 :

	public RequestToken requestPin() {
		// use pin to get credentials

		// opening browser to get pin
		Desktop desk = null;
		try {
			RequestToken requestToken = twitter.getOAuthRequestToken();
			URI auto = new URI(requestToken.getAuthorizationURL());
			if (Desktop.isDesktopSupported()) {
				desk = Desktop.getDesktop();
				desk.browse(auto);
			}
			return requestToken;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("nié");
			// if return null - try again
			return null;
		}
	}

	// Step 3 & 4 :

	public void loginWithPin(Account account, String pin, RequestToken requestToken,
			int rememberMe) {
		try {
			//AccessToken accessToken = null;

			// Entrer le pin
			try {
				if (pin.length() > 0) {
					AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, pin);
					System.out.println(accessToken);
					System.out.println(account);
					account.setUserAccessToken(accessToken);
					account.setUser(twitter.verifyCredentials());
					// credentials.ajouterCompte(newUser);
					// update the new credentials list by adding the new object
					// in the file
					FileOutputStream fos = new FileOutputStream("cache/storeCredentials.tw");
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					// create new user
					oos.writeObject(account);
					oos.close();
					fos.close();
					currentUser = account;
					this.loadStorageUnits();
					if (rememberMe == 1) {
						createSession(account);
					}
					
					@SuppressWarnings("unused")
					MainFrame main = new MainFrame(this);
					
					System.out.println("Connecté");
				} else {
					@SuppressWarnings("unused")
					AccessToken accessToken = twitter.getOAuthAccessToken();
				}
			} catch (TwitterException te) {
				if (401 == te.getStatusCode()) {
					System.out.println("Unable to get the access token.");
				} else {
					te.printStackTrace();
				}
			}
		} catch (Exception e) {
			// oops sth went wrong
			e.printStackTrace();
		}

	}

	// Try to login

	// Returns 0 : if username matches password
	// Returns 1 : if username doesn't matches password
	// Returns 2 : if username isn't in db
	// Returns -1 : if storeCredentials.tw doesn't exist
	// Returns -2 : if IO error

	public int testLogin(String id, String pw) {

		try {
			FileInputStream fis = new FileInputStream(new File("cache/storeCredentials.tw"));
			ObjectInputStream ois = new ObjectInputStream(fis);
			AccountList credentials = new AccountList();
			credentials.addAccount((Account) ois.readObject());

			// User already registered ?
			for (int i = 0; i < credentials.getList().size(); i++) {
				if (credentials.getList().get(i).getUserName()
						.equalsIgnoreCase(id)) {
					if (credentials.getList().get(i).getPassword().equals(pw)) {
						return 0;
					} else {
						// Wrong password
						return 1;
					}
				}
			}
			// User not registered
			ois.close();
			fis.close();
			return 2;
		} catch (FileNotFoundException e) {
			// storeCredentials.tw doesn't exists
			System.out.println("sc.tw doesn't exists");
			return -1;
		} catch(EOFException e) { 
			return -1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -2;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -2;
		}

	}

	public void login(String user, int rememberMe) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(new File("cache/storeCredentials.tw"));
			ObjectInputStream ois = new ObjectInputStream(fis);
			AccountList credentials = new AccountList();
			credentials.addAccount((Account) ois.readObject());

			for (int i = 0; i < credentials.getList().size(); i++) {
				if (credentials.getList().get(i).getUserName().equalsIgnoreCase(user)) {
					AccessToken accessToken = credentials.getList().get(i).getUserAccessToken();
					twitter.setOAuthAccessToken(accessToken);
					currentUser = credentials.getList().get(i);
					this.loadStorageUnits();
					// loop exit
					if (rememberMe == 1) {
						createSession(credentials.getList().get(i));
					}
					throw new AuthOKException();
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AuthOKException e) {
			System.out.println("Connecté.");
		}

	}

	/*
	 * Sending Tweet & Direct Message
	 */

	public String sendTweet(String t) {
		try {
			twitter.updateStatus(t);
			return("Your tweet has been post !");
		} catch (TwitterException e) {
			e.printStackTrace();
			return("An error occured !");
			// oops sth went wrong - try again later ?
		}
	}

	public  String sendDirectMessage(String user, String text) {
		try {
			twitter.sendDirectMessage(user, text);
			return("Your direct message has been send");
		} catch (TwitterException e) {
			e.printStackTrace();
			return("An error occured !");
			// try again later ?
		}
	}

	/*
	 * Retweet & Favorite & Reply & Delete Tweet
	 */

	public void createFavorite(long statusId) {
		try {
			twitter.createFavorite(statusId);
		} catch (TwitterException e) {
			e.printStackTrace();
			// bad statusId ?
		}
	}

	public void destroyFavorite(long statusId) {
		try {
			twitter.destroyFavorite(statusId);
		} catch (TwitterException e) {
			e.printStackTrace();
			// bad statusId ?
		}
	}

	public void retweet(long statusId) {
		try {
			twitter.retweetStatus(statusId);
		} catch (TwitterException e) {
			e.printStackTrace();
			// bad statusId ?
		}
	}

	public String reply(long statusId, long userId, String t) {
		try {
			twitter.updateStatus(new StatusUpdate("@"+ twitter.showUser(userId).getScreenName() +" "+ t).inReplyToStatusId(statusId));
			return("Your reply has been send");
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return("An error occured");
		}
	}
	
	public String reply(Status status, String t) {
		try {
			twitter.updateStatus(new StatusUpdate(t).inReplyToStatusId(status.getId()));
			return("Your reply has been send");
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return("An error occured");
		}
		
		
	}

	public String showUser(long statusId)
	{
		try {
			return twitter.showUser(statusId).getScreenName();
			
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public long showUserId(long statusId)
	{
		try{
			return twitter.showUser(statusId).getId();
		}
		catch (TwitterException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public void deleteTweet(long statusId) {
		try {
			twitter.destroyStatus(statusId);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Search by keyword
	 */

	public StorageList<Tweet> search(String t) {
		Query query = new Query(t);
		try {
			StorageSearch searchTweets = new StorageSearch(currentUser, t);
			QueryResult result = twitter.search(query);
			searchTweets.statusList.addSearchList(result.getTweets());
			return searchTweets.statusList;
		} catch (TwitterException e) {
			e.printStackTrace();
			// oops sth went wrong
			return null;
		}

	}
	
	/*
	 * Getting Direct Messages
	 */
	
	public StorageList<DirectMessage> getDirectMessages() {
		try {
			StorageDirectMessages sdm = new StorageDirectMessages(currentUser);
			ResponseList<DirectMessage> directMessages = twitter.getDirectMessages();
			sdm.directMessages.addStatusList(directMessages);
			sdm.saveTweets();
			return sdm.directMessages;
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public StorageList<DirectMessage> getDirectMessages(Paging p) {
		try {
			StorageDirectMessages sdm = new StorageDirectMessages(currentUser);
			ResponseList<DirectMessage> directMessages = twitter.getDirectMessages();
			sdm.directMessages.addStatusList(directMessages);
			sdm.saveTweets();
			return sdm.directMessages;
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * Getting Tweets
	 */

	// Home Timeline

	public StorageList<Status> getHomeTimeline() {
		try {
			StorageHomeTimeline hts = new StorageHomeTimeline(currentUser);
			ResponseList<Status> tweets = twitter.getHomeTimeline();
			hts.statusList.addStatusList(tweets);
			hts.saveTweets();
			return hts.statusList;
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public StorageList<Status> getHomeTimeline(Paging p) {
		try {
			StorageHomeTimeline hts = new StorageHomeTimeline(currentUser);
			ResponseList<Status> tweets = twitter.getHomeTimeline(p);
			hts.statusList.addStatusList(tweets);
			hts.saveTweets();
			System.out.println(tweets.getRateLimitStatus());
			return hts.statusList;
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// Public Timeline twitter.getPublicTimeline()

	public ResponseList<Status> getPublicTimeline() {
		try {
			StoragePublicTimeline pts = new StoragePublicTimeline();
			ResponseList<Status> tweets = twitter.getUserTimeline();
			pts.statusList.addStatusList(tweets);
			return tweets;
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// Logged user Timeline

	public ResponseList<Status> getUserTimeline() {
		try {
			StorageUserTimeline uts = new StorageUserTimeline(currentUser, currentUser.getUserName());
			ResponseList<Status> tweets = twitter.getUserTimeline();
			uts.statusList.addStatusList(tweets);
			return tweets;
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public StorageList<Status> getUserTimeline(Paging p) {
		try {
			StorageUserTimeline uts = new StorageUserTimeline(currentUser, currentUser.getUserName());
			ResponseList<Status> tweets = twitter.getUserTimeline(p);
			uts.statusList.addStatusList(tweets);
			//uts.saveTweets();
			return uts.statusList;
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// Retrieve user timeline by userId

	public StorageList<Status> getUserTimelineById(long userId) {
		try {
			StorageUserTimeline uts = new StorageUserTimeline("user" + userId);
			ResponseList<Status> tweets = twitter.getUserTimeline(userId);
			uts.statusList.addStatusList(tweets);
			uts.saveTweets();
			return uts.statusList;
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public ResponseList<Status> getUserTimelineById(long userId, Paging p) {
		try {
			StorageUserTimeline uts = new StorageUserTimeline("user" + userId);
			ResponseList<Status> tweets = twitter.getUserTimeline(userId, p);
			uts.statusList.addStatusList(tweets);
			return tweets;
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	// get user mentions
	
	public StorageList<Status> getMentionsTimeline() {
		try {
			StorageMentions ms = new StorageMentions(currentUser);
			ResponseList<Status> tweets = twitter.getMentions();
			ms.statusList.addStatusList(tweets);
			ms.saveTweets();
			return ms.statusList;
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public StorageList<Status> getMentionsTimeline(Paging p) {
		try {
			StorageMentions ms = new StorageMentions(currentUser);
			ResponseList<Status> tweets = twitter.getMentions(p);
			ms.statusList.addStatusList(tweets);
			ms.saveTweets();
			return ms.statusList;
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	// get favorites
	
	public StorageList<Status> getFavorites() {
		try {
			StorageFavorites sf = new StorageFavorites(currentUser);
			ResponseList<Status> tweets = twitter.getFavorites();
			sf.statusList.addStatusList(tweets);
			sf.saveTweets();
			return sf.statusList;
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public StorageList<Status> getFavorites(Paging p) {
		try {
			StorageFavorites sf = new StorageFavorites(currentUser);
			ResponseList<Status> tweets = twitter.getFavorites(p);
			sf.statusList.addStatusList(tweets);
			sf.saveTweets();
			return sf.statusList;
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	// ???
	
	public StorageList<Status> getInteractionsTimeline() {
		
		return null;
	}
	
	public StorageList<UserList> getUserLists() {
		
		try {
			StorageUserLists ms = new StorageUserLists(currentUser);
			ResponseList<UserList> tweets = twitter.getUserLists(twitter.getId(), -1);
			ms.userList.addStatusList(tweets);
			ms.saveTweets();
			return ms.userList;
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public StorageList<Status> getListById() {
		return null;
	}
	
	// :(
	
	// Home ok, user ok, mentions, trends, search, favorite, DM ok, interaction, list

	/*
	 * Session (remember me button) management
	 */

	public Account sessionTest() {
		FileInputStream fis;
		try {
			fis = new FileInputStream("cache/session.tw");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Account sessionUser = (Account) ois.readObject();
			ois.close();
			fis.close();

			return sessionUser;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void createSession(Account c) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("cache/session.tw");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			// create new session
			oos.writeObject(c);
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

	public void destroySession() {
		File file = new File("cache/session.tw");
		file.delete();
	}

	/*
	 * Image method
	 */

	public URL getUserBigAvatar() throws MalformedURLException {
		User user;
		URL url;
		ProfileImage pi;
		String url_n;
		
		try {
			user = twitter.showUser(twitter.getId());
			pi = twitter.getProfileImage(user.getScreenName(), ProfileImage.ORIGINAL);
			url_n = pi.getURL();
			url_n = new StringBuffer(url_n).insert((url_n.length() - 4), "_reasonably_small").toString();
			url = new URL(url_n);
			return url;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	
	

	/*
	 * Reply Tree
	 */

	public Node getReply(Status status) {

		Node racine = new Node(status);
		long statusId = status.getId();
		RelatedResults related;
		try {
			related = twitter.getRelatedResults(statusId);
			ResponseList<Status> tweets = null;
			tweets = related.getTweetsWithConversation();
			for (Status stat : tweets) {
				racine.addSon(stat);
			}
			return racine;
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public Node getReplyTree(Status status, int depth) {
		Node racine = getReply(status);
		while (depth > 0) {
			for (int i = 0; i < racine.getSons().size(); i++) {
				getReplyTree(racine.getSons().get(i).getValue(), depth - 1);
			}
		}
		return racine;
	}
	


	/*
	 * Get Entities (URL, Hashtag, Media)
	 */

	public URLEntity[] getURLEntities(Status status) {
		return status.getURLEntities();
	}

	public HashtagEntity[] getHashtagEntities(Status status) {
		return status.getHashtagEntities();
	}

	public MediaEntity[] getMediaEntities(Status status) {
		return status.getMediaEntities();
	}
	
}
	