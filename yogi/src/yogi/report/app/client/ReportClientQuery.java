package yogi.report.app.client;

public class ReportClientQuery {
	
	public static String UserId = "AA627674";
	public static String Partition = "D";
	public static String Query = "Query1";
	private String name;
	private String partitionCode;
	private String[] filterValues;
	private String[] dataSets;
	private String queryUserId;
	
	public ReportClientQuery(String[] filterValues) {
		this(Query, Partition,filterValues ,new String[0], UserId);
		
	}

	public ReportClientQuery(String name, String partitionCode, String[] filterValues,
			String[] dataSets, String queryUserId) {
		super();
		this.name = name;
		this.partitionCode = partitionCode;
		this.filterValues = filterValues;
		this.dataSets = dataSets;
		this.queryUserId = queryUserId;
	}
	
	protected String getName() {
		return name;
	}
	protected String getPartitionCode() {
		return partitionCode;
	}
	protected String[] getFilterValues() {
		return filterValues;
	}
	protected String[] getDataSets() {
		return dataSets;
	}
	protected String getQueryUserId() {
		return queryUserId;
	}
}
