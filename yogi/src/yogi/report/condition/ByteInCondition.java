package yogi.report.condition;


public class ByteInCondition extends BaseInCondition<Byte> {

	public ByteInCondition(String value) {
		super(value);
	}
	public ByteInCondition(String value,char separator) {
		super(value, separator);
	}

	@Override
	public Byte scan(String token) {
		return Byte.valueOf(token);
	}
	
}
