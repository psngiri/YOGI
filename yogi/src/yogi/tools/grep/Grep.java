package yogi.tools.grep;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import yogi.base.Selector;
import yogi.base.util.converter.FileConverter;


public class Grep {
	public static void main(String[] args) {
		if(args.length < 2) {
			System.out.println("Usage: Grep pattern inputFileName [outputFileName]");
			return;
		}
		String pattern = args[0];
		String inputFileName = args[1];
		String outputFileName = null;
		if(args.length > 2) outputFileName = args[2];
		FileConverter fileConverter = new FileConverter(inputFileName, outputFileName);
		fileConverter.addRecordSelector(new MySelector(pattern));
		fileConverter.convert();
	}

	public static class MySelector implements Selector<String>
	{
		private Pattern pattern;
		public MySelector(String regex) {
			super();
	        pattern = Pattern.compile(regex);
		}
		public boolean select(String record) {
	        Matcher matcher = pattern.matcher(record);
			return matcher.matches();
		}
		
	}
}
