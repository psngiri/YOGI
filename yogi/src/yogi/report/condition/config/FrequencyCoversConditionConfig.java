package yogi.report.condition.config;

import yogi.period.frequency.Frequency;
import yogi.report.condition.Condition;
import yogi.report.condition.frequency.FrequencyCoversCondition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.FrequencyListValidator;

public class FrequencyCoversConditionConfig extends ConditionConfig<Frequency> {

	private static final long serialVersionUID = 1L;

	public FrequencyCoversConditionConfig() {
		super("Covers", new FrequencyListValidator());
	}

	@Override
	public Condition<Frequency> getCondition(String value) {
		return new FrequencyCoversCondition(value.toUpperCase());
	}

}
