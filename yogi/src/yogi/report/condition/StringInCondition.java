package yogi.report.condition;

public class StringInCondition extends BaseInCondition<String> {

	public StringInCondition(String value) {
		super(value);
	}

	public StringInCondition(String value, char separator) {
		super(value, separator);
	}

	@Override
	public String scan(String token) {
		return token;
	}
	
}
