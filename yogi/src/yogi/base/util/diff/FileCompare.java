package yogi.base.util.diff;

import java.io.LineNumberReader;
import java.util.Comparator;

import yogi.base.Selector;
import yogi.base.io.BaseFileReader;

public class FileCompare {
	private String fromFileName;
	private String toFileName;
	private Comparator<String> comparator;
	private Selector<String> fromRecordSelector;
	private Selector<String> toRecordSelector;
	
	
	public FileCompare(String fromFileName, String toFileName) {
		super();
		this.fromFileName = fromFileName;
		this.toFileName = toFileName;
		comparator = new MyComparator();
	}
	
	public Selector<String> getFromRecordSelector() {
		return fromRecordSelector;
	}

	public void setFromRecordSelector(Selector<String> fromRecordSelector) {
		this.fromRecordSelector = fromRecordSelector;
	}

	public Selector<String> getToRecordSelector() {
		return toRecordSelector;
	}

	public void setToRecordSelector(Selector<String> toRecordSelector) {
		this.toRecordSelector = toRecordSelector;
	}

	public Comparator<String> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<String> comparator) {
		this.comparator = comparator;
	}

	public String getFromFileName() {
		return fromFileName;
	}

	public String getToFileName() {
		return toFileName;
	}

	public boolean compare() throws Exception
	{
		MyFileReader fromFileReader = new MyFileReader(fromFileName);
		MyFileReader toFileReader = new MyFileReader(toFileName);
		String fromRecord = null;
		String toRecord = null;
		int index = 0;
		fromFileReader.open();
		toFileReader.open();
		LineNumberReader fromReader = fromFileReader.getReader();
		LineNumberReader toReader = toFileReader.getReader();
		try {
			while((fromRecord = fromReader.readLine()) != null)
			{
				if(fromRecordSelector == null || fromRecordSelector.select(fromRecord))
				{
					toRecord = toReader.readLine();
					while(toRecordSelector != null && !toRecordSelector.select(toRecord))
					{
						toRecord = toReader.readLine();
					}
					comparator.compare(fromRecord, toRecord);
					index ++;
				}
			}
		} catch (Exception e) {
			throw new Exception(String.format("LineNumber: %s : %s", index, e.getMessage()));
		}finally
		{
			fromFileReader.close();
		}
		try {
			if((toRecord = toReader.readLine()) != null)
				throw new Exception(String.format("ToFile has more lines than from file which has %s lines", index));
		} catch (Exception e) {
			throw new Exception(e);
		}finally
		{
			toFileReader.close();
		}
		return true;
	}
	
	static class MyFileReader extends BaseFileReader
	{

		public MyFileReader(String fileName) {
			super(fileName, 0);
		}
		public LineNumberReader getReader()
		{
			return reader;
		}
	}
	
	static class MyComparator implements Comparator<String> 
	{

		public int compare(String o1, String o2) {
			if(o1.trim().equals(o2.trim())) return 0;
			throw new RuntimeException(String.format("%s (not equal to) %s", o1, o2));
		}
		
	}
}
