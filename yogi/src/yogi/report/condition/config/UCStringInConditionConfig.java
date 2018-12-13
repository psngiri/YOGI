package yogi.report.condition.config;

import yogi.report.condition.Condition;
import yogi.report.condition.StringInCondition;
import yogi.report.server.config.BaseValidator;
import yogi.report.server.config.ConditionConfig;
import yogi.report.server.config.validator.StringInValidator;

public class UCStringInConditionConfig extends ConditionConfig<String> {
	
	private static final long serialVersionUID = 1L;

	public UCStringInConditionConfig() {
		super("In",new StringInValidator());
	}
	
	public UCStringInConditionConfig(BaseValidator validator) {
		super("In", validator);
	}

	@Override
	public Condition<String> getCondition(String value) {
		return new StringInCondition(value){

			@Override
			public String scan(String token) {
				return super.scan(token.toUpperCase());
			}
			
		};
	}

}

