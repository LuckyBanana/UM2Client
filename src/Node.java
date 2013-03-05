import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

import twitter4j.Status;

public class Node implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6346446236446971815L;
	private Status value;
	private Node father;
	private ArrayList<Node> sons;
	private boolean hidden;

	public Node(Status v) {
		value = v;
		father = null;
		sons = new ArrayList<Node>();
		hidden = false;
	}

	public Node(Status v, Node p) {
		value = v;
		father = p;
		sons = new ArrayList<Node>();
		hidden = false;
	}

	public void addSon(Status s) {
		Node n = new Node(s, this);
		this.sons.add(n);
	}

	public Status getValue() {
		return value;
	}

	public void setValue(Status val) {
		this.value = val;
	}

	public Node getFather() {
		return father;
	}

	public void setFather(Node pere) {
		this.father = pere;
	}

	public ArrayList<Node> getSons() {
		return sons;
	}

	public void setSons(ArrayList<Node> fils) {
		this.sons = fils;
	}
	
	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	
	public int nbSons() {
		return this.getSons().size();
	}

	/*
	 * Put in a string all the tweets from a given node
	 */

	public String getText() {
		String s = "Arbre de tweets :\n" + getValue().getUser().getName() + ":"
				+ getValue().getText();
		int i = 0;
		int j = 0;
		int k = 0;
		ArrayList<Node> suivants = new ArrayList<Node>();
		System.out.println(sons.size());
		while (i < sons.size()) {
			j = 0;
			while (j < sons.get(i).getSons().size()) {
				suivants.add(sons.get(i).getSons().get(j));
				j++;
			}
			s += "\n" + sons.get(i).getValue().getUser().getName() + ":"
					+ sons.get(i).getValue().getText();
			i++;

		}
		System.out.println(suivants.size());
		while (k < suivants.size()) {

			s += "\n" + suivants.get(k).getValue().getUser().getName() + ":"
					+ suivants.get(k).getValue().getText();
			k++;
		}
		return s;

	}

	/*
	 * Breadth-first search
	 */

	public void saveTree() {
		int i;
		LinkedList<Node> fifo = new LinkedList<Node>();
		fifo.add(this);
		while (!fifo.isEmpty()) {
			i = 0;
			Node x = fifo.remove();
			x.save();
			while (i < x.getSons().size()) {
				Node z = x.getSons().get(i);
				fifo.add(z);
				i++;
			}
		}
	}

	/*
	 * Save & Load Tree Call this methods on root only.
	 */

	public void save() {
		try {
			FileOutputStream fos = new FileOutputStream("cache/trees/"+value.getId()+"tree.tw");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(value);
			oos.writeObject(father);
			oos.writeObject(sons);
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

	@SuppressWarnings("unchecked")
	public void load() {
		try {
			FileInputStream fis = new FileInputStream("cache/trees/"+value.getId()+"tree.tw");
			ObjectInputStream ois = new ObjectInputStream(fis);
			value = (Status) ois.readObject();
			father = (Node) ois.readObject();
			sons = (ArrayList<Node>) ois.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("L'arbre du tweet selectionné n'existe pas.");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Linking Reply Trees
	 * Return the common node if exists
	 * Return null if no link
	 */
	
	public Node linkReplyTrees(Node root1) {
		int i;
		int j;
		LinkedList<Node> fifo1 = new LinkedList<Node>();
		fifo1.add(root1);
		while(!fifo1.isEmpty()) {
			j = 0;
			LinkedList<Node> fifo = new LinkedList<Node>();
			fifo.add(this);
			Node y = fifo1.remove();
			while (!fifo.isEmpty()) {
				i = 0;
				Node x = fifo.remove();
				if(x.equals(y)){
					return x;
				}
				while (i < x.getSons().size()) {
					Node z = x.getSons().get(i);
					fifo.add(z);
					i++;
				}
			}
			while (j < y.getSons().size()) {
				Node t = y.getSons().get(j);
				fifo.add(t);
				j++;
			}
		}
		return null;
	}
	
	/*
	 * Hide a tweet in the tree
	 */
	
	public void hide() {
		setHidden(true);
	}
	
	public void unhide() {
		setHidden(false);
	}


}
