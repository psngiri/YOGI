package yogi.report.condition.config;

import yogi.period.frequency.Frequency;
import yogi.report.condition.Condition;
import yogi.report.condition.frequency.FrequencyInCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.FrequencyListValidator;

public class FrequencyInConditionConfig extends ConditionConfig<Frequency> {

	private static final long serialVersionUID = 1L;

	public FrequencyInConditionConfig() {
		super("In", new FrequencyListValidator());
	}

	@Override
	public Condition<Frequency> getCondition(String value) {
		return new FrequencyInCondition(value.toUpperCase());
	}

}
