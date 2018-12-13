package yogi.report.condition;


public class StringNumberLikeCondition extends BaseLikeCondition<String> {
	
	public StringNumberLikeCondition(String value) {
		super(value.toUpperCase());		
	}
}
