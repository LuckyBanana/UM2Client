
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TextDemo extends JPanel implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4025589641953494334L;
	protected JTextField textField;
    protected JLabel label;
   //protected JTextField intField;
    protected JTextArea textArea;
    protected int compteur;
    private final static String newline = "\n";

    public TextDemo() {
        super(new GridBagLayout());
        //Declaration du champs du tweet
        textField = new JTextField("Entrer votre Tweet:");
        textField.addActionListener(this);
        //Declaration du JLabel avec le compteur
        label = new JLabel("Il reste " +(140-textField.getText().length())+ " caracteres");
        
        textField.addKeyListener(new KeyAdapter() {
//        	public void keyTyped(KeyEvent e) {
//        		label.setText(""+(140-(textField.getText().length())));
//        	}
//        	public void keyPressed(KeyEvent e) {
//        		label.setText(""+(140-(textField.getText().length())));
//        	}
        	public void keyReleased(KeyEvent e) {
                label.setText(""+(140-(textField.getText().length())));
            }
        });
        
        //Declaration JTextArea pour l'affichage des merdouilles
        textArea = new JTextArea(140, 20);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(textField, c);
        add(label, c);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scrollPane, c);
    }

    public void actionPerformed(ActionEvent evt) {
        String text = textField.getText();
        textArea.append(text + newline);
        textField.selectAll();
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TextDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add contents to the window.
        frame.add(new TextDemo());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
   public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
     /*   javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });*/
       JFrame frame = new JFrame("TextDemo");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       //Add contents to the window.
       frame.add(new TextDemo());

       //Display the window.
       frame.pack();
       frame.setVisible(true);
    }
}