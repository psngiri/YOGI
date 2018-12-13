package yogi.report.server;

import java.io.Serializable;

public class UserQuery  implements Serializable{

	private static final long serialVersionUID = 6003861206821066755L;
	private String userName;
	private String queryName;
	private Query query;
	public UserQuery(String userName, String queryName, Query query) {
		super();
		this.userName = userName;
		this.queryName = queryName;
		this.query = query;
	}
	public String getUserName() {
		return userName;
	}
	public String getQueryName() {
		return queryName;
	}
	public Query getQuery() {
		return query;
	}
	@Override
	public String toString() {
		return "UserQuery [userName=" + userName + ", queryName=" + queryName
				+ ", query=" + query + "]";
	}

}
