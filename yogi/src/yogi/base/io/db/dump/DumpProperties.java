package yogi.base.io.db.dump;

import yogi.base.indexing.Indexer;
import yogi.base.io.Scanner;
import yogi.period.date.Date;
import yogi.period.date.io.YYYYMMDDDateScanner;

public class DumpProperties {
	public static String ColumnSeparator = ",";
	public static String DumpDirectoryName = "dbDump";
	public static boolean ReadFromDbDump = false;
	public static boolean WriteToDbDump = false;
	public static boolean AppendWriterDumpToCorrespondingReaderDump = true;
	public static boolean DeleteDumpOnSuccessfullExit = false;
	public static boolean LoadToDb = false;
	public static Scanner<Date,String> dateScanner = new YYYYMMDDDateScanner();
	private static Indexer<String, Integer> fileIndexer = new Indexer<String, Integer>();
	
	public static String getDumpLocation()
	{
		return "/" + DumpDirectoryName + "/";
	}
	
	public static String getDumpFileName(Class<?> klass) {
		String className = klass.getName();
		return DumpProperties.getDumpLocation() + className + getFileIndexValue(className) + ".dump";
	}

	private static String getFileIndexValue(String className)
	{
		Integer index = fileIndexer.get(className);
		int indexValue = 0;
		String fileIndexValue = "";
		if(index != null){
			indexValue = index;
			fileIndexValue = String.valueOf(indexValue);
		}
		fileIndexer.index(className, indexValue+1);
		return fileIndexValue;
	}
	
	public static void clearFileIndexer(){
		fileIndexer.clear();
	}
	
}
