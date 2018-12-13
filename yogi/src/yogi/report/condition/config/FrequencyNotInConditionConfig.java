package yogi.report.condition.config;

import yogi.period.frequency.Frequency;
import yogi.report.condition.Condition;
import yogi.report.condition.frequency.FrequencyNotInCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.FrequencyListValidator;

public class FrequencyNotInConditionConfig extends ConditionConfig<Frequency> {

	private static final long serialVersionUID = 1L;

	public FrequencyNotInConditionConfig() {
		super("NotIn", new FrequencyListValidator());
	}

	@Override
	public Condition<Frequency> getCondition(String value) {
		return new FrequencyNotInCondition(value.toUpperCase());
	}

}
