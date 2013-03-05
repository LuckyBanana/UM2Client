import javax.swing.BorderFactory;
import javax.swing.JButton;


public class Bouton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7550170455282208731L;

	public Bouton(int x, int y, int w, int h, String tooltip)
	{
		super();
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setBounds(x, y, w, h);
		this.setToolTipText(tooltip);
		
	}
}
