package examples.server.dbReport.report.command;


import java.util.ArrayList;
import java.util.List;

import yogi.remote.client.app.CommandAdapter;

public class DummyDataSetsCommand extends CommandAdapter<Object> {
	
	private static final long serialVersionUID = 1L;
	
	public static String location;
	public static  String appDir;
	
	public DummyDataSetsCommand(String userId) {
		super(userId);
	}

	@Override
	public Object execute() {
		List<Object> dataSets = new ArrayList<Object>();
		dataSets.add(new DataSet("Dummy"));
		return dataSets;
	}
	
	class DataSet{
		private String subNbr;
		private String value;
		public DataSet(String name) {
			super();
			this.subNbr = name;
			this.value = name;
		}
		public String getSubNbr() {
			return subNbr;
		}
		public String getValue() {
			return value;
		}
		
	}

}
