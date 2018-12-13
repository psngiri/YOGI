package yogi.report.condition.config;

import java.util.List;

import yogi.report.condition.Condition;
import yogi.report.condition.IntegerListInCondition;
import yogi.report.server.config.BaseValidator;
import yogi.report.server.config.ConditionConfig;

public class IntegerListInConditionConfig extends ConditionConfig<List<Integer>> {

	private static final long serialVersionUID = 1L;

	public IntegerListInConditionConfig() {
		super("In", new BaseValidator("^(([0-9])(\\^[0-9])*)(,(([0-9])(\\^[0-9])*))*$", "must be comma seperated list of entities with each entity containing numeric values seperated with ^"));
	}

	@Override
	public Condition<List<Integer>> getCondition(String value) {
		return new IntegerListInCondition(value);
	}

}
