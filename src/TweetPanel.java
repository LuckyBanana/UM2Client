import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLEditorKit;

import twitter4j.DirectMessage;
import twitter4j.EntitySupport;
import twitter4j.Status;
import twitter4j.Tweet;
import twitter4j.TwitterException;
import twitter4j.User;

public class TweetPanel extends Conteneur {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8272586199361038077L;
	
	private File answer_h_f = new File("Graphisme/Interface_principale/tweet_button/answer_h.png");
	private File answer_n_f = new File("Graphisme/Interface_principale/tweet_button/answer_n.png");
	private File delete_h_f = new File("Graphisme/Interface_principale/tweet_button/delete_h.png");
	private File delete_n_f = new File("Graphisme/Interface_principale/tweet_button/delete_n.png");
	private File fav_h_f = new File("Graphisme/Interface_principale/tweet_button/fav_h.png");
	private File fav_n_f = new File("Graphisme/Interface_principale/tweet_button/fav_n.png");
	private File fav_d_f = new File("Graphisme/Interface_principale/tweet_button/fav_d.png");
	private File retweet_h_f = new File("Graphisme/Interface_principale/tweet_button/retweet_h.png");
	private File retweet_n_f = new File("Graphisme/Interface_principale/tweet_button/retweet_n.png");
	private File retweet_d_f = new File("Graphisme/Interface_principale/tweet_button/retweet_d.png");
	private File save_h_f = new File("Graphisme/Interface_principale/tweet_button/save_h.png");
	private File save_n_f = new File("Graphisme/Interface_principale/tweet_button/save_n.png");
	private File separation_f = new File("Graphisme/Interface_principale/separation.png");
	private File cadre_avatar_f = new File("Graphisme/Interface_principale/petit_cadre.png");
	private File conversation_n_f = new File("Graphisme/Interface_principale/tweet_button/conversation_n.png");
	private File conversation_h_f = new File("Graphisme/Interface_principale/tweet_button/conversation_h.png");
	
	
	private JLabel username = new JLabel();
	private JLabel date = new JLabel();
	private JEditorPane tweet = new JEditorPane();
	private JLabel nb_fav = new JLabel();
	private JLabel nb_answer = new JLabel();
	private JLabel nb_retweet = new JLabel();
	private JLabel screename = new JLabel();
	
	private Bouton answer_b = new Bouton(12, 81, 11, 12, "Answer");
	private Bouton delete_b = new Bouton(260, 95, 9, 11, "Delete");
	private Bouton fav_b = new Bouton(11, 64, 14, 14, "Fave it");
	private Bouton retweet_b = new Bouton(12, 99, 14, 8, "Retweet it");
	private	Bouton save_b = new Bouton(293, 93, 14, 14, "Save it");
	private Bouton pseudo_b = new Bouton(65, 15, 150, 20, null);
	
	private Enum enuum;
	private Enum type;
		
	protected Status status;
	protected DirectMessage directm;
	protected Tweet twit;
	protected Node noeu;
	private Client twitter = new Client();
	private User user;
	private int i=0;
	
	public TweetPanel(final Status status_s, final Client twitter_t) {

		this.setLayout(null);
		this.setBorder(null);
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(310, 116));
		this.type = enuum.STATUS;
		
		this.status = status_s;
		this.twitter = twitter_t;
		try {
			this.user = twitter.currentUser.getUser();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		chargementImage();
		chargementBouton();
		affichageInfo();
		
	
		
		/*** JLABEL ****/
		
		
		this.repaint();
		validate();

	}
	
	public TweetPanel(final DirectMessage dm, final Client twitter_t) {

		this.setLayout(null);
		this.setBorder(null);
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(310, 116));
		this.type = enuum.DIRECT_MESSAGES;
		
		this.directm = dm;
		this.twitter = twitter_t;
		try {
			this.user = twitter.currentUser.getUser();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		chargementImage();
		chargementBouton();
		affichageInfo();
		
	
		
		/*** JLABEL ****/
		
		
		this.repaint();
		validate();

	}
	
	public TweetPanel(final Tweet t, final Client twitter_t) {

		this.setLayout(null);
		this.setBorder(null);
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(310, 116));
		this.type = enuum.TWEET;
		
