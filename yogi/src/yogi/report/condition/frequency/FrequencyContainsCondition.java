package yogi.report.condition.frequency;

import yogi.base.io.Scanner;
import yogi.period.frequency.Frequencies;
import yogi.period.frequency.Frequency;
import yogi.report.condition.ConditionBaseImpl;


public class FrequencyContainsCondition extends ConditionBaseImpl<Frequency> {
	private Frequency frequency;
	
	public FrequencyContainsCondition(String value, Scanner<Frequency, String> scanner)
	{
		super(value);
		frequency = scanner.scan(value).create();
	}
	
	public boolean satisfied(Frequency data) {
		return Frequencies.isSubSet(data, frequency);		
	}

}
