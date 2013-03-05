import java.io.Serializable;
import java.util.Vector;

public class AccountList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -98180181144740888L;
	private Vector<Account> list;

	public AccountList() {
		list = new Vector<Account>();
	}

	public Vector<Account> getList() {
		return list;
	}

	public void setList(Vector<Account> liste) {
		this.list = liste;
	}

	public void addAccount(Account c) {
		this.getList().add(c);
	}

	public void removeAccount(Account c) {
		this.getList().remove(c);

	}

}
