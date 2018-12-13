package yogi.report.server;

import java.io.Serializable;

public class QueryFile  implements Serializable{

	private static final long serialVersionUID = 5946215226982355578L;
	private String queryName;
	private String timeStamp;
	public QueryFile(String queryName, String timeStamp) {
		super();
		this.queryName = queryName;
		this.timeStamp = timeStamp;
	}
	public String getQueryName() {
		return queryName;
	}
	public String getTimeStamp() {
		return timeStamp;
	}

}
