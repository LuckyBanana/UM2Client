import java.util.Vector;
import java.awt.Graphics;
import java.io.File;
import javax.swing.*;



public class Conteneur extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<ComposantGraphique> composant = new Vector<ComposantGraphique>();
	
	public Conteneur()
	{
		//this.setBackground(Color.BLACK);
		
	}
	
	public void ajouterComposant(ComposantGraphique image)
	{
		this.composant.add(image);
		this.repaint();
	}
	
	public void retirerComposant(int i)
	{
		this.composant.remove(i);
		this.repaint();
	}
	
	public void modifComposant(int i, File fichier, int x, int y)
	{
		this.composant.get(i).setImage(fichier, x, y);
	}
	
	
	public void paintComponent(Graphics g)
	{
		for(int i=0; i<this.composant.size(); i++)
		{
			this.composant.get(i).paintComponent(g);
		}
	}


}
