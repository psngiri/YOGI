package yogi.report.condition.config;

import yogi.report.condition.ByteNotInCondition;
import yogi.report.condition.Condition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.ByteInValidator;

public class ByteNotInConditionConfig extends ConditionConfig<Byte> {

	private static final long serialVersionUID = 1L;

	public ByteNotInConditionConfig() {
		super("NotIn", new ByteInValidator());
	}

	@Override
	public Condition<Byte> getCondition(String value) {
		return new ByteNotInCondition(value);
	}

}
