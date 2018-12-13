package yogi.period.frequency.io;

import yogi.period.frequency.Frequency;

public class MondayToSundayOneToSevenFrequencyFormatter extends MondayToSundayFrequencyFormatter {
	public MondayToSundayOneToSevenFrequencyFormatter()  {
		super('.', new char[] {'1','2','3','4','5','6','7'});
	}
	
	public MondayToSundayOneToSevenFrequencyFormatter(char separator)  {
		super(separator, new char[] {'1','2','3','4','5','6','7'});
	}
	
	@Override
	public String format(Frequency frequency) {
		if(frequency == null) return "";
		
		return super.format(frequency);
	}
}
