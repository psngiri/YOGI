package yogi.report.condition.config;

import java.util.List;

import yogi.report.condition.Condition;
import yogi.report.condition.LongListInCondition;
import yogi.report.server.config.BaseValidator;
import yogi.report.server.config.ConditionConfig;

public class LongListInConditionConfig extends ConditionConfig<List<Long>> {

	private static final long serialVersionUID = 1L;

	public LongListInConditionConfig() {
		super("In", new BaseValidator("^(([0-9])(\\^[0-9])*)(,(([0-9])(\\^[0-9])*))*$", "must be comma seperated list of entities with each entity containing numeric values seperated with ^"));
	}

	@Override
	public Condition<List<Long>> getCondition(String value) {
		return new LongListInCondition(value);
	}

}
