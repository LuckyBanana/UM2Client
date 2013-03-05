import java.awt.Graphics;
import java.awt.Image;
import java.awt.color.CMMException;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ComposantGraphique extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Image img;
	protected int pos_x;
	protected int pos_y;
	protected int width;
	protected int height;
	
	ComposantGraphique(File fichier,int x, int y) throws IOException
	{
		this.img = ImageIO.read(fichier);
		this.pos_x = x;
		this.pos_y = y; 
		this.width = this.img.getWidth(this);
        this.height = this.img.getHeight(this);
	}
	
	ComposantGraphique(URL url, int x, int y) throws IOException
	{
		try {
			this.img = ImageIO.read(url);
			this.pos_x = x;
			this.pos_y = y;
			this.width = this.img.getWidth(this);
			this.height = this.img.getHeight(this);
		}
		catch (CMMException e){
			
		}
		
	}

	public int getWidth()
	{ return this.width; }
	
	public int getHeight()
	{
		return this.height;
	}
	
	public int getPosX()
	{
		return this.pos_x;
	}
	
	public int getPosY()
	{
		return this.pos_y;
	}
	
	public void setImage(File fichier, int x, int y)
	{
		try {
			this.img = ImageIO.read(fichier);
			this.pos_x = x;
			this.pos_y = y;
			this.width = this.img.getWidth(this);
			this.height = this.img.getHeight(this);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void paintComponent(Graphics g)
	{		
		g.drawImage(this.img, this.pos_x, this.pos_y, this.width, this.height, this);
	}
	
}