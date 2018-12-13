package yogi.report.condition.config;

import yogi.base.io.Scanner;
import yogi.period.frequency.Frequency;
import yogi.period.frequency.io.MondayToSundayOptionalOffCharFrequencyScanner;
import yogi.report.condition.Condition;
import yogi.report.condition.frequency.FrequencyNotEqualsCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.FrequencyValidator;

public class FrequencyNotEqualsConditionConfig extends ConditionConfig<Frequency> {

	private static final long serialVersionUID = 1L;
	private Scanner<Frequency, String> scanner = new MondayToSundayOptionalOffCharFrequencyScanner(new char[]{'M', 'T', 'W', 'Q', 'F', 'J', 'S'});


	public FrequencyNotEqualsConditionConfig() {
		super("NotEquals", new FrequencyValidator());
	}
	
	public FrequencyNotEqualsConditionConfig(Scanner<Frequency, String> scanner)
	{
		this();
		this.scanner = scanner;
	}

	@Override
	public Condition<Frequency> getCondition(String value) {
		return new FrequencyNotEqualsCondition(value.toUpperCase(),scanner);
	}

}
