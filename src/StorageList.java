import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Tweet;

public class StorageList<T> extends ArrayList<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -400273237112685621L;

	public void addStatusList(ResponseList<T> rl) {
		for (int i = 0; i < rl.size(); i++) {
			if (this.size() < 1000) {
				if (!this.contains(rl.get(i)))
					add(rl.get(i));
			} else {
				if (!this.contains(rl.get(i))) {
					this.remove(999);
					add(rl.get(i));
				}
			}
		}

	}
	
	public void addSearchList(List<T> list) {
		for (int i = 0; i < list.size(); i++) {
			if (this.size() < 1000) {
				if (!this.contains(list.get(i)))
					add(list.get(i));
			} else {
				if (!this.contains(list.get(i))) {
					this.remove(999);
					add(list.get(i));
				}
			}
		}

	}


	public void addStatus(T t) {
		if (this.size() < 1000) {
			if (!this.contains(t))
				add(t);
		} else {
			if (!this.contains(t)) {
				this.remove(999);
				add(t);
			}
		}

	}
	
	@SuppressWarnings("unchecked")
	public void sortTweets() {
		java.util.Collections.sort((ArrayList<Status>) this, new Comparator<Status>() {
			public int compare(Status s1, Status s2) {
				return s1.getCreatedAt().compareTo(s2.getCreatedAt());
			}
		});
	}

}
