package yogi.report.condition.frequency;

import yogi.period.frequency.Frequency;


public class FrequencyNotInCondition extends FrequencyInCondition {

	public FrequencyNotInCondition(String value) {
		super(value);
	}

	@Override
	public boolean satisfied(Frequency data) {
		return !super.satisfied(data);
	}
	
}

