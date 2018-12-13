package yogi.base.util;

import yogi.base.Selector;
import yogi.base.util.converter.FileConverter;
import yogi.base.util.converter.RecordConverter;

public class Head {
	private String inputFileName;
	private String outputFileName;
	private int count;
	
	public Head(String inputFileName, String outputFileName, int count) {
		super();
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
		this.count = count;
	}

	public static void main(String[] args) 
	{
		
		if(args.length < 2)
			System.out.println("Usage : Head <inputFileName> <count>");
		else{
			String outfile1 = args[0] + ".out";
			new Head(args[0], outfile1, Integer.parseInt(args[1])).create();
		}
	}

	public void create() 
	{
		FileConverter fileConverter = new FileConverter();
		fileConverter.setInputFileName(inputFileName);
		fileConverter.setOutputFileName(outputFileName);
		
		fileConverter.addRecordConverter(new MyRecordConverter());
		fileConverter.addRecordSelector(new MyRecordSelector(this));
		
		fileConverter.convert();
	}
	
	static class MyRecordConverter implements RecordConverter
	{

		public String convert(String record) {
			
			return record;
		}
	}

	static class MyRecordSelector implements Selector<String>
	{
		private int counter = 0;
		private Head head;
		
		public MyRecordSelector(Head head) {
			this.head = head;
		}

		public boolean select(String item) {
			counter++;
			if(counter > head.count) throw new RuntimeException("Done");
			return true;
		}

	}
}
