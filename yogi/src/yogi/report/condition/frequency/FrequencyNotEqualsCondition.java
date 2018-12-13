package yogi.report.condition.frequency;

import yogi.base.io.Scanner;
import yogi.period.frequency.Frequency;
import yogi.report.condition.ConditionBaseImpl;


public class FrequencyNotEqualsCondition extends ConditionBaseImpl<Frequency> {

	private Frequency frequency;
	
	public FrequencyNotEqualsCondition(String value, Scanner<Frequency, String> scanner) {
		super(value);
		frequency = scanner.scan(value).create();
	}
	
	public boolean satisfied(Frequency data) {
		return frequency != data;
	}
}
