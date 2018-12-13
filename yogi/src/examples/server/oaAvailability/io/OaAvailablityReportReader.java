package examples.server.oaAvailability.io;

import yogi.report.server.tuple.io.TupleRowLineMultiThreadReader;



public class OaAvailablityReportReader extends TupleRowLineMultiThreadReader {
	public static String AppDir = "/";
	public static boolean RUN = true;
	public static int numOfThreads = 2;
	public static String DataLocation = ".";
	
	public OaAvailablityReportReader() {
		super(10000);
	}
	
	@Override
	public void setDataSet(String dataSet) {
		super.setFileName(DataLocation+dataSet);
	}

	@Override
	protected int getNumberOfThreads() {
		return numOfThreads;
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

}
