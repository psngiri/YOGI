package examples.server.rdl.io;

import yogi.report.server.tuple.io.TupleRowMultiThreadReader;



public class RdlReportReader extends TupleRowMultiThreadReader {
	public static String DataLocation = ".";
	public RdlReportReader() {
		super(31);
	}

	@Override
	public void setDataSet(String dataSet) {
		super.setFileName(DataLocation+dataSet+"/legs.gz");
	}
	
}