		this.twit = t;
		this.twitter = twitter_t;
		try {
			this.user = twitter.currentUser.getUser();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		chargementImage();
		chargementBouton();
		affichageInfo();
		
	
		
		/*** JLABEL ****/
		
		
		this.repaint();
		validate();

	}
	
	public void chargementImage()
	{
		try 
		{
			if(type == enuum.STATUS)
			{
				/* 0 */ ComposantGraphique avatar = new ComposantGraphique(status.getUser().getProfileImageURL(), 13,13);
				this.ajouterComposant(avatar);
				
				if(status.isFavorited())
				{
					/* 1 */ ComposantGraphique fav = new ComposantGraphique(fav_h_f, 11, 64);
					this.ajouterComposant(fav);
				}
				else
				{
					/* 1 */ ComposantGraphique fav = new ComposantGraphique(fav_n_f, 11, 64);
					this.ajouterComposant(fav);
				}
				
				
			}
			else if(type == enuum.DIRECT_MESSAGES)
			{
				/* 0 */ ComposantGraphique avatar = new ComposantGraphique(directm.getSender().getProfileImageURL(), 13, 13);
				this.ajouterComposant(avatar);
				/* 1 */ ComposantGraphique fav = new ComposantGraphique(fav_d_f, 11, 64);
				this.ajouterComposant(fav); 
			}
			else
			{
				URL  url = new URL(twit.getProfileImageUrl());
				/* 0 */ ComposantGraphique avatar = new ComposantGraphique(url, 13, 13);
				/* 1 */ ComposantGraphique fav = new ComposantGraphique(fav_d_f, 11, 64);
				this.ajouterComposant(fav); 
			}
			
			/* 2 */ ComposantGraphique cadre_avatar = new ComposantGraphique(cadre_avatar_f, 12, 11);
			this.ajouterComposant(cadre_avatar);
			
			
			/* 3 */ComposantGraphique answer = new ComposantGraphique(answer_n_f, 12, 82);
			this.ajouterComposant(answer);
			
			if(type == enuum.STATUS && status.isRetweetedByMe())
			{
				/* 4 */ ComposantGraphique retweet = new ComposantGraphique(retweet_h_f, 12, 99);
				this.ajouterComposant(retweet);
			}
			else if(type == enuum.DIRECT_MESSAGES)
			{
				/* 4 */ ComposantGraphique retweet = new ComposantGraphique(retweet_d_f, 12, 99);
				this.ajouterComposant(retweet);
			}
			else
			{
				/* 4 */ ComposantGraphique retweet = new ComposantGraphique(retweet_n_f, 12, 99);
				this.ajouterComposant(retweet);
			}
			
			/* 5 */ ComposantGraphique save = new ComposantGraphique(save_n_f, 293, 93);
			this.ajouterComposant(save);
			
			
			/* 6 */ComposantGraphique separation = new ComposantGraphique(separation_f, 4, 113);
			this.ajouterComposant(separation);
			
			/* 7 */ComposantGraphique conversation = new ComposantGraphique(conversation_n_f, 273, 93);
			this.ajouterComposant(conversation);
			
			if(type == enuum.STATUS && status.getUser().equals(user))
			{
				/* 8 */ ComposantGraphique delete = new ComposantGraphique(delete_n_f, 260, 95);	
				this.ajouterComposant(delete);
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void chargementBouton()
	{
		answer_b.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				modifComposant(3, answer_h_f, 12, 82);
				repaint();
			}

			public void mouseExited(MouseEvent e) {
				modifComposant(3, answer_n_f, 12, 82);
				repaint();
			}
		});
		answer_b.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				reponse();
				
			}
		});
		
		if(type == enuum.STATUS && (status.getUser().equals(user)))
		{
			delete_b.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					modifComposant(8, delete_h_f, 280, 95);
					repaint();
				}

				public void mouseExited(MouseEvent e) {
					modifComposant(8, delete_n_f, 280, 95);
					repaint();
				}
			});
			delete_b.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0) {
					twitter.deleteTweet(status.getId());							
				}
				
			});
		}
		
		
		if(type == enuum.STATUS && status.isFavorited())
		{
			fav_b.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				modifComposant(1, fav_n_f, 11, 64);
				repaint();
			}

			public void mouseExited(MouseEvent e) {
				modifComposant(1, fav_h_f, 11, 64);
				repaint();
			}
			
		});
			fav_b.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent e) {
					twitter.destroyFavorite(status.getId());
				}
				
			});
		}
		else if(type == enuum.STATUS)
		{
			fav_b.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				modifComposant(1, fav_h_f, 11, 64);
				repaint();
			}

			public void mouseExited(MouseEvent e) {
				modifComposant(1, fav_n_f, 11, 64);
				repaint();
			}
		});
			fav_b.addActionListener(new ActionListener(){
				
				public void actionPerformed(ActionEvent e) {
					if(type == enuum.STATUS){
						twitter.createFavorite(status.getId());
					}
				}
				
			});
		}
		
		if(type == enuum.STATUS && status.isRetweetedByMe())
		{
			retweet_b.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				modifComposant(4, retweet_n_f, 12, 99);
				repaint();
			}

			public void mouseExited(MouseEvent e) {
				modifComposant(4, retweet_h_f, 12, 99);
				repaint();
			}
		});
		}
		else if(type == enuum.STATUS)
		{
			retweet_b.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				modifComposant(4, retweet_h_f, 12, 99);
				repaint();
			}

			public void mouseExited(MouseEvent e) {
				modifComposant(4, retweet_n_f, 12, 99);
				repaint();
			}
		});
		}
		retweet_b.addActionListener(new ActionListener(){

			
				public void actionPerformed(ActionEvent e) {
				if(type == enuum.STATUS)
				{
					twitter.retweet(status.getId());
				}
			}
			
		});
		
		save_b.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				modifComposant(5, save_h_f, 293, 93);
				repaint();
			}

			public void mouseExited(MouseEvent e) {
				modifComposant(5, save_n_f, 293, 93);
				repaint();
			}
		});
		save_b.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				twitter.lovedTweets.loveTweet(status);
			}
		});
		
		pseudo_b.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent arg0)
			{
				remove(username);
				add(screename);
				repaint();
			}
			
			public void mouseExited(MouseEvent arg0)
			{
				add(username);
				remove(screename);
				repaint();
			}
		});
		
		this.add(answer_b);
		this.add(fav_b);
		this.add(retweet_b);
		this.add(save_b);
		this.add(pseudo_b);
	}
	
	public void reponse()
	{
			final JFrame pop_up = new JFrame();
            pop_up.setSize(467, 90);
            pop_up.setLocationRelativeTo(null);
            pop_up.setUndecorated(true);
            final JLabel label = new JLabel();
            
            
            final Conteneur content = new Conteneur();
            
            File answer_box_f = new File("Graphisme/Interface_principale/tweet_button/answer_box.png");
            
            try
            {
				ComposantGraphique answer_box = new ComposantGraphique(answer_box_f, 0, 0);
				content.ajouterComposant(answer_box);
				
			} catch (IOException e1) 
			{
				e1.printStackTrace();
			}
        
            content.setLayout(null);                        
            
            final JTextField warning = new JTextField("Your tweet is too long !");
    		warning.setEditable(false);
    		warning.setBounds(100, 45, 150, 20);
    		warning.setBorder(BorderFactory.createEmptyBorder());
    		warning.setFont(new Font("Verdana", 1, 10));
    		warning.setForeground(new Color(6, 122, 127));
    		warning.setBackground(new Color(255, 255, 255));
    		
    		final JTextField tweet_status = new JTextField();
    		tweet_status.setEditable(false);
    		tweet_status.setBounds(100, 45, 150, 20);
    		tweet_status.setBorder(BorderFactory.createEmptyBorder());
    		tweet_status.setFont(new Font("Verdana", 1, 10));
    		tweet_status.setForeground(new Color(6, 122, 127));
    		tweet_status.setBackground(new Color(255, 255, 255));
    		
           final JTextArea post_tweet_jta = new JTextArea(2, 25);
    		post_tweet_jta.setBounds(20, 12, 360, 50);
    		post_tweet_jta.setFont(new Font("Verdana", 1, 12));
    		post_tweet_jta.setForeground(new Color(0, 0, 0));
    		post_tweet_jta.setBorder(BorderFactory.createEmptyBorder());
    		post_tweet_jta.setLineWrap(true);
    		if(type == enuum.STATUS)
    		{
    			post_tweet_jta.setText("@"+status.getUser().getScreenName());
    		}
    		post_tweet_jta.addKeyListener(new KeyAdapter() {
    			public void keyReleased(KeyEvent e)
    			{
    				boolean warning_b = false;
    				label.setText("" + (140 - (post_tweet_jta.getText().length())));
    				if (post_tweet_jta.getText().length() > 140)
    				{
    					warning_b = true;
    					content.add(warning);
    					content.repaint();					
    				}
    				else if (post_tweet_jta.getText().length() <= 140 && warning_b)
    				{
    					content.remove(warning);
    					content.repaint();
    				}

    			}
    		});
    		
           Bouton post_tweet = new Bouton(385, 19, 62, 49, "Tweet this");
    		post_tweet.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent arg0) {
    				if (post_tweet_jta.getText().length() <= 140) {
    					
    					if(type == enuum.STATUS )
    					{
    						tweet_status.setText(twitter.reply(status, post_tweet_jta.getText()));
    						System.out.println(tweet_status.getText());
    					}
    					else
    					{
    						tweet_status.setText(twitter.sendDirectMessage(directm.getSenderScreenName(), post_tweet_jta.getText()));
    						System.out.println(tweet_status.getText());
    					}
    					try {
    						Thread.sleep(5000);
    						post_tweet_jta.setText("");
    						label.setText("" + (140 - (post_tweet_jta.getText().length())));
    						//tweet_status.setText("");
    						pop_up.dispose();
    						
    					} catch (InterruptedException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    					
    				}
    			}
    		});
    		
    		content.add(post_tweet_jta);
    		content.add(post_tweet);
    		pop_up.setContentPane(content);
    		pop_up.setVisible(true);
           
		}

	public void affichageInfo()
	{
		username.setBounds(65, 15, 150, 20);
		if(type == enuum.STATUS) { username.setText(status.getUser().getName()); }
		else if(type == enuum.DIRECT_MESSAGES) { username.setText(directm.getSender().getName()); }
		else { username.setText(twit.getFromUser()); }
		username.setFont(new Font("Verdana", 1, 12));
		username.setForeground(new Color(255, 255, 255));
		username.setBorder(null);
		
		screename.setBounds(65, 15, 150, 20);
		if(type == enuum.STATUS) { username.setText(status.getUser().getScreenName()); }
		else if(type == enuum.DIRECT_MESSAGES) { username.setText(directm.getSender().getScreenName()); }
		else { username.setText(twit.getFromUser()); }
		screename.setFont(new Font("Verdana", 1, 12));
		screename.setForeground(new Color(255, 255, 255));
		screename.setBorder(null);
		
		date.setBounds(240, 15, 100, 20);
		if(type == enuum.STATUS)
		{ String date_s = new String(status.getCreatedAt().toString());
		  date.setText(date_s.substring(8, 10) + " " + date_s.substring(4,8) + date_s.substring(11, 16));}
		
		else if(type == enuum.DIRECT_MESSAGES)
		{	String date_s = new String(directm.getCreatedAt().toString());
			date.setText(date_s.substring(8, 10) + " " + date_s.substring(4,8) + date_s.substring(11, 16));}
		else
		{ String date_s = new String(twit.getCreatedAt().toString()); 
			date.setText(date_s.substring(8, 10) + " " + date_s.substring(4,8) + date_s.substring(11, 16));}
		System.out.println(date.getText());
		date.setFont(new Font("Verdana", 1, 9));
		date.setForeground(new Color(119, 211, 214));
		date.setBorder(null);
		
		HTMLEditorKit hek = new HTMLEditorKit();
		
		tweet.setEditorKit(hek);
		tweet.setBounds(65, 37, 245, 75);
		tweet.setEditable(false);
		
		if(type == enuum.STATUS) { tweet.setText(addLinks(status));}
		else if(type == enuum.DIRECT_MESSAGES) { tweet.setText(addLinks(directm));
		System.out.println(tweet.getText());}
		else { tweet.setText(addLinks(twit)); }
		//tweet.setLineWrap(true);
		tweet.setBorder(null);
		tweet.setOpaque(false);
		tweet.setFont(new Font("Verdana", 1, 10));
		tweet.setForeground(new Color(255, 255, 255));
		System.out.println(tweet.getText());
		tweet.addHyperlinkListener(new HyperlinkListener(){

			@Override
			public void hyperlinkUpdate(HyperlinkEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					URI auto;
					try {
						auto = new URI(arg0.getURL().toString());
						if (Desktop.isDesktopSupported()) {
							Desktop desk = Desktop.getDesktop();
							try {
								desk.browse(auto);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			}
			
		});
		//status.getHashtagEntities();
		
		nb_answer.setBounds(28, 84, 50, 20);
		//nb_answer.setText(status.getre
		nb_answer.setBorder(null);
		nb_answer.setForeground(new Color(119, 221, 214));
		nb_answer.setFont(new Font("Verdana", 1, 3));
		
		nb_retweet.setBounds(28, 92, 50, 20);
		if(type == enuum.STATUS) { nb_retweet.setText(Long.toString(status.getRetweetCount()));}
		else { nb_retweet.setText("0");}
		nb_retweet.setBorder(null);
		nb_retweet.setForeground(new Color(119, 221, 214));
		nb_retweet.setFont(new Font("Verdana", 1, 10));
		
		
		this.add(nb_retweet);
		this.add(tweet);
		this.add(nb_answer);
		this.add(nb_fav);
		this.add(username);
		this.add(date);
	}
	
	public String addLinks(Status status) {
		String text = status.getText();
		String textFinal = "<font size=\"2\"><font face=\"verdana\"><font color=\"WHITE\">"+status.getText();
		int mention = 0;
		int hashtag = 0;
		int urlCount = 0;
		for(int s = 0; s < text.length(); s++) {
			if(text.charAt(s) == '@') {
				try
				{String url = "<b><a href = \"http://twitter.com/"+status.getUserMentionEntities()[mention].getScreenName()+"\" style=\"text-decoration:none; color:#99ebee; \">"+
						"@"+status.getUserMentionEntities()[mention].getScreenName()+"</a></b>";
				textFinal = textFinal.replaceAll("@"+status.getUserMentionEntities()[mention].getScreenName(), url);
				mention++;
				}
				catch(ArrayIndexOutOfBoundsException e)
				{System.out.println("ex1"); }
				catch(NullPointerException e)
				{System.out.println("ex2"); }
			}
			if(text.charAt(s) == '#') {
				try
				{String url = "<b><a href = \"http://twitter.com/search/"+status.getHashtagEntities()[hashtag].getText()+"\" style=\"text-decoration:none; color:#99ebee; \">"+
						"#"+status.getHashtagEntities()[hashtag].getText()+"</a></b>";
				textFinal = textFinal.replaceAll("#"+status.getHashtagEntities()[hashtag].getText(), url);
				hashtag++;}
				catch(ArrayIndexOutOfBoundsException e)
				{System.out.println("ex1"); }
				catch(NullPointerException e)
				{System.out.println("ex2"); }
			}
		}
		if(text.contains("http://")) {
			try{
				System.out.println(status.getURLEntities()[urlCount].getURL());
			String url = "<b><a href = "+status.getURLEntities()[urlCount].getURL()+"\" style=\"text-decoration:none; color:#99ebee; \">"+
					status.getURLEntities()[urlCount].getURL()+"</a></b>";
			textFinal = textFinal.replaceAll(status.getURLEntities()[urlCount].getURL().toString(), url);
			urlCount++;
			}
			catch (ArrayIndexOutOfBoundsException e)
			{System.out.println("ex1"); }
			catch(NullPointerException e)
			{System.out.println("ex2"); }
		}
		
		textFinal = textFinal+"</font>";
		return textFinal;
	}

	public String addLinks(DirectMessage directm) {
		String text = directm.getText();
		String textFinal = "<font size=\"2\"><font face=\"Verdana\"><font color=\"WHITE\">"+directm.getText();
		int mention = 0;
		int hashtag = 0;
		int urlCount = 0;
		/*for(int s = 0; s < text.length(); s++) {
			if(text.charAt(s) == '@') {
				try
				{String url = "<a href = \"http://twitter.com/"+((EntitySupport) directm).getUserMentionEntities()[mention].getScreenName()+"\">"+
						"@"+((EntitySupport) directm).getUserMentionEntities()[mention].getScreenName()+"</a>";
				textFinal = textFinal.replaceAll("@"+((EntitySupport) directm).getUserMentionEntities()[mention].getScreenName(), url);
				mention++;
				}
				catch(ArrayIndexOutOfBoundsException e)
				{ }
			}
			if(text.charAt(s) == '#') {
				try
				{String url = "<a href = \"http://twitter.com/search/"+((EntitySupport) directm).getHashtagEntities()[hashtag].getText()+"\">"+
						"#"+((EntitySupport) directm).getHashtagEntities()[hashtag].getText()+"</a>";
				textFinal = textFinal.replaceAll("#"+((EntitySupport) directm).getHashtagEntities()[hashtag].getText(), url);
				hashtag++;}
				catch(ArrayIndexOutOfBoundsException e)
				{ }
			}
		}
		if(text.contains("http://")) {
			try{
				System.out.println(((EntitySupport) directm).getURLEntities()[urlCount].getURL());
			String url = "<a href = "+((EntitySupport) directm).getURLEntities()[urlCount].getURL()+"\">"+
					((EntitySupport) directm).getURLEntities()[urlCount].getURL()+"</a>";
			textFinal = textFinal.replaceAll(((EntitySupport) directm).getURLEntities()[urlCount].getURL().toString(), url);
			urlCount++;
			}
			catch (ArrayIndexOutOfBoundsException e)
			{
				e.printStackTrace();
				
			}
		}
		*/
		textFinal = textFinal+"</font>";
		return textFinal;
	}
	
	public String addLinks(Tweet status) {
		String text = status.getText();
		String textFinal = "<font size=\"2\"><font face=\"verdana\"><font color=\"WHITE\">"+status.getText();
		int mention = 0;
		int hashtag = 0;
		int urlCount = 0;
		for(int s = 0; s < text.length(); s++) {
			if(text.charAt(s) == '@') {
				try
				{String url = "<b><a href = \"http://twitter.com/"+status.getUserMentionEntities()[mention].getScreenName()+"\" style=\"text-decoration:none; color:#99ebee; \">"+
						"@"+status.getUserMentionEntities()[mention].getScreenName()+"</a></b>";
				textFinal = textFinal.replaceAll("@"+status.getUserMentionEntities()[mention].getScreenName(), url);
				mention++;
				}
				catch(ArrayIndexOutOfBoundsException e)
				{ }
			}
			if(text.charAt(s) == '#') {
				try
				{String url = "<b><a href = \"http://twitter.com/search/"+status.getHashtagEntities()[hashtag].getText()+"\"style=\"text-decoration:none; color:#99ebee;\">"+
						"#"+status.getHashtagEntities()[hashtag].getText()+"</a></b>";
				textFinal = textFinal.replaceAll("#"+status.getHashtagEntities()[hashtag].getText(), url);
				hashtag++;}
				catch(ArrayIndexOutOfBoundsException e)
				{ }
			}
		}
		if(text.contains("http://")) {
			try{
				System.out.println(status.getURLEntities()[urlCount].getURL());
			String url = "<b><a href = "+status.getURLEntities()[urlCount].getURL()+"\"style=\"text-decoration:none; color:#99ebee; \">"+
					status.getURLEntities()[urlCount].getURL()+"</a></b>";
			textFinal = textFinal.replaceAll(status.getURLEntities()[urlCount].getURL().toString(), url);
			urlCount++;
			}
			catch (ArrayIndexOutOfBoundsException e)
			{
				e.printStackTrace();
				
			}
		}
		
		textFinal = textFinal+"</font>";
		return textFinal;
	}
}


	