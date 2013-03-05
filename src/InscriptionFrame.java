import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import twitter4j.auth.RequestToken;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InscriptionFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private Conteneur panneau = new Conteneur();
	private boolean check = false;

    Client twitter = new Client();
	
	private File background_f = new File("Graphisme/Accueil/inscription.png");
	private File check_f = new File("Graphisme/Accueil/checked_box.png");
	private File uncheck_f= new File("Graphisme/Accueil/uncheck_box.png");
	private File icone_f = new File("Graphisme/icone.png");
	private JButton bouton = new JButton("");
	private JButton info_bulle = new JButton("");
	
	public InscriptionFrame()
	{
		final RequestToken requestToken = twitter.requestPin();
	      
		this.setSize(488, 345);
		this.setLocationRelativeTo(null);
		this.addMouseListener(new gestionSouris());
		
		Image icone;
		try {
			icone = ImageIO.read(icone_f);
			this.setIconImage(icone);
		} catch (IOException e1)
		{
			e1.printStackTrace();}
		
		this.setUndecorated(true);
		
		panneau.setLayout(null);
		
		try {
			
			ComposantGraphique background = new ComposantGraphique(background_f, 0, 0);		
			ComposantGraphique uncheck = new ComposantGraphique(uncheck_f, 399, 251);
			
			panneau.ajouterComposant(background);
			panneau.ajouterComposant(uncheck);
			
			panneau.repaint();
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
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
		
		final JTextField pin_jtf = new JTextField();
		pin_jtf.setBounds(200, 223, 200, 20);
		pin_jtf.setFont(new Font("Verdana", 1, 12));
		pin_jtf.setHorizontalAlignment(JTextField.CENTER);
		pin_jtf.setBorder(BorderFactory.createEmptyBorder());
		
		panneau.add(user);
		panneau.add(password);
		panneau.add(pin_jtf);
		
		bouton.setOpaque(false);
		bouton.setContentAreaFilled(false);
		bouton.setBorderPainted(false);
		bouton.setBounds(196, 285, 138, 36);
		bouton.addActionListener(new ActionListener()
		{	public void actionPerformed(ActionEvent event)
			{ 
				@SuppressWarnings("unused")
				Account ses = twitter.sessionTest();
				
					String id = user.getText();
					@SuppressWarnings("deprecation")
					String pw = password.getText();
					String pin = pin_jtf.getText();
					//int rep = twitter.testLogin(id, pw);
					int rm;
					if(check){ rm = 1; } else { rm = 0; }
					 					 
				try
				{
					Account newUser = twitter.createAccount(id, pw);
					twitter.loginWithPin(newUser, pin, requestToken, rm);
					dispose();
				}
				catch(UserAlreadyRegisteredException e)
				{
					File userAlready = new File ("Graphisme/Inscription/user_already_registred.png");
					final JFrame pop_up = new JFrame();
                    pop_up.setSize(218, 105);
                    pop_up.setLocationRelativeTo(null);
                    pop_up.setUndecorated(true);
                    
                    Conteneur content = new Conteneur();
                    try
                    {
						ComposantGraphique wp = new ComposantGraphique(userAlready, 0, 0);
						content.ajouterComposant(wp);
						
					} catch (IOException e1) 
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
					
				
			}}});
		

		info_bulle.setOpaque(false);
		info_bulle.setContentAreaFilled(false);
		info_bulle.setBorderPainted(false);
		info_bulle.setBounds(130, 220, 25, 40);
		info_bulle.setToolTipText("Enter the pin provided by Twitter once you've authorized our application.");
		
		panneau.add(bouton);
		panneau.add(info_bulle);
		panneau.repaint();
	
		
		
		this.setContentPane(panneau);
		this.setVisible(true);
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
			
			if((x >= 399) && (x <= 413) && (y >= 251) && (y <= 266) && (!check)) //Check button
			{
				check = true;
				panneau.modifComposant(1, check_f, 396, 249);
				panneau.repaint();
			}
			
			else if((x >= 396) && (x <= 415) && (y >= 249) && (y <= 264) && (check))
			{
				check = false;
				panneau.modifComposant(1, uncheck_f, 399, 251);
				panneau.repaint();
			}
						
		}
		
	}
	
}
