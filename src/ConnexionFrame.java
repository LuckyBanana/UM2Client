import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class ConnexionFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Conteneur panneau = new Conteneur();
	private boolean check = false;

    Client twitter = new Client();
	
	private File background_f = new File("Graphisme/Accueil/accueil.png");
	private File check_f = new File("Graphisme/Accueil/checked_box.png");
	private File new_highlight_f = new File("Graphisme/Accueil/new_highlight.png");
	private File new_simple_f = new File("Graphisme/Accueil/new_simple.png");
	private File uncheck_f= new File("Graphisme/Accueil/uncheck_box.png");
	private File icone_f = new File("Graphisme/icone.png");
	private JButton bouton = new JButton("");
	


	
	public ConnexionFrame()
	{
	 /**** Mise en page graphique *****/
		
		
		
		
		this.setSize(488, 345);
		this.setLocationRelativeTo(null);
		this.addMouseListener(new gestionSouris());
		
		
		Image icone;
		try {
			icone = ImageIO.read(icone_f);
			this.setIconImage(icone);
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
		
		this.setUndecorated(true);
		
		panneau.setLayout(null);
		
		Account ses = twitter.sessionTest();
		
		if(ses != null)
		{
			twitter.login(ses.getUserName(), 1);
			@SuppressWarnings("unused")
			MainFrame main = new MainFrame(twitter);
			dispose();
		}
		else
		{
			
			final JTextField user = new JTextField();
			user.setBounds(200, 161, 200, 20);
			user.setFont(new Font("Verdana", 1, 12));
			user.setHorizontalAlignment(JTextField.CENTER);
			user.setBorder(BorderFactory.createEmptyBorder());
			
			final JPasswordField password = new JPasswordField();
			password.setBounds(200, 192, 200, 20);
			password.setFont(new Font("Verdana", 1, 12));
			password.setHorizontalAlignment(JTextField.CENTER);
			password.setBorder(BorderFactory.createEmptyBorder());
			
			bouton.setOpaque(false);
			bouton.setContentAreaFilled(false);
			bouton.setBorderPainted(false);
			bouton.setBounds(196, 285, 138, 36);
			bouton.addActionListener(new ActionListener()
			{	public void actionPerformed(ActionEvent event)
				{ 
				
						String id = user.getText();
						@SuppressWarnings("deprecation")
						String pw = password.getText();
						int rep = twitter.testLogin(id, pw);
						int rm;
						if(check){ rm = 1; } else { rm = 0; }
						
	
						// Create Account ? - If storeCredential.tw doesn't exist, create it.
	                    // Ask for userName & Password
	                    // Link with pin
	                    // Store new account in storeCredentials.tw
	
	                    // Connection ?
	                    // UserName in db & matches password - Tokens akready in db : OK
	                    // UserName in db & wrong password : Try Again
	                    // UserName not in db : Please register
	
	                    //  0 : if username matches password
	                    //  1 : if username doesn't matches password
	                    //  2 : if username isn't in db
	                    // -1 : if storeCredentials.tw doesn't exist
	                    // -2 : if IO error
						 
						 if(rep == 0)
						 {
	                         twitter.login(id, rm);
	                         @SuppressWarnings("unused")
							MainFrame main = new MainFrame(twitter);
	                         dispose();
						 }
						 
						 else if(rep == 1)
						 {
	                         final JFrame pop_up = new JFrame();
	                         pop_up.setSize(218, 105);
	                         pop_up.setLocationRelativeTo(null);
	                         pop_up.setUndecorated(true);
	                         
	                         Conteneur content = new Conteneur();
	                         
	                         File wrong_password = new File("Graphisme/Accueil/wrong_password.png");
	                         
	                         try
	                         {
								ComposantGraphique wp = new ComposantGraphique(wrong_password, 0, 0);
								content.ajouterComposant(wp);
								
							} catch (IOException e) 
							{
								e.printStackTrace();
							}
	                     
	                         content.setLayout(null);                        
	                         
	                         JButton ok = new JButton("");
	                         ok.setOpaque(false);
	                 		 ok.setContentAreaFilled(false);
	                 		 ok.setBorderPainted(false);
	                 		 ok.setBounds(58, 53, 93, 38);
	                 		 ok.addActionListener(new ActionListener()
	                 		 {
	                 			 public void actionPerformed(ActionEvent event)
	                 			 {
	                 				 pop_up.dispose();
	                 			 }
	                 		 });
	                 		 
	                 		content.add(ok);
	                 		pop_up.setContentPane(content);
	                 		pop_up.setVisible(true);
	                        
						 }
						 
						 else if(rep == 2)
						 {
	                         @SuppressWarnings("unused")
							InscriptionFrame inscription = new InscriptionFrame();
	                        dispose();
						 }
						 
						 {
	                         // oops something went wrong - try again ?
						 }
					}
					
					
				});
			
			panneau.add(bouton);
			panneau.add(user);
			panneau.add(password);
			
			try {
				
				ComposantGraphique background = new ComposantGraphique(background_f, 0, 0);		
				ComposantGraphique new_simple = new ComposantGraphique(new_simple_f, 52, 253);
				ComposantGraphique uncheck = new ComposantGraphique(uncheck_f, 399, 222);
			
	
				this.addMouseMotionListener(new MouseAdapter() {
	
					@Override
					public void mouseMoved(MouseEvent arg0)
					{			
						
						if((arg0.getX() >= 45) && (arg0.getX() <= 150) && (arg0.getY() >= 240) && (arg0.getY() <= 285))
							{
								panneau.modifComposant(1, new_highlight_f, 5, 226);
								panneau.repaint();
							}
												
						
						
						else
							{
								panneau.modifComposant(1, new_simple_f, 52, 253);
								panneau.repaint();
							}
					
					}});
				
				panneau.ajouterComposant(background);
				panneau.ajouterComposant(new_simple);
				panneau.ajouterComposant(uncheck);				
				
				panneau.repaint();
	
				
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
					
			this.setContentPane(panneau);
			this.setVisible(true);
			
		}
	}
	
	public class gestionSouris extends MouseAdapter
	{
		@Override
		public void mouseReleased(MouseEvent arg0)
		{
			int x = arg0.getX();		
			int y = arg0.getY();
			
			if((x >= 460) && (x <= 470) && (y >= 7) && (y <= 19))//close bouton
			{
				System.exit(0);
			}
			
			if((x >= 399) && (x <= 411) && (y >= 17) && (y <= 20))// reduce bouton
			{
				setExtendedState(JFrame.ICONIFIED);
			}
			
			if((x >= 399) && (x <= 413) && (y >= 222) && (y <= 237) && (!check)) //Check button
			{
				check = true;
				panneau.modifComposant(2, check_f, 396, 220);
				panneau.repaint();
			}
			
			else if((x >= 396) && (x <= 415) && (y >= 220) && (y <= 239) && (check))
			{
				check = false;
				panneau.modifComposant(2, uncheck_f, 399, 222);
				panneau.repaint();
			}
			
			if((x >= 45) && (x <= 150) && (y >= 240) && (y <= 285))
			{
				@SuppressWarnings("unused")
				InscriptionFrame inscription = new InscriptionFrame();
				dispose();
			}
			
			if((x >= 196) && (x <= 334) && (y >= 285) && (y <= 320))
			{
				@SuppressWarnings("unused")
				MainFrame main = new MainFrame(twitter);
				dispose();
			}
			
		}
		
	}
	
}
