package yogi.report.condition.frequency;

import yogi.period.frequency.Frequency;
import yogi.period.frequency.io.MondayToSundayOptionalOffCharFrequencyScanner;
import yogi.report.condition.BaseInCondition;


public class FrequencyInCondition extends BaseInCondition<Frequency> {

	private MondayToSundayOptionalOffCharFrequencyScanner scanner;
	
	public FrequencyInCondition(String value) {
		super(value);
	}
	
	public FrequencyInCondition(String value, char separator) {
		super(value, separator);
	}
	
	public Frequency scan(String token) {
		if(scanner == null) {
			scanner = new MondayToSundayOptionalOffCharFrequencyScanner(new char[]{'M', 'T', 'W', 'Q', 'F', 'J', 'S'});
		}
		return scanner.scan(token).create();
	}
	
}
