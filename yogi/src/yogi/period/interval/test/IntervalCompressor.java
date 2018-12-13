package yogi.period.interval.test;

import java.util.ArrayList;
import java.util.List;

import yogi.base.Selector;
import yogi.base.Util;
import yogi.base.io.Formatter;
import yogi.base.util.converter.FileConverter;
import yogi.base.util.converter.RecordConverter;
import yogi.period.date.Date;
import yogi.period.date.io.DDMMMYYDateFormatter;
import yogi.period.date.io.DDMMMYYDateScanner;
import yogi.period.date.range.DateRange;
import yogi.period.date.range.io.DateRangeFormatter;
import yogi.period.frequency.Frequency;
import yogi.period.frequency.io.MondayToSundayFrequencyFormatter;
import yogi.period.frequency.io.MondayToSundayFrequencyScanner;
import yogi.period.interval.Interval;
import yogi.period.interval.Intervals;
import yogi.period.interval.io.IntervalFormatter;

public class IntervalCompressor {

	private String inputFileName;
	private String outputFileName;
	private String discontinueDate;
	
	public IntervalCompressor(String inputFileName, String outputFileName, String discontinueDate) {
		super();
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
		this.discontinueDate = discontinueDate;
	}

	public static void main(String[] args) 
	{
		
		if(args.length < 2)
			System.out.println("Usage : SimExploder <inputFileName1> <discontinueDate DDMMMYY>");
		String outfile1 = args[0] + ".out";
	
		new IntervalCompressor(args[0], outfile1, args[1]).compress();
	}

	public void compress() 
	{
		FileConverter fileConverter = new FileConverter();
		fileConverter.setInputFileName(inputFileName);
		fileConverter.setOutputFileName(outputFileName);
		
		fileConverter.addRecordConverter(new MyRecordConverter(discontinueDate, this));
		fileConverter.addRecordSelector(new MyRecordSelector());
		
		fileConverter.convert();
	}

	protected List<Interval> compressIntervals(List<Interval> intervals) {
		return Intervals.compress(intervals);
	}
	
	private static class MyRecordConverter implements RecordConverter
	{
		String systemEndDate;
		DDMMMYYDateScanner dateScanner = new DDMMMYYDateScanner();
		MondayToSundayFrequencyScanner frequencyScanner;
		IntervalFormatter intervalFormatter;
		String previousKey = null;
		String previousRecord = null;
		List<Interval> intervals = new ArrayList<Interval>();
		IntervalCompressor intervalCompressor;
		
		public MyRecordConverter(String systemEndDate, IntervalCompressor intervalCompressor) 
		{
			super();
			this.systemEndDate = systemEndDate;
			char[] onCharacters = new char[] {'1','2','3','4','5','6','7'};
			frequencyScanner = new MondayToSundayFrequencyScanner(onCharacters);
			Formatter<Frequency> frequencyFormatter = new MondayToSundayFrequencyFormatter(' ',onCharacters);
			Formatter<DateRange> dateRangeFormatter = new DateRangeFormatter(new DDMMMYYDateFormatter());
			intervalFormatter = new IntervalFormatter(dateRangeFormatter, frequencyFormatter);
			this.intervalCompressor = intervalCompressor;
		}

		public String convert(String record) 
		{
			String inputRecord = record.substring(0, 71);
			String key = inputRecord.substring(1,9) + inputRecord.substring(11,13) + inputRecord.substring(35);
			Interval inputInterval = getInterval(inputRecord);
			
			String returnValue = null;
			try {
				returnValue = write(key);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				System.out.println("Input record: " + inputRecord);
				System.out.println("Previous record: " + previousRecord);
				e.printStackTrace();
				System.exit(1);
			}
			intervals.add(inputInterval);
			previousRecord = inputRecord;
			previousKey = key;
			return returnValue;
		}

		private String write(String key) 
		{	
			if(key.equals(previousKey) || previousKey == null) return null;
			
					
			List<Interval> compressedIntervals = intervalCompressor.compressIntervals(intervals);
			if(!Intervals.same(intervals, compressedIntervals))
				System.out.println("Intervals are different: " + intervals + " compressed intervals : "+ compressedIntervals);
			intervals.clear();
			
					
			if(compressedIntervals.isEmpty())
				return null;
			
			String inputIntervalString = previousRecord.substring(14, 35);
			
			StringBuilder stringBuilder = new StringBuilder();
			for(int i =0; i < compressedIntervals.size(); i++)
			{
				String outputIntervalString = intervalFormatter.format(compressedIntervals.get(i));
				String outputRecord = previousRecord.replace(inputIntervalString, outputIntervalString);
				stringBuilder.append(outputRecord.substring(0,9)).append(String.format("%02d",i+1)).append(outputRecord.substring(11, outputRecord.length()));
				stringBuilder.append(Util.getLineSeparator());
			}
			String rtnValue = stringBuilder.toString();
			return rtnValue.substring(0, rtnValue.length()-1);
			
		}


		private Interval getInterval(String ssimRecord) 
		{
			String inputStartDateString = null;
			inputStartDateString = ssimRecord.substring(14, 21);
			
			String inputEndDateString = ssimRecord.substring(21, 28);
			String inputFrequencyString = ssimRecord.substring(28,35);
			
			if(inputEndDateString.equals("00XXX00"))
				inputEndDateString = systemEndDate;
		
			Date inputStartDate = dateScanner.scan(inputStartDateString).create();
			Date inputEndDate = dateScanner.scan(inputEndDateString).create();
			Frequency inputFrequency = frequencyScanner.scan(inputFrequencyString).create();
			Interval interval = new Interval(inputStartDate, inputEndDate, inputFrequency);
			return interval;
		}
		
	}
	
	private static class MyRecordSelector implements Selector<String>
	{
		public boolean select(String record) {
			return record.startsWith("3");
		}
		
	}
}
