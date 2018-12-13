package yogi.report.condition.config;

import yogi.report.condition.ByteInCondition;
import yogi.report.condition.Condition;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.ByteInValidator;

public class ByteInConditionConfig extends ConditionConfig<Byte> {

	private static final long serialVersionUID = 1L;

	public ByteInConditionConfig() {
		super("In", new ByteInValidator());
	}

	@Override
	public Condition<Byte> getCondition(String value) {
		return new ByteInCondition(value);
	}

}
