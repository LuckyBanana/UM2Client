import java.io.File;
import java.io.Serializable;

import twitter4j.User;
import twitter4j.auth.AccessToken;

public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1579254612404959509L;
	protected String userName;
	protected String password;
	protected AccessToken userAccessToken;
	protected User user;

	public Account(String u, AccessToken at) {
		userName = u;
		setUserAccessToken(at);
		createUserDIR();
		createTreesDIR();
		createUserTweetsDIR();
		createTrendsDIR();	
		//createSearchDIR();
	}

	public Account(String u, String p) {
		userName = u;
		password = p;
		createUserDIR();
		createTreesDIR();
		createUserTweetsDIR();
		createTrendsDIR();	
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public AccessToken getUserAccessToken() {
		return userAccessToken;
	}

	public void setUserAccessToken(AccessToken userAccessToken) {
		this.userAccessToken = userAccessToken;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void createUserDIR() {
		@SuppressWarnings("unused")
		boolean file = new File("cache/"+this.getUserName()).mkdir();
	}
	
	public void createTreesDIR() {
		@SuppressWarnings("unused")
		boolean file = new File("cache/"+this.getUserName()+"/trees/").mkdir();
	}
	
	public void createUserTweetsDIR() {
		@SuppressWarnings("unused")
		boolean file = new File("cache/"+this.getUserName()+"/usertweets/").mkdir();
	}
	
	public void createTrendsDIR() {
		@SuppressWarnings("unused")
		boolean file = new File("cache/"+this.getUserName()+"/trends/").mkdir();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
