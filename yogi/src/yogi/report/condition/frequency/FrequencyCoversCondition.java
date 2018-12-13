package yogi.report.condition.frequency;

import java.util.HashSet;
import java.util.Scanner;

import yogi.period.frequency.Frequencies;
import yogi.period.frequency.Frequency;
import yogi.period.frequency.FrequencyManager;
import yogi.period.frequency.io.MondayToSundayOptionalOffCharFrequencyScanner;
import yogi.report.condition.ConditionBaseImpl;


public class FrequencyCoversCondition extends ConditionBaseImpl<Frequency> {

	private static MondayToSundayOptionalOffCharFrequencyScanner scanner = new MondayToSundayOptionalOffCharFrequencyScanner(new char[]{'M', 'T', 'W', 'Q', 'F', 'J', 'S'});
	
	HashSet<Frequency> frequencySet = new HashSet<Frequency>();
	
	public FrequencyCoversCondition(String value) {
		this(value, ',');
	}
	
	public FrequencyCoversCondition(String value, char separator) {
		super(value);
		Scanner scanner = new Scanner(value);
		scanner.useDelimiter(String.valueOf(separator));
		while(scanner.hasNext())
		{
			String token = scanner.next().trim();
			frequencySet.add(scan(token));
		}
	}

	public Frequency scan(String token) {
		return scanner.scan(token).create();
	}
	
	@Override
	public boolean satisfied(Frequency data) {
		if(data == null) return true;
		for(Frequency frequency : frequencySet) {
			if(data == FrequencyManager.NoDayFrequency) {
				if(frequency == FrequencyManager.NoDayFrequency) {
					return true;
				} else {
					continue;
				}
			}
			if(Frequencies.isSubSet(frequency, data)) return true;
		}
		return false;
	}
}
