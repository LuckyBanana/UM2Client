import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import twitter4j.DirectMessage;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.User;


public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private Conteneur panneau = new Conteneur();
	private Client twitter = new Client();
	
	private Enum affichage_tweet;

	private File background_f = new File("Graphisme/Interface_principale/background.png");
	private File icone_f = new File("Graphisme/icone.png");
	private File button_f = new File("Graphisme/Interface_principale/button.png");
	private File cadre_gd_avatar_f = new File("Graphisme/Interface_principale/cadre_grand_avatar.png");
	private File logo_f = new File("Graphisme/Interface_principale/logo.png");
	private File new_tweet_f = new File("Graphisme/Interface_principale/new_tweet.png");
	private File research_n_f = new File("Graphisme/Interface_principale/research_n.png");
	private File research_h_f = new File("Graphisme/Interface_principale/research_h.png");
	private File search_bar_f = new File("Graphisme/Interface_principale/search_bar.png");
	private File tweet_box_f = new File("Graphisme/Interface_principale/tweet_box.png");

	private File dm_h_f = new File("Graphisme/Interface_principale/menu/dm_h.png");
	private File dm_n_f = new File("Graphisme/Interface_principale/menu/dm_n.png");
	private File fave_h_f = new File("Graphisme/Interface_principale/menu/fave_h.png");
	private File fave_n_f = new File("Graphisme/Interface_principale/menu/fave_n.png");
	private File interaction_h_f = new File("Graphisme/Interface_principale/menu/interaction_h.png");
	private File interaction_n_f = new File("Graphisme/Interface_principale/menu/interaction_n.png");
	private File list_h_f = new File("Graphisme/Interface_principale/menu/list_h.png");
	private File list_n_f = new File("Graphisme/Interface_principale/menu/list_n.png");
	private File mention_h_f = new File("Graphisme/Interface_principale/menu/mention_h.png");
	private File mention_n_f = new File("Graphisme/Interface_principale/menu/mention_n.png");
	private File timeline_h_f = new File("Graphisme/Interface_principale/menu/timeline_h.png");
	private File timeline_n_f = new File("Graphisme/Interface_principale/menu/timeline_n.png");
	private File trend_n_f = new File("Graphisme/Interface_principale/menu/trend_n.png");
	private File trend_h_f = new File("Graphisme/Interface_principale/menu/trend_h.png");
	private File tweet_n_f = new File("Graphisme/Interface_principale/menu/tweet_n.png");
	private File tweet_h_f = new File("Graphisme/Interface_principale/menu/tweet_h.png");
	private File conversation_n = new File("Graphisme/Interface_principale/tweet_button/conversation_n.png");
	private File conversation_h = new File("Graphisme/Interface_principale/tweet_button/conversation_h.png");
	
	private Bouton research_b = new Bouton(235, 673, 31, 35, "Launch Search");
	private Bouton dm_b = new Bouton(867, 649, 34, 21, "Direct Message");
	private Bouton fave_b = new Bouton(822, 697, 29, 27, "Favorites Tweet");
	private Bouton interaction_b = new Bouton(924, 644, 32, 29, "Interactions");
	private Bouton list_b = new Bouton(920, 690, 37, 25, "List");
	private Bouton mention_b = new Bouton(825, 645, 26, 28, "Mentions");
	private Bouton timeline_b = new Bouton(779, 645, 27, 27, "Timeline");
	private Bouton trend_b = new Bouton(866, 688, 38, 30, "Trends");
	private Bouton tweet_b = new Bouton(777, 689, 30, 26, "Your tweets");
	private Bouton refresh_b = new Bouton(804, 3, 16, 18, "Refresh");
	private Bouton deco_b = new Bouton(831, 8, 25, 11, "Sign out");
	private Bouton parametre_b = new Bouton(874, 2, 26, 17, "Parameters");
	private Bouton reduce_b = new Bouton(905, 16, 13, 4, "Reduce");
	// private Bouton max_b = new Bouton();
	private Bouton close_b = new Bouton(966, 7, 11, 12, "Quit");
	private Bouton post_tweet = new Bouton(659, 657, 62, 46, "Tweet this");

	private JTextField recherche = new JTextField();
	private JLabel label = new JLabel();
	private Vector<TweetPanel> tweetPanel = new Vector<TweetPanel>();
	private JScrollPane jscroll = new JScrollPane();
	private JScrollPane jscroll1 = new JScrollPane();
	private JScrollPane jscroll2 = new JScrollPane();
	private Conteneur tweet_panel = new Conteneur();
	private Conteneur tweet_panel1 = new Conteneur();
	private boolean warning_b = false;
	private boolean boite =false;
	private Enum tweet_actuel;
	
	@SuppressWarnings("unused")
	private User user;

	public MainFrame(final Client twitter_t)
	{
		this.twitter = twitter_t;
		this.setSize(994, 749);
		this.setLocationRelativeTo(null);
		// this.addMouseListener(new gestionSouris());

		Image icone;
		try {
			icone = ImageIO.read(icone_f);
			this.setIconImage(icone);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		this.setUndecorated(true);
		
		tweet_panel.setPreferredSize(new Dimension(310, 2380));
		tweet_panel.setLayout(null);
		tweet_panel.setOpaque(false);
		tweet_panel1.setPreferredSize(new Dimension(310, 2380));
		tweet_panel1.setLayout(null);
		tweet_panel1.setOpaque(false);
		
		panneau.setLayout(null);		
		
		recherche.setBounds(27, 683, 180, 19);
		recherche.setBorder(BorderFactory.createEmptyBorder());
		recherche.setFont(new Font("Verdana", 1, 10));
		recherche.setForeground(new Color(6, 122, 127));
		recherche.setBackground(new Color(255, 255, 255));
		panneau.add(recherche);
		
		chargementImage();
		ajoutListenerBouton();
		chargementInfo();
		chargementPostTweet();
		creationJscroll(jscroll, 18, 171);
		creationJscroll(jscroll1, 348, 171);
		chargementTweet(affichage_tweet.TIMELINE, 20, recherche);
		tweet_actuel = affichage_tweet.TIMELINE;
		
			

		panneau.repaint();

		
		this.setContentPane(panneau);
		this.setVisible(true);

	}
	
	
	public void chargementImage()
	{
		try {
			/* 0 */ComposantGraphique background = new ComposantGraphique(background_f, 0, 0);			
			/* 1 */ComposantGraphique button = new ComposantGraphique(button_f,789, 0);
			/* 2 */ComposantGraphique cadre_gd_avatar = new ComposantGraphique(cadre_gd_avatar_f, 297, 21);
			/* 3 */ComposantGraphique logo = new ComposantGraphique(logo_f, 0, 0);
			/* 4 */ComposantGraphique new_tweet = new ComposantGraphique(new_tweet_f, 280, 643);
			/* 5 */ComposantGraphique research = new ComposantGraphique(research_n_f, 235, 673);
			/* 6 */ComposantGraphique search_bar = new ComposantGraphique(search_bar_f, 3, 617);
			/* 7 */ComposantGraphique tweet_box = new ComposantGraphique(tweet_box_f, 18, 171);
			/* 8 */ComposantGraphique dm = new ComposantGraphique(dm_n_f, 867, 649);
			/* 9 */ComposantGraphique fave = new ComposantGraphique(fave_n_f, 822, 687);
			/* 10 */ComposantGraphique interaction = new ComposantGraphique(interaction_n_f, 924, 644);
			/* 11 */ComposantGraphique list = new ComposantGraphique(list_n_f, 920, 690);
			/* 12 */ComposantGraphique mention = new ComposantGraphique(mention_n_f, 825, 645);
			/* 13 */ComposantGraphique timeline = new ComposantGraphique(timeline_n_f, 779, 645);
			/* 14 */ComposantGraphique trend = new ComposantGraphique(trend_n_f, 866, 688);
			/* 15 */ComposantGraphique tweet = new ComposantGraphique(tweet_n_f, 777, 689);
			
			
			panneau.ajouterComposant(background);
			
			URL avatar_gd_url;
			try {
				avatar_gd_url = twitter.getUserBigAvatar();
				/* 1 */ ComposantGraphique avatar_gd;
				try {
					avatar_gd = new ComposantGraphique(avatar_gd_url, 301, 24);
					panneau.ajouterComposant(avatar_gd);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
             
			panneau.ajouterComposant(button);
			panneau.ajouterComposant(cadre_gd_avatar);
			panneau.ajouterComposant(logo);
			panneau.ajouterComposant(new_tweet);
			panneau.ajouterComposant(research);
			panneau.ajouterComposant(search_bar);
			panneau.ajouterComposant(tweet_box);
			panneau.ajouterComposant(dm);
			panneau.ajouterComposant(fave);
			panneau.ajouterComposant(interaction);
			panneau.ajouterComposant(list);
			panneau.ajouterComposant(mention);
			panneau.ajouterComposant(timeline);
			panneau.ajouterComposant(trend);
			panneau.ajouterComposant(tweet);
			
			
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void ajoutListenerBouton()
	{		
		panneau.add(recherche);
		research_b.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				panneau.modifComposant(6, research_h_f, 235, 673);
				panneau.repaint();
			}

			public void mouseExited(MouseEvent e) {
				panneau.modifComposant(6, research_n_f, 235, 673);
				panneau.repaint();
			}
		});
		research_b.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				
				chargementTweet(affichage_tweet.RESEARCH, 20, recherche);
				tweet_actuel = affichage_tweet.RESEARCH;
			} 
		});

		dm_b.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				panneau.modifComposant(9, dm_h_f, 867, 649);
				panneau.repaint();
			}

			public void mouseExited(MouseEvent e) {
				panneau.modifComposant(9, dm_n_f, 867, 649);
				panneau.repaint();
			}
		});
		dm_b.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				chargementTweet(affichage_tweet.DIRECT_MESSAGES, 20, recherche);
				tweet_actuel = affichage_tweet.DIRECT_MESSAGES;
			}
		});

		
		fave_b.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				panneau.modifComposant(10, fave_h_f, 822, 687);
				panneau.repaint();
			}

			public void mouseExited(MouseEvent e) {
				panneau.modifComposant(10, fave_n_f, 822, 687);
				panneau.repaint();
			}
		});
		
		fave_b.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				tweet_actuel = affichage_tweet.FAVE;
				chargementTweet(affichage_tweet.FAVE, 20, recherche);
				//tweet_actuel = affichage_tweet.FAVE;
				
			}
		});
		
		interaction_b.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				panneau.modifComposant(11, interaction_h_f, 924, 644);
				panneau.repaint();
			}

			public void mouseExited(MouseEvent e) {
				panneau.modifComposant(11, interaction_n_f, 924, 644);
				panneau.repaint();
			}
		});

		
		list_b.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				panneau.modifComposant(12, list_h_f, 920, 690);
				panneau.repaint();
			}

			public void mouseExited(MouseEvent e) {
				panneau.modifComposant(12, list_n_f, 920, 690);
				panneau.repaint();
			}
		});
		
		list_b.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				chargementTweet(affichage_tweet.LIST, 1, recherche);
				tweet_actuel = affichage_tweet.LIST;
			}
		});
		
		mention_b.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				panneau.modifComposant(13, mention_h_f, 825, 645);
				panneau.repaint();
			}

			public void mouseExited(MouseEvent e) {
				panneau.modifComposant(13, mention_n_f, 825, 645);
				panneau.repaint();
			}
		});
		mention_b.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				chargementTweet(affichage_tweet.MENTION, 1, recherche);
				tweet_actuel = affichage_tweet.MENTION;
			}
		});

		
		timeline_b.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				panneau.modifComposant(14, timeline_h_f, 779, 645);
				panneau.repaint();
			}

			public void mouseExited(MouseEvent e) {
				panneau.modifComposant(14, timeline_n_f, 779, 645);
				panneau.repaint();
			}
		});
		
		timeline_b.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				chargementTweet(affichage_tweet.TIMELINE, 1, recherche);
				tweet_actuel = affichage_tweet.TIMELINE;
			}
		});
		
		
		trend_b.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				panneau.modifComposant(15, trend_h_f, 866, 688);
				panneau.repaint();
			}

			public void mouseExited(MouseEvent e) {
				panneau.modifComposant(15, trend_n_f, 866, 688);
				panneau.repaint();
			}
		});
		trend_b.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				chargementTweet(affichage_tweet.TREND, 1, recherche);
				tweet_actuel = affichage_tweet.TREND;
			}
		});
		
		
		tweet_b.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				panneau.modifComposant(16, tweet_h_f, 777, 689);
				panneau.repaint();
			}

			public void mouseExited(MouseEvent e) {
				panneau.modifComposant(16, tweet_n_f, 777, 689);
				panneau.repaint();
			}
		});
		
		tweet_b.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				chargementTweet(affichage_tweet.TWEET, 1, recherche);
				tweet_actuel = affichage_tweet.TWEET;
			}
		});
		
		
		refresh_b.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				tweetPanel.removeAllElements();
				chargementTweet(tweet_actuel, 20, recherche);
				System.out.println("refresh");
				System.out.println(tweet_actuel.toString());
			}
		});
		
		deco_b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				twitter.destroySession();
				@SuppressWarnings("unused")
				ConnexionFrame accueil = new ConnexionFrame();
				dispose();
			}
		});

	
		//parametre_b.addActionListener

		reduce_b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setExtendedState(JFrame.ICONIFIED);
			}
		});

		close_b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		
		panneau.add(close_b);
		panneau.add(deco_b);
		panneau.add(dm_b);
		panneau.add(fave_b);
		panneau.add(interaction_b);
		panneau.add(list_b);
		panneau.add(mention_b);
		panneau.add(parametre_b);
		panneau.add(reduce_b);
		panneau.add(refresh_b);
		panneau.add(research_b);
		panneau.add(timeline_b);
		panneau.add(trend_b);
		panneau.add(tweet_b);

	}
	
	public void chargementInfo()
	{
		User user;
		try {
			
			user = twitter.currentUser.getUser();
			JTextField pseudo = new JTextField();
			pseudo.setText(user.getName());
			pseudo.setBounds(457, 22, 200, 25);
			pseudo.setFont(new Font("Verdana", 1, 18));
			pseudo.setForeground(new Color(255, 255, 255));
			pseudo.setEditable(false);
			pseudo.setBorder(BorderFactory.createEmptyBorder());
			pseudo.setOpaque(false);
			
			JTextField identifiant = new JTextField();
			identifiant.setText("@ " +user.getScreenName());
			identifiant.setBounds(457, 50, 100, 17);
			identifiant.setFont(new Font("Verdana", 1, 13));
			identifiant.setForeground(new Color(255, 255, 255));
			identifiant.setEditable(false);
			identifiant.setBorder(BorderFactory.createEmptyBorder());
			identifiant.setOpaque(false);
			
			JTextArea description = new JTextArea();
			description.setText(user.getDescription());
			description.setBounds(457, 73, 466, 50);
			description.setFont(new Font("Verdana", 1, 12));
			description.setForeground(new Color(255, 255, 255));
			description.setEditable(false);
			description.setBorder(BorderFactory.createEmptyBorder());
			description.setOpaque(false);
			description.setLineWrap(true);
			
			JTextField following = new JTextField();
			following.setText(Integer.toString(user.getFriendsCount()) + " following");
			following.setBounds(457, 125, 115, 30);
			following.setFont(new Font("Verdana", 1, 13));
			following.setForeground(new Color(255, 255, 255));
			following.setEditable(false);
			following.setBorder(BorderFactory.createEmptyBorder());
			following.setOpaque(false);
			
			JTextField follower = new JTextField();
			follower.setText(Integer.toString(user.getFollowersCount()) + " followers");
			follower.setBounds(590, 125, 115, 30);
			follower.setFont(new Font("Verdana", 1, 13));
			follower.setForeground(new Color(255, 255, 255));
			follower.setEditable(false);
			follower.setBorder(BorderFactory.createEmptyBorder());
			follower.setOpaque(false);
			
			JTextField tweet_n = new JTextField();
			tweet_n.setText(Integer.toString(user.getStatusesCount()) + " tweets");
			tweet_n.setBounds(720, 125, 115, 30);
			tweet_n.setFont(new Font("Verdana", 1, 13));
			tweet_n.setForeground(new Color(255, 255, 255));
			tweet_n.setEditable(false);
			tweet_n.setBorder(BorderFactory.createEmptyBorder());
			tweet_n.setOpaque(false);
			
			panneau.add(pseudo);
			panneau.add(identifiant);
			panneau.add(description);
			panneau.add(following);
			panneau.add(follower);
			panneau.add(tweet_n);
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void chargementPostTweet()
	{
		label.setBounds(630, 700, 30, 20);
		label.setFont(new Font("Verdana", 1, 10));
		label.setForeground(new Color(6, 122, 127));
				
		final JTextField warning = new JTextField("Your tweet is too long !");
		warning.setEditable(false);
		warning.setBounds(400, 700, 150, 20);
		warning.setBorder(BorderFactory.createEmptyBorder());
		warning.setFont(new Font("Verdana", 1, 10));
		warning.setForeground(new Color(6, 122, 127));
		warning.setBackground(new Color(255, 255, 255));
		
		final JTextField tweet_status = new JTextField();
		tweet_status.setEditable(false);
		tweet_status.setBounds(400, 700, 150, 20);
		tweet_status.setBorder(BorderFactory.createEmptyBorder());
		tweet_status.setFont(new Font("Verdana", 1, 10));
		tweet_status.setForeground(new Color(6, 122, 127));
		tweet_status.setBackground(new Color(255, 255, 255));
		
		final JTextArea post_tweet_jta = new JTextArea(2, 25);
		post_tweet_jta.setBounds(300, 655, 360, 50);
		post_tweet_jta.setFont(new Font("Verdana", 1, 12));
		post_tweet_jta.setBorder(BorderFactory.createEmptyBorder());
		post_tweet_jta.setLineWrap(true);
		post_tweet_jta.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				label.setText("" + (140 - (post_tweet_jta.getText().length())));
				if (post_tweet_jta.getText().length() > 140)
				{
					warning_b = true;
					panneau.add(warning);
					panneau.repaint();					
				}
				else if (post_tweet_jta.getText().length() <= 140 && warning_b)
				{
					panneau.remove(warning);
					panneau.repaint();
				}

			}
		});

		post_tweet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (post_tweet_jta.getText().length() <= 140) {
					
					tweet_status.setText(twitter.sendTweet(post_tweet_jta.getText()));
					
					try {
						Thread.sleep(5000);
						post_tweet_jta.setText("");
						label.setText("" + (140 - (post_tweet_jta.getText().length())));
						//tweet_status.setText("");
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		});

		
		panneau.add(label);
		panneau.add(tweet_status);
		panneau.add(post_tweet_jta);
		panneau.add(post_tweet);
	}
	
	
	public void chargementTweet(Enum tweetACharger, final int nombre, final JTextField recherche)
	{
		tweetPanel.removeAllElements();
		tweet_panel.removeAll();
		tweet_panel.repaint();
		jscroll.setViewportView(tweet_panel);
        ((JComponent) jscroll.getViewport().getView()).setOpaque(false);
		final JLabel see_more = new JLabel("See more");
		see_more.setFont(new Font("Verdana", 1, 12));
		see_more.setForeground(new Color(255, 255, 255));
		//see_more.setOpaque(false);
		see_more.setBorder(BorderFactory.createEmptyBorder());
		
		
		try {
			user = twitter.currentUser.getUser();
		} catch (IllegalStateException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		 		 
		 Paging p = new Paging(1, 20);
		 switch(tweetACharger)
		 {
		 	case TIMELINE:
		 	{
		 		twitter.homeTimelineTweets.statusList = twitter.getHomeTimeline(p);
		 		tweet_actuel = affichage_tweet.TIMELINE;
		 		 int num = twitter.homeTimelineTweets.statusList.size()-1;
		         while(num>twitter.homeTimelineTweets.statusList.size()-21)
		         {
		            try {
		            	try {		       
		                    tweetPanel.add(new TweetPanel(twitter.homeTimelineTweets.statusList.get(num), twitter));
		           			 num--;          
		            	} catch (IllegalStateException e1) {
		            		e1.printStackTrace();
		            	} 
		            } catch (ArrayIndexOutOfBoundsException e) {
		            	e.printStackTrace();
		            } catch (NullPointerException e) {
		                 e.printStackTrace();
		             }
		                 
		         }
		 	}break;
		 	
		 	case RESEARCH:
		 	{
		 		//twitter.homeTimelineTweets.statusList = twitter.search(recherche.getText());
		 	}break;
		 	
		 	case TWEET:
		 	{
		 		twitter.currentUserTweets.statusList = twitter.getUserTimeline(p);
		 		System.out.println(twitter.currentUserTweets.nbTweets());
		 		int num = 0;
		         while(num < twitter.currentUserTweets.nbTweets())
		         {
		            try {
		            	try {
		                    System.out.println("Tweet chargé, création tweetPanel" + num);
		                    tweetPanel.add(new TweetPanel(twitter.currentUserTweets.statusList.get(num), twitter));
		                    num++;          
		            	} catch (IllegalStateException e1) {
		            		e1.printStackTrace();
		            	} 
		            } catch (ArrayIndexOutOfBoundsException e) {
		            	e.printStackTrace();
		            } catch (NullPointerException e) {
		                 e.printStackTrace();
		             }
		                 
		         }
		 	}break;
		 	
		 	case TREND:
		 	{
		 		//twitter.homeTimelineTweets.statusList = twitter.
		 	}break;
		 	
		 	case DIRECT_MESSAGES:
		 	{
		 		 twitter.directMessage.directMessages = twitter.getDirectMessages(p);
		 		 if(twitter.directMessage.directMessages.size()==0)
		 		 {
		 			    System.out.println("No directMessage dude");
		            	JLabel no_reponse = new JLabel("You have no direct messages");
		            	no_reponse.setBounds(10, 15, 150, 20);
		            	no_reponse.setFont(new Font("Verdana", 1, 20));
		            	no_reponse.setForeground(new Color(255, 255, 255));
		            	tweet_panel.add(no_reponse);
		            	tweet_panel.repaint();
		 		 }
		 		 else
		 		 {
		 			 int num = 0;
		 			 while(num<twitter.directMessage.directMessages.size())
			         {
			            try {
			            	try {
			                    tweetPanel.add(new TweetPanel(twitter.directMessage.directMessages.get(num), twitter));
			                    num++;          
			            	} catch (IllegalStateException e1) {
			            		e1.printStackTrace();
			            	} 
			            } catch (ArrayIndexOutOfBoundsException e) {
			            	e.printStackTrace();
			            } catch (NullPointerException e) {
			                 e.printStackTrace();
			             }
			         }  
		         }
		        
		 	}break;
		 	
		 	case FAVE:
		 	{
		 		twitter.favsTweets.statusList = twitter.getFavorites(p);
		 		System.out.println(twitter.favsTweets.nbFavorites());
		 		int num = twitter.favsTweets.nbFavorites()-1;
		         while(num >= 0)
		         {
		            try {
		            	try {
		                    System.out.println("Tweet chargé, création tweetPanel" + num);
		                    tweetPanel.add(new TweetPanel(twitter.favsTweets.statusList.get(num), twitter));
		                    num--;          
		            	} catch (IllegalStateException e1) {
		            		e1.printStackTrace();
		            	} 
		            } catch (ArrayIndexOutOfBoundsException e) {
		            	e.printStackTrace();
		            } catch (NullPointerException e) {
		                 e.printStackTrace();
		             }
		                 
		         }
		         
		 	}break;
		 	
		 	case INTERACTION:
		 	{
		 		//twitter.homeTimelineTweets.statusList = twitter
		 	}break;
		 	
		 	case LIST:
		 	{
		 		//twitter.homeTimelineTweets.statusList = twitter
		 	}break;
		 	
		 	case MENTION:
		 	{
		 		twitter.mentionTweet.statusList = twitter.getMentionsTimeline(p);
		 		System.out.println(twitter.mentionTweet.nbMentions());
		 		int num = twitter.mentionTweet.nbMentions()-1;
		         while(num >= 0)
		         {
		            try {
		            	try {
		                    System.out.println("Tweet chargé, création tweetPanel" + num);
		                    tweetPanel.add(new TweetPanel(twitter.mentionTweet.statusList.get(num), twitter));
		                    num--;          
		            	} catch (IllegalStateException e1) {
		            		e1.printStackTrace();
		            	} 
		            } catch (ArrayIndexOutOfBoundsException e) {
		            	e.printStackTrace();
		            } catch (NullPointerException e) {
		                 e.printStackTrace();
		             }
		                 
		         }
		 	}break;
		 }
         
        
         
         
         int y = 0;
         int b = 93;
         int i = 0;
         
         while(i <20 || i < tweetPanel.size()-1)
         {
        	 final int j = i;
        	 tweetPanel.get(i).setBounds(0, y, 310, 118);
        	 Bouton conversation_b = new Bouton(273, 93, 19, 14, "Conversation");
        	 conversation_b.setBounds(273, b, 19, 14);
        	 conversation_b.addMouseListener(new MouseAdapter()
        	 {
        		 public void mouseEntered(MouseEvent e)
        		 {
        			 tweetPanel.get(j).modifComposant(7, conversation_h, 273, 93);
        		 }
        		 
        		 public void mouseExited(MouseEvent e)
        		 {
        			 tweetPanel.get(j).modifComposant(7, conversation_n, 273, 93);
        		 }
        	 });
        	 conversation_b.addActionListener(new ActionListener()
        	 {
        		public void actionPerformed(ActionEvent arg0)
        		{
        			Node noeud = twitter.getReply(tweetPanel.get(j).status);
        			afficherReponse(noeud);		
        		}
        	 });
        	 tweet_panel.add(conversation_b);
        	 tweet_panel.add(tweetPanel.get(i));
        	 y+= 118;
        	 b+= 118;
        	 i++;
         }
         see_more.setBounds(80, y, 100, 20);
         
         final Bouton see_more_b = new Bouton(80, y, 100, 20, "");
         see_more_b.addActionListener(new ActionListener()
         {
        	 public void actionPerformed(ActionEvent arg0)
        	 {
        		 chargerPlus(tweet_actuel, nombre+20, recherche);
        		 tweet_panel.remove(see_more_b);
        		 tweet_panel.remove(see_more);
        	 }
         });
         see_more_b.addMouseListener(new MouseAdapter(){
 			public void mouseEntered(MouseEvent e)
 			{
 				see_more.setText("<html><u>See more</u></html>");
 			}
 			
 			public void mouseExited(MouseEvent e)
 			{
 				see_more.setText("See more");
 			}
 		});
         tweet_panel.add(see_more_b);
         tweet_panel.add(see_more);
         jscroll.setViewportView(tweet_panel);
         ((JComponent) jscroll.getViewport().getView()).setOpaque(false);

		
		
	}
	
	public void chargerPlus(Enum tweetACharger, final int nombre, final JTextField recherche)
	{
		int y = tweet_panel.getHeight()-20;
		tweet_panel.setPreferredSize(new Dimension(310, (tweet_panel.getHeight()+2380)));

		
		final JLabel see_more = new JLabel("See more");
		see_more.setFont(new Font("Verdana", 1, 12));
		see_more.setForeground(new Color(255, 255, 255));
		see_more.setBorder(BorderFactory.createEmptyBorder());
		
		
		try {
			user = twitter.currentUser.getUser();
		} catch (IllegalStateException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Paging p = new Paging(2, nombre);
		 switch(tweetACharger)
		 {
		 	case TIMELINE:
		 	{
		 		twitter.getHomeTimeline(p);
		 		 int num = twitter.homeTimelineTweets.statusList.size()-(nombre - 19);
		         while(num>twitter.homeTimelineTweets.statusList.size()-(nombre + 1))
		         {
		            try {
		            	try {
		                    tweetPanel.add(new TweetPanel(twitter.homeTimelineTweets.statusList.get(num), twitter));
		                    num--;          
		            	} catch (IllegalStateException e1) {
		            		e1.printStackTrace();
		            	} 
		            } catch (ArrayIndexOutOfBoundsException e) {
		            	e.printStackTrace();
		            } catch (NullPointerException e) {
		                 e.printStackTrace();
		             }
		                 
		         }
		 		
		 	}break;
		 	
		 	case TWEET :
		 	{
		 		twitter.getUserTimeline(p);
		 		System.out.println(twitter.currentUserTweets.nbTweets());
		 		int num = 0;
		         while(num < twitter.currentUserTweets.nbTweets())
		         {
		            try {
		            	try {
		                    System.out.println("Tweet chargé, création tweetPanel" + num);
		                    tweetPanel.add(new TweetPanel(twitter.currentUserTweets.statusList.get(num), twitter));
		                    num++;          
		            	} catch (IllegalStateException e1) {
		            		e1.printStackTrace();
		            	} 
		            } catch (ArrayIndexOutOfBoundsException e) {
		            	e.printStackTrace();
		            } catch (NullPointerException e) {
		                 e.printStackTrace();
		             }
		                 
		         }
		 	}break;
		 	
		 	case DIRECT_MESSAGES:
		 	{
		 		 twitter.getDirectMessages(p);
		 		 int num = twitter.directMessage.directMessages.size()-1;
		         while(num>twitter.directMessage.directMessages.size()-21)
		         {
		            try {
		            	try {
		                    System.out.println("Tweet chargé, création tweetPanel" + num);
		                    tweetPanel.add(new TweetPanel(twitter.directMessage.directMessages.get(num), twitter));
		                    num--;          
		            	} catch (IllegalStateException e1) {
		            		e1.printStackTrace();
		            	} 
		            } catch (ArrayIndexOutOfBoundsException e) {
		            	e.printStackTrace();
		            } catch (NullPointerException e) {
		                 e.printStackTrace();
		             }
		                 
		         }
		        
		 	}break;
		 	
		 	case FAVE:
		 	{
		 		twitter.getFavorites(p);
		 		System.out.println(twitter.favsTweets.nbFavorites());
		 		int num = 0;
		         while(num < twitter.favsTweets.nbFavorites())
		         {
		            try {
		            	try {
		                    System.out.println("Tweet chargé, création tweetPanel" + num);
		                    tweetPanel.add(new TweetPanel(twitter.favsTweets.statusList.get(num), twitter));
		                    num++;          
		            	} catch (IllegalStateException e1) {
		            		e1.printStackTrace();
		            	} 
		            } catch (ArrayIndexOutOfBoundsException e) {
		            	e.printStackTrace();
		            } catch (NullPointerException e) {
		                 e.printStackTrace();
		             }
		                 
		         }
		         
		 	}break;
		 	
		 	case MENTION:
		 	{
		 		twitter.getMentionsTimeline(p);
		 		System.out.println(twitter.mentionTweet.nbMentions());
		 		int num = 0;
		         while(num < twitter.mentionTweet.nbMentions())
		         {
		            try {
		            	try {
		                    System.out.println("Tweet chargé, création tweetPanel" + num);
		                    tweetPanel.add(new TweetPanel(twitter.mentionTweet.statusList.get(num), twitter));
		                    num++;          
		            	} catch (IllegalStateException e1) {
		            		e1.printStackTrace();
		            	} 
		            } catch (ArrayIndexOutOfBoundsException e) {
		            	e.printStackTrace();
		            } catch (NullPointerException e) {
		                 e.printStackTrace();
		             }
		                 
		         }
		 	}break;
		 }
		 
	         
	         int b = 93+y;
	         int i = 20;
	         while(i <nombre || i < tweetPanel.size())
	         {
	        	 final int j = i;
	        	 tweetPanel.get(i).setBounds(0, y, 310, 118);
	        	 Bouton conversation_b = new Bouton(273, b, 19, 14, "Conversation");
	        	 conversation_b.setBounds(273, b, 19, 14);
	        	 conversation_b.addActionListener(new ActionListener()
	        	 {
	        		public void actionPerformed(ActionEvent arg0)
	        		{
	        			Node noeud = twitter.getReply(tweetPanel.get(j).status);
	        			afficherReponse(noeud);
	        		}
	        	 });
	        	 tweet_panel.add(conversation_b);
	        	 tweet_panel.add(tweetPanel.get(i));
	        	 y+= 118;
	        	 b+= 118;
	        	 i++;
	         }
	         see_more.setBounds(80, y, 100, 20);
	         
	         final Bouton see_more_b = new Bouton(80, y, 100, 20, "");
	         see_more_b.addActionListener(new ActionListener()
	         {
	        	 public void actionPerformed(ActionEvent arg0)
	        	 {
	        		 chargerPlus(tweet_actuel, nombre+20, recherche);
	        		 tweet_panel.remove(see_more_b);
	        		 tweet_panel.remove(see_more);
	        	 }
	         });
	         see_more_b.addMouseListener(new MouseAdapter(){
	 			public void mouseEntered(MouseEvent e)
	 			{
	 				see_more.setText("<html><u>See more</u></html>");
	 			}
	 			
	 			public void mouseExited(MouseEvent e)
	 			{
	 				see_more.setText("See more");
	 			}
	 		});
	         
	     tweet_panel.add(see_more_b);
	     tweet_panel.add(see_more);
	     jscroll.setViewportView(tweet_panel);
		 ((JComponent) jscroll.getViewport().getView()).setOpaque(false);
	}
	
	public void creationJscroll(JScrollPane js, int x, int y)
	{
		final MyScrollBar scrollbar = new MyScrollBar();
		js.setVerticalScrollBar(scrollbar);
		js.setBounds(x, y, 328, 462);
		js.setBorder(null);
		js.setOpaque(false);
		js.getViewport().setOpaque(false);
		//((JComponent) jscroll.getViewport().getView()).setOpaque(false);
		js.getVerticalScrollBar().setOpaque(false);
		js.getVerticalScrollBar().setBorder(null);
		panneau.add(js);
		panneau.repaint();
	}
	
	public void afficherReponse(Node node)
	{
		final Vector<TweetPanel> tweetReponse = new Vector<TweetPanel>();
		tweet_panel1.removeAll();
		if(!boite){
		try {
			/* 16 */ComposantGraphique tweet_box2 = new ComposantGraphique(tweet_box_f, 348, 171);
			panneau.ajouterComposant(tweet_box2);
			boite = true;
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}}
		
		
		try {
			user = twitter.currentUser.getUser();
		} catch (IllegalStateException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		 
		// Paging p = new Paging(1, 20);
		 
		 System.out.println(node.nbSons());
		 if(node.nbSons() <= 0)
		 {
			 System.out.println("No reponse dude");
         	JLabel no_reponse = new JLabel("There's no answer to this tweet");
         	no_reponse.setBounds(20, 15, 400, 20);
         	no_reponse.setFont(new Font("Verdana", 1, 12));
         	no_reponse.setForeground(new Color(255, 255, 255));
         	tweet_panel1.add(no_reponse);
         	tweet_panel1.repaint();
		 }
		 else
		 {
			 int num= 0;
	         while(num < node.nbSons())
	         {
	            try {
	            	try {
	                    tweetReponse.add(new TweetPanel(node.getSons().get(num).getValue(), twitter));
	           			 num++;          
	            	} catch (IllegalStateException e1) {
	            		e1.printStackTrace();
	            	} 
	            } catch (ArrayIndexOutOfBoundsException e) {            	
	            	e.printStackTrace();
	            } catch (NullPointerException e) {
	                 e.printStackTrace();
	             }
	         }      
         }
         
         int y = 0;
         int b = 93;
         int i = 0;
         while(i < node.nbSons()-2 || i < tweetReponse.size())
         {
        	 final int j = i;
        	 tweetReponse.get(i).setBounds(0, y, 310, 118);
        	 Bouton conversation_b = new Bouton(273, 93, 19, 14, "Conversation");
        	 conversation_b.setBounds(273, b, 19, 14);
        	 conversation_b.addActionListener(new ActionListener()
        	 {
        		public void actionPerformed(ActionEvent arg0)
        		{
        			Node noeud = twitter.getReply(tweetReponse.get(j).status);
        			afficherReponse(noeud);

        		}
        	 });
        	 tweet_panel1.add(conversation_b);
        	 tweet_panel1.add(tweetReponse.get(i));
        	 y+= 118;
        	 b+= 118;
        	 i++;
         }
        
         jscroll1.setViewportView(tweet_panel1);
         ((JComponent) jscroll.getViewport().getView()).setOpaque(false);
         
		//StorageList<Status> replies = (StorageList<Status>) node.getSons();
	}
	
}
