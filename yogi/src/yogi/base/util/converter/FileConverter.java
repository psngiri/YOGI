package yogi.base.util.converter;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import yogi.base.Selector;
import yogi.base.io.BaseFileReader;
import yogi.base.io.BaseHeaderFileWriter;

public class FileConverter {
	private String inputFileName;
	private String outputFileName;
	private List<RecordConverter> recordConverters = new ArrayList<RecordConverter>();
	private List<Selector<String>> recordSelectors = new ArrayList<Selector<String>>();
	private boolean append = false;
	
	public FileConverter() {
		super();
	}
	
	public FileConverter(String inputFileName, String outputFileName) {
		super();
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
	}

	public FileConverter(String inputFileName) {
		super();
		this.inputFileName = inputFileName;
		this.outputFileName = null;
	}

	public void addRecordConverter(RecordConverter recordConverter)
	{
		recordConverters.add(recordConverter);
	}
	
	public void addRecordSelector(Selector<String> recordSelector)
	{
		recordSelectors.add(recordSelector);
	}
	
	public void clear()
	{
		recordConverters.clear();
		recordSelectors.clear();
	}
	
	public String getInputFileName() {
		return inputFileName;
	}

	public String getOutputFileName() {
		return outputFileName;
	}

	public void setInputFileName(String inputFileName) {
		this.inputFileName = inputFileName;
	}

	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}

	public boolean isAppend() {
		return append;
	}

	public void setAppend(boolean append) {
		this.append = append;
	}

	public void convert()
	{
		MyFileReader fileReader = new MyFileReader(inputFileName);
		MyFileWriter fileWriter = (outputFileName == null)? new SystemOutWriter(): new MyFileWriter(outputFileName);
		fileWriter.setAppend(isAppend());
		fileReader.open();
		fileWriter.open();
		LineNumberReader reader = fileReader.getReader();
		PrintWriter writer = fileWriter.getWriter();
		String record = null;
		try {
			while((record = reader.readLine()) != null)
			{
				if(!select(record)) continue ;
				for(RecordConverter recordConverter: recordConverters)
				{
					record = recordConverter.convert(record);
				}
				if(record != null) writer.println(record);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}finally
		{
			fileReader.close();
			fileWriter.close();
		}
	}

	private boolean select(String record) {
		for(Selector<String> recordFilter: recordSelectors)
		{
			if(!recordFilter.select(record)) return false;
		}
		return true;
	}
	
	public static class MyFileReader extends BaseFileReader
	{

		public MyFileReader(String fileName) {
			super(fileName, 0);
		}
		public LineNumberReader getReader()
		{
			return reader;
		}
	}
	
	public static class MyFileWriter extends BaseHeaderFileWriter
	{
		protected MyFileWriter()
		{
			super();
		}
		
		public MyFileWriter(String fileName) {
			super(fileName);
		}
		public PrintWriter getWriter()
		{
			return writer;
		}
	}
	
	public static class SystemOutWriter extends MyFileWriter
	{
		private PrintWriter printWriter;

		public SystemOutWriter() {
			super();
		}
		
		@Override
		public boolean open() {
			printWriter = new PrintWriter(System.out, true);
			return true;
		}

		@Override
		public boolean close() {
			printWriter.flush();
			return true;
		}

		public PrintWriter getWriter()
		{
			return printWriter;
		}
	}
}
