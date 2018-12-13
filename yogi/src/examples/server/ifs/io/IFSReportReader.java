package examples.server.ifs.io;

import java.util.ArrayList;

import yogi.report.server.tuple.io.TupleRowMultiThreadReader;



public class IFSReportReader extends TupleRowMultiThreadReader {
	public static String AppDir = "/";
	public static boolean RUN = true;
	public static int numOfThreads = 8;
	public static String DataLocation = ".";
	private String fName;
	private ArrayList<String> fNames = new ArrayList<String>();
	
	public IFSReportReader(int recordLength, String fName) {
		super(recordLength);
		this.fName = fName;
	}
	
	@Override
	public void setDataSet(String dataSet) {
		super.setFileName(DataLocation+this.getUserId().substring(2)+AppDir+dataSet+fName);
		for(int i = 0; i < fNames.size(); i ++){
			setFileName(i, DataLocation+this.getUserId().substring(2)+AppDir+dataSet+fNames.get(i));
		}
	}

	public void setRecordLengthFileName(int index, int recordLength, String fName) {
		if(fNames.size() == index) this.fNames.add(fName);
		else if(fNames.size() > index) this.fNames.set(index, fName);
		else throw new RuntimeException("use successive indexes while setting fNames");
		setRecordLength(index, recordLength);
	}

	public String getFileName(int index) {
		return fNames.get(index);
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
