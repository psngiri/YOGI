package yogi.report.condition;

public class CharacterInCondition extends BaseInCondition<Character> {

	public CharacterInCondition(String value) {
		super(value);
	}

	public CharacterInCondition(String value, char separator) {
		super(value, separator);
	}

	@Override
	public Character scan(String token) {
		return token.charAt(0);
	}
	
}
